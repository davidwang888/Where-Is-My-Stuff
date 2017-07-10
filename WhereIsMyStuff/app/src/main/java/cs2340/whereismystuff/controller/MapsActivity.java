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
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        _isFoundItem = getIntent().getExtras().getBoolean("isFoundItem");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     *
     * Specific to this app, either all the lost items or the found items
     * will appear on the map
     *
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.addMarker(markerOptions);
                Intent intent;
                if (_isFoundItem) {
                    intent = new Intent(MapsActivity.this,
                            EnterFoundItemActivity
                            .class);
                } else {
                    intent = new Intent(MapsActivity.this,
                            EnterLostItemActivity
                                    .class);
                }
                intent.putExtra("latLng", latLng);
                startActivity(intent);
            }
        });
        List<Item> itemList;
        if (_isFoundItem) {
            itemList = _model.getFoundItems();
        } else {
            itemList = _model.getLostItems();
        }
        for (Item r : itemList) {
            LatLng loc = r.getLatLng();
            mMap.addMarker(new MarkerOptions().position(loc).title(r.getName()).snippet(r.getDescription()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }

    }
}
