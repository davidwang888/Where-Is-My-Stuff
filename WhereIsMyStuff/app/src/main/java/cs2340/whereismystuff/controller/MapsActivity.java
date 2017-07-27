package cs2340.whereismystuff.controller;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import cs2340.whereismystuff.R;
import cs2340.whereismystuff.model.Item;
import cs2340.whereismystuff.model.ItemType;
import cs2340.whereismystuff.model.Model;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private boolean _clickable;
    private boolean _isLostItem;
    private GoogleMap mMap;
    private Model _model = Model.getInstance();

    private TextView _mapsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        _clickable = getIntent().getExtras() != null;
        _mapsTextView = (TextView) findViewById(R.id.mapsTitleTextView);
        if (_clickable) {
            _isLostItem = getIntent().getExtras().getBoolean("isLostItem");
        }
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
                if (_clickable) {
                    onClick(mMap, latLng);
                }
            }
        });
        List<Item> itemList;
        if (_clickable) {
            if (_isLostItem) {
                _mapsTextView.setText("Click on where you lost the item!");
                itemList = _model.getLostItems();
            } else {
                _mapsTextView.setText("Click on where you found the item!");
                itemList = _model.getFoundItems();
            }
        } else {
            _mapsTextView.setText("Locations of All Items");
            itemList = _model.getLostItems();
            itemList.addAll(_model.getFoundItems());
        }
        for (Item r : itemList) {
            LatLng loc = r.getLatLng();

            Marker m = mMap.addMarker(new MarkerOptions().position(loc).title(r
                    .getName
                    ()).snippet(r.getDescription()));
            m.setTag(r.getType().ordinal());
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }

        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }

    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        CustomInfoWindowAdapter(){
            myContentsView = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = ((TextView) myContentsView.findViewById(R.id
                    .customInfoContentsTitle));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = ((TextView) myContentsView.findViewById(R.id
                    .customInfoContentsSnippet));
            tvSnippet.setText(marker.getSnippet());

            ImageView imageView = (ImageView) myContentsView.findViewById(R
                    .id.customInfoContentsImageView);
            //Drawable drawable = getResources().getDrawable(R.drawable
                    //.furniture);
            Drawable drawable = null;
            if (marker.getTag().equals(0)) {
                drawable = ContextCompat.getDrawable
                        (getApplicationContext(),R.drawable.technological);
            } else if (marker.getTag().equals(1)) {
                drawable = ContextCompat.getDrawable
                        (getApplicationContext(),R.drawable.furniture);
            } else if (marker.getTag().equals(2)) {
                drawable = ContextCompat.getDrawable
                        (getApplicationContext(),R.drawable.recreational);
            } else if (marker.getTag().equals(3)) {
                drawable = ContextCompat.getDrawable
                        (getApplicationContext(),R.drawable.personal);
            } else if (marker.getTag().equals(4)) {
                drawable = ContextCompat.getDrawable
                        (getApplicationContext(),R.drawable.pet);
            } else {
                drawable = ContextCompat.getDrawable
                        (getApplicationContext(),R.drawable.other);
            }

            imageView.setBackground(drawable);

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }

    }

    /**
     * Creates a marker of the click location on the map. Starts correct
     * intent to move to either EnterLostItem or EnterFoundItem
     */
    private void onClick(GoogleMap mMap, LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        Marker m = mMap.addMarker(markerOptions);
        m.setTag(0);
        Intent intent;
        if (_isLostItem) {
            intent = new Intent(MapsActivity.this, EnterLostItemActivity.class);
        } else {
            intent = new Intent(MapsActivity.this, EnterFoundItemActivity
                    .class);
        }
        intent.putExtra("latLng", latLng);
        startActivity(intent);
    }
}
