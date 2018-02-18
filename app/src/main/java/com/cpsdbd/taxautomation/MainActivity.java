package com.cpsdbd.taxautomation;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cpsdbd.taxautomation.Activities.BaseActivity;
import com.cpsdbd.taxautomation.Activities.DetailActivity;
import com.cpsdbd.taxautomation.Model.Entity;
import com.cpsdbd.taxautomation.Utility.MyDatabaseRef;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends BaseActivity implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private Marker mMarker;

    LatLng dhakaLatLong;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpNavigationDrawer();

        dhakaLatLong = new LatLng(23.7276,90.4106);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        //polyLineList = new ArrayList<>();

        mapFragment.getMapAsync(MainActivity.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.setTrafficEnabled(false);
        mMap.setIndoorEnabled(false);
        mMap.setBuildingsEnabled(true);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Log.d("HHHH","MMMM AR");
                final Entity entity = (Entity) marker.getTag(); //

                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("entity",entity);

                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                },1000);

                return false;
            }
        });

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                //Log.d("HHHH","Called");
            }
        });
        mMap.getUiSettings().setCompassEnabled(false);

        gotoLocation(dhakaLatLong);


        getDataFromDatabase();

    }

    private void getDataFromDatabase() {
        MyDatabaseRef myDatabaseRef = new MyDatabaseRef();

        myDatabaseRef.getEntityRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot x: dataSnapshot.getChildren()){
                    Entity entity = x.getValue(Entity.class);

                    Log.d("MMMM",entity.getId());

                    MyThread myThread = new MyThread(entity);
                    myThread.start();




                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void gotoLocation(LatLng dhakaLatLong) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(dhakaLatLong).zoom(12).tilt(30).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }


    public class MyThread extends Thread{

        Handler handler = new Handler();

        private Entity entity;

        volatile int counter;

        public MyThread(Entity entity) {
            this.entity = entity;



        }

        @Override
        public void run() {

            counter++;

            Log.d("RRRR",counter+"");

            Log.d("SOHEL","JJJ");

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Marker marker =mMap.addMarker(new MarkerOptions().position(new LatLng(entity.getLat(),entity.getLng())).title(entity.getAddress()));
                    marker.setTag(entity);

                    switch (entity.getStatus()){
                        case 1:
                            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.blue));
                            break;

                        case 2:
                            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.red));
                            break;

                        case 3:
                            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.green));
                            break;
                        case 4:
                            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.orang));

                            break;
                    }

                }
            },100);
        }
    }
}
