package com.alexnaupay.hselfiecamera;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.maps.CameraUpdateFactory;
import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.MapsInitializer;
import com.huawei.hms.maps.OnMapReadyCallback;
import com.huawei.hms.maps.SupportMapFragment;
import com.huawei.hms.maps.model.BitmapDescriptorFactory;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.Marker;
import com.huawei.hms.maps.model.MarkerOptions;
import com.huawei.hms.push.HmsMessaging;

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

        subscribe("hTopic");
    }


    @Override
    public void onMapReady(HuaweiMap huaweiMap) {
        Log.d(TAG, "onMapReady: ");
        map = huaweiMap;
        map.setMapType(HuaweiMap.MAP_TYPE_TERRAIN);
        map.getUiSettings().setAllGesturesEnabled(true);

        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(-15.8369, -72.1326))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.epicenter_yellow)).snippet("Sur"));
        //marker.setSnippet("Sur");

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-15.8369, -72.1326), 5));
    }

    public void subscribe(String topic) {
        try {
            // Subscribe to a topic.
            HmsMessaging.getInstance(this).subscribe(topic)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            // Obtain the topic subscription result.
                            if (task.isSuccessful()) {
                                Log.i(TAG, "subscribe topic successfully");
                                Toast.makeText(HuaweiMapActivity.this, "subscribe topic successfully", Toast.LENGTH_LONG).show();
                            } else {
                                Log.e(TAG, "subscribe topic failed, return value is " + task.getException().getMessage());
                            }
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "subscribe failed, catch exception : " + e.getMessage());
        }
    }

}
