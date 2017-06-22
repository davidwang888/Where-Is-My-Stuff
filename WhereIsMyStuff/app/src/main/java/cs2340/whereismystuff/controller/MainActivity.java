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
 *
 * Contains multiple buttons to allow the user to decide the next course of
 * action
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Displays text in the middle of the screen welcoming the user
     */
    private TextView _text;

    /**
     * Button user will click when they want to logout
     */
    private Button _logoutButton;

    /**
     * Button the user will click when they want to enter a lost item
     */
    private Button _enterLostItemButton;

    /**
     * Button the user will click when they want to enter a found item
     */
    private Button _enterFoundItemButton;

    /**
     * Button the user will click when they want to search for an item
     */
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
        _text = (TextView) findViewById(R.id.mainTitleTextView);
        _logoutButton = (Button) findViewById(R.id.mainLogoutButton);
        _enterLostItemButton = (Button) findViewById(R.id
                .mainEnterLostItemButton);
        _enterFoundItemButton = (Button) findViewById(R.id
                .mainEnterFoundItemButton);
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

    /**
     * Upon the enter lost item button clicked, the screen is changed to the
     * enter lost activity screen
     */
    private void onEnterLostItemButtonClick() {
        Intent intent = new Intent(MainActivity.this, EnterLostItemActivity
                .class);
        startActivity(intent);
    }

    /**
     * Upon the enter found item button clicked, the screen is changed to the
     * enter found item screen
     */
    private void onEnterFoundItemButtonClick(){
        Intent intent = new Intent(MainActivity.this, EnterFoundItemActivity
                .class);
        startActivity(intent);
    }

    /**
     * Upon the search items button clicked, the screen is changed to the
     * search item
     */
    private void onSearchItemsButtonClick() {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }
}
