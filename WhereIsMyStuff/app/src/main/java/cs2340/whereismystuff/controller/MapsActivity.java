package cs2340.whereismystuff.controller;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Scanner;

import cs2340.whereismystuff.R;
import cs2340.whereismystuff.model.Item;
import cs2340.whereismystuff.model.Model;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private boolean _isFoundItem;
    private GoogleMap mMap;
    private Model _model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        System.out.println("in MapsActivity.java/onCreate");
        System.out.println(getIntent().getExtras().getBoolean("isFoundItem"));
        if (getIntent().getExtras().getBoolean("isFoundItem")) {
            _isFoundItem = true;
        } else {
            _isFoundItem = false;
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Setting a click event handler for the map
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);
                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                mMap.addMarker(markerOptions);
                if (_isFoundItem) {
                    Intent intent = new Intent(MapsActivity.this,
                            EnterFoundItemActivity
                            .class);
                    intent.putExtra("latLng", latLng);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MapsActivity.this,
                            EnterLostItemActivity
                                    .class);
                    intent.putExtra("latLng", latLng);
                    startActivity(intent);
                }



            }
        });

        if (_isFoundItem) {
            List<Item> itemList = _model.getFoundItems();
            for (Item r : itemList) {
                LatLng loc = r.getLatLng();
                mMap.addMarker(new MarkerOptions().position(loc).title(r.getName()).snippet(r.getDescription()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
            }

            //mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
        } else {
            List<Item> itemList = _model.getLostItems();
            for (Item r : itemList) {
                LatLng loc = r.getLatLng();
                mMap.addMarker(new MarkerOptions().position(loc).title(r.getName()).snippet(r.getDescription()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
            }
        }

    }
}
