package cs2340.whereismystuff.controller;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.HashMap;

import cs2340.whereismystuff.R;
import cs2340.whereismystuff.model.Item;
import cs2340.whereismystuff.model.ItemType;
import cs2340.whereismystuff.model.Model;
import cs2340.whereismystuff.model.User;

/**
 * Represents the welcome screen that is the first screen the user sees when the
 * app is started
 *
 * Gives the user the option to login or register and loads the appropriate
 * Activity
 */
public class WelcomeActivity extends AppCompatActivity {

    /**
     * Button the user clicks to login
     */
    private Button _loginButton;

    /**
     * Button the user clicks to register
     */
    private Button _registerButton;

    private Model _model = Model.getInstance();

    private DatabaseReference _databaseRef = FirebaseDatabase.getInstance()
            .getReference();
    private DatabaseReference _lostItems;
    private DatabaseReference _foundItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        _loginButton = (Button) findViewById(R.id.welcomeLoginButton);
        _registerButton = (Button) findViewById(R.id.welcomeRegisterButton);
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginButtonClick();
            }
        });
        _registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterButtonClick();
            }
        });
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword("bursar@duke.edu", "123456789");
        _lostItems = _databaseRef.child("lost items");
        _lostItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    String name = (String) snap.child("_name").getValue();
                    int type = ItemType.valueOf((String) snap.child("_type")
                            .getValue
                            ()).ordinal();
                    String description = (String) snap.child("_description")
                            .getValue();
                    User user = snap.child("_user").getValue(User.class);
                    Double latitude = (Double) snap.child("_latLng").child
                            ("latitude").getValue();
                    Double longitude = (Double) snap.child("_latLng").child
                            ("longitude").getValue();
                    LatLng latLng = new LatLng(latitude, longitude);
                    _model.addLostItem(name, type, description, user, latLng);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        _foundItems = _databaseRef.child("found items");
        _foundItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    String name = (String) snap.child("_name").getValue();
                    int type = ItemType.valueOf((String) snap.child("_type")
                            .getValue
                                    ()).ordinal();
                    String description = (String) snap.child("_description")
                            .getValue();
                    User user = snap.child("_user").getValue(User.class);
                    Double latitude = (Double) snap.child("_latLng").child
                            ("latitude").getValue();
                    Double longitude = (Double) snap.child("_latLng").child
                            ("longitude").getValue();
                    LatLng latLng = new LatLng(latitude, longitude);
                    _model.addFoundItem(name, type, description, user, latLng);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        System.out.println("WelcomeActivity.java/onCreate, here");
        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(intent);


    }

    /**
     * Creates an intent to display the login screen and starts it
     */
    private void onLoginButtonClick() {
        Intent intent = new Intent(WelcomeActivity.this, LoginActivity
                .class);
        startActivity(intent);
    }

    /**
     * Creates an intent to display the register screen and starts it
     */
    private void onRegisterButtonClick() {
        Intent intent = new Intent(WelcomeActivity.this,
                RegisterActivity.class);
        startActivity(intent);
    }
}
