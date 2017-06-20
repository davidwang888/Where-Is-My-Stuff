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

/**
 * Represents the main screen of the app that the user is sent to after they
 * have logged in
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Displays text in the middle of the screen welcoming the user
     */
    private TextView _text;

    /**
     * Allows the user to logout
     */
    private Button _logoutButton;
    private Button _enterLostItemButton;
    private Button _enterFoundItemButton;
    private Button _searchItemsButton;

    /**
     * Singular instance of model that the entire project uses to communicate
     * with the backend
     */
    private static Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _text = (TextView) findViewById(R.id.mainTextView);
        _logoutButton = (Button) findViewById(R.id.mainLogoutButton);
        _enterLostItemButton = (Button) findViewById(R.id.mainEnterLostItemButton);
        _enterFoundItemButton = (Button) findViewById(R.id
                .mainEnterFoundButton);
        _searchItemsButton = (Button) findViewById(R.id.mainSearchItemsButton);
        model = Model.getInstance();
        String _name = model.getName();
        _text.setText("Welcome " + _name + "!");
        _logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogoutButtonClick();
            }
        });
        _enterLostItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEnterLostItemButtonClick();
            }
        });
        _enterFoundItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEnterFoundItemButtonClick();
            }
        });
        _searchItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchItemsButtonClick();
            }
        });
    }

    /**
     * Creates an intent to return the user to the welcome screen, starts it,
     * and displays a toast indicating that the user has logged out successfully
     */
    private void onLogoutButtonClick() {
        Toast.makeText(MainActivity.this, "Logout Successful!", Toast
                .LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, WelcomeActivity
                .class);
        startActivity(intent);
    }

    private void onEnterLostItemButtonClick() {
        Intent intent = new Intent(MainActivity.this, EnterLostItemActivity.class);
        startActivity(intent);
    }

    private void onEnterFoundItemButtonClick(){
        Intent intent = new Intent(MainActivity.this, EnterFoundItemActivity
                .class);
        startActivity(intent);
    }

    private void onSearchItemsButtonClick() {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }
}
