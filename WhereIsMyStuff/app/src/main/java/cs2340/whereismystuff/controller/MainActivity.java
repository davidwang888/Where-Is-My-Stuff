package cs2340.whereismystuff.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cs2340.whereismystuff.R;
import cs2340.whereismystuff.model.Model;

public class MainActivity extends AppCompatActivity {
    private TextView _text;
    private Button _logoutButton;
    private static Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _text = (TextView) findViewById(R.id.mainTextView);
        _logoutButton = (Button) findViewById(R.id.mainLogoutButton);
        model = Model.getInstance();
        Bundle extras = getIntent().getExtras();
        if (extras.containsKey("usernameEmail")) {
            String _usernameEmail = extras.getString("usernameEmail");
            String _name = model.getName(_usernameEmail);
            _text.setText("Welcome " + _name + "!");
        }
        _logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogoutButtonClick();
            }
        });
    }

    private void onLogoutButtonClick() {
        Toast.makeText(MainActivity.this, "Logout Successful!", Toast
                .LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, WelcomeActivity
                .class);
        startActivity(intent);
    }
}
