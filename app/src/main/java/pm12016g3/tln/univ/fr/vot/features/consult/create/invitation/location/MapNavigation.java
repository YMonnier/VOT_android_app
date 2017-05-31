package pm12016g3.tln.univ.fr.vot.features.consult.create.invitation.location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import pm12016g3.tln.univ.fr.vot.features.MainActivity;

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

    @RootContext
    MainActivity context;


    /**
     * Initialize the Map Navigation and the Location Service.
     */
    public void init() {
        // Get Google Map and initialize it
        /*SupportMapFragment mapFragment = (SupportMapFragment) context.getSupportFragmentManager()
                .findFragmentById(R.id.map_navigation);*/
        //mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //googleMap.setOnMapLongClickListener(this);
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        mapView = googleMap;
    }
}
