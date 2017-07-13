package cs2340.whereismystuff.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import cs2340.whereismystuff.R;
import cs2340.whereismystuff.model.Model;

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

    /**
     * Singular instance of model that the entire project uses to communicate
     * with the backend
     */
    private Model _model = Model.getInstance();

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
        _model.setUp();
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
