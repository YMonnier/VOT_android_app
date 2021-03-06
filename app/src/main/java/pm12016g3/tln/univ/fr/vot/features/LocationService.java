package pm12016g3.tln.univ.fr.vot.features;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import pm12016g3.tln.univ.fr.vot.models.location.Geo;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.
 * File LocationService.java.
 * Created by Ysee on 30/05/2017 - 11:39.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

public class LocationService extends Service implements LocationListener {

    /**
     * Tag used for Logger.
     */
    private static final String TAG = LocationService.class.getSimpleName();

    /**
     * Intent identifier for location broadcast.
     */
    public static final String LOCATION_BROADCAST = "LOCATION_BROADCAST";


    /**
     * Intent identifier to asking location settings.
     */
    public static final String ASK_LOCATION_SETTINGS_BROADCAST = "ASK_LOCATION_SETTINGS_BROADCAST";

    /**
     * Intent extra identifier for location broadcast.
     */
    public static final String EXTRA_LOCATION = "EXTRA_LOCATION";

    /**
     * Location manager use to handle updating position.
     */
    LocationManager locationManager;

    /**
     * Store the previous location to copare with the current
     * location to know which location has the best accuracy.
     */
    private Location currentBestLocation = null;


    private int timeInterval = 60_000;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    /**
     * Start the Location Service.
     * Add the Location Update for Network and GPS Provider.
     * If the the application does not follows permission, we
     * ask the user to change it.
     * @param intent
     * @param startId
     */
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "onStart");


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "ActivityCompat.checkSelfPermission::OK");
            broadcastLocationSettings();
        } else {
            Log.d(TAG, "ActivityCompat.checkSelfPermission::NOT OK");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(this);
    }

    /**
     * Used to broadcast a message to
     * alert user that GPS is disable.
     * See `MapNavigation.askLocationReceiver`
     */
    private void broadcastLocationSettings() {
        Intent intent = new Intent(ASK_LOCATION_SETTINGS_BROADCAST);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /**
     * Used to broadcast the location updated to the MapNavigation.
     * @param location sharing location
     */
    private void broadcastLocation(Location location) {
        Log.d(TAG, "broadcast location : " + location);



        Geo coordinate = new Geo.Builder()
                .setLatitude(location.getLatitude())
                .setLongitude(location.getLongitude())
                .build();

        Intent intent = new Intent(LOCATION_BROADCAST);
        intent.putExtra(EXTRA_LOCATION, new Gson().toJson(coordinate));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /**
     * Method called when the location is updated.
     * @param location new location(GPS coordinates)
     */
    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "location updated: " + location);

        if (isBetterLocation(location, currentBestLocation)) {
            currentBestLocation = location;
            broadcastLocation(currentBestLocation);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.d(TAG, "onStatusChanged: " + s + " | " + i);
    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(getBaseContext(), "Gps is turned on!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        /*Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        */
        Toast.makeText(getBaseContext(), "Gps is turned off!! ",
                Toast.LENGTH_SHORT).show();
    }


    /**
     * From android documentation.
     * Determines whether one location reading is better than the current location fix
     *
     * @param location            The new location that you want to evaluate
     * @param currentBestLocation The current location fix, to which you want to compare the new one.
     */
    protected boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > timeInterval;
        boolean isSignificantlyOlder = timeDelta < -timeInterval;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use the new location,
        // because the user has likely moved.
        if (isSignificantlyNewer) {
            return true;
            // If the new location is more than two minutes older, it must be worse.
        } else if (isSignificantlyOlder) {
            return false;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(),
                currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }

    /**
     * From android documentation.
     * Checks whether two providers are the same
     */
    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }

    /**
     * From android documentation.
     *
     * @return the last know best location
     */
    /*
    private Location getLastBestLocation() {
        Location location = null;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location locationNet = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            long GPSLocationTime = 0;
            if (null != locationGPS) {
                GPSLocationTime = locationGPS.getTime();
            }

            long NetLocationTime = 0;

            if (null != locationNet) {
                NetLocationTime = locationNet.getTime();
            }

            if (0 < GPSLocationTime - NetLocationTime) {
                location = locationGPS;
            } else {
                location = locationNet;
            }
        }
        return location;
    }
*/
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
