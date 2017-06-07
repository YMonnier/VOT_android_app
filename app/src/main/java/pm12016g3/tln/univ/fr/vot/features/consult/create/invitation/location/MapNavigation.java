package pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.location;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.MainActivity;
import pm12016g3.tln.univ.fr.vot.features.Settings;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.location.
 * File MapNavigation.java.
 * Created by Ysee on 30/05/2017 - 11:34.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EBean
public class MapNavigation implements OnMapReadyCallback {

    /**
     * Tag used for Logger.
     */
    private static final String TAG = MapNavigation.class.getSimpleName();
    /**
     * Map view
     */
    GoogleMap mapView;

    LocationListener locationListener;

    LocationRequest locationRequest;

    Marker marker;


    @RootContext
    MainActivity context;


    /**
     * Initialize the Map Navigation and the Location Service.
     */
    public void init() {
        // Get Google Map and initialize it
        /*SupportMapFragment mapFragment = (SupportMapFragment) context.getSupportFragmentManager()
                .findFragmentById(R.id.map_navigation);
        mapFragment.getMapAsync(this);*/
        MapFragment mapFragment = ((MapFragment)context.getFragmentManager().findFragmentById(R.id.map_navigation));


        try {
            MapsInitializer.initialize(context.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.setOnMapLongClickListener((GoogleMap.OnMapLongClickListener) this);
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setMyLocationEnabled(true);

        locationListener = new com.google.android.gms.location.LocationListener() {
            private boolean firstTime = true;

            @Override
            public void onLocationChanged(Location location) {
                LatLng latLng = new LatLng(location.getLongitude(),location.getLatitude());
                mapView.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                mapView.addMarker(new MarkerOptions().title("Vous êtes ici").position(latLng).draggable(true));

            }
        };
        LocationServices.FusedLocationApi.requestLocationUpdates(Settings.googleApiClient, locationRequest, locationListener);

    }

}
