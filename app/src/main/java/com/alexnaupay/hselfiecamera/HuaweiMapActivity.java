package com.alexnaupay.hselfiecamera;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.huawei.hms.maps.CameraUpdateFactory;
import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.MapsInitializer;
import com.huawei.hms.maps.OnMapReadyCallback;
import com.huawei.hms.maps.SupportMapFragment;
import com.huawei.hms.maps.model.BitmapDescriptorFactory;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.Marker;
import com.huawei.hms.maps.model.MarkerOptions;

public class HuaweiMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = HuaweiMapActivity.class.getSimpleName();

    private HuaweiMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.huawei_map_activity);

        MapsInitializer.initialize(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //To add a fragment dynamically
        SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.map_frame, supportMapFragment)
                .commit();
        supportMapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(HuaweiMap huaweiMap) {
        Log.d(TAG, "onMapReady: ");
        map = huaweiMap;
        map.setMapType(HuaweiMap.MAP_TYPE_TERRAIN);
        map.getUiSettings().setAllGesturesEnabled(true);

        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(-15.8369, -72.1326))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.epicenter_yellow)));
        marker.setSnippet("Sur");

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-15.8369, -72.1326), 5));
    }
}
