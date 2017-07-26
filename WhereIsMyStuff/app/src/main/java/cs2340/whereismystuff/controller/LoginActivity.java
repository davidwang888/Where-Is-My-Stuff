package cs2340.whereismystuff.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cs2340.whereismystuff.R;
import cs2340.whereismystuff.model.Model;

/**
 * Represents the login screen
 *
 * Contains two text boxes for the user to enter their username and password in
 * and allows them to attempt to login or return to the welcome screen
 */
public class LoginActivity extends AppCompatActivity {
    /**
     * Text box user will enter username in
     */
    private EditText _usernameEmail;

    /**
     * Text box user will enter password in
     */
    private EditText _password;

    /**
     * Button user will click when they want to login
     */
    private Button _loginButton;

    /**
     * Button user will click when they want to return to the welcome screen
     */
    private Button _cancelButton;

    /**
     * Singular instance of model that the entire project uses to communicate
     * with the backend
     */
    private static Model model;

    private int _loginAttemps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _usernameEmail = (EditText) findViewById(R.id
                .loginUsernameEmailEditText);
        _password = (EditText) findViewById(R.id.loginPasswordEditText);
        _loginButton = (Button) findViewById(R.id.loginLoginButton);
        _cancelButton = (Button) findViewById(R.id.loginCancelButton);
        model = Model.getInstance();
        _loginAttemps = 0;
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginButtonClick();
            }
        });
        _cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelButtonClick();
            }
        });
    }

    /**
     * Upon the login button being clicked, checks the username and password the
     * user has entered and responds appropriately
     */
    private void onLoginButtonClick() {
        _loginAttemps++;
        int code = model.loginUser(_usernameEmail.getText().toString(),
                _password.getText().toString());
        if (_loginAttemps > 5) {
            Toast.makeText(LoginActivity.this, "User locked out. Contact "
                    + "admin wheres.my.stuff@gmail.com to regain access.", Toast
                    .LENGTH_SHORT)
                    .show();
        } else if (code == 0) {
            Toast.makeText(LoginActivity.this, "Login Successful!",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity
                    .class);
            startActivity(intent);
        } else if (code == 1) {
            Toast.makeText(LoginActivity.this, "Please Enter Username/Email "
                    + "And Password", Toast.LENGTH_SHORT).show();
        } else if (code == 2) {
            Toast.makeText(LoginActivity.this, "Username Not In "
                    + "Database", Toast.LENGTH_SHORT).show();
        } else if (code == 3) {
            Toast.makeText(LoginActivity.this, "Email Not In "
                    + "Database", Toast.LENGTH_SHORT).show();
        } else if (code == 4) {
            Toast.makeText(LoginActivity.this, "Username/Email Or Password "
                    + "Incorrect", Toast.LENGTH_SHORT).show();
        } else if (_loginAttemps >= 3) {
            Toast.makeText(LoginActivity.this, "User locked out. Contact "
                    + "admin where.is.my.stuff@gmail.com to regain access.",
                    Toast
                    .LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(LoginActivity.this, "ERROR", Toast
                    .LENGTH_SHORT).show();
        }
    }

    /**
     * Upon the cancel button being clicked, the page is changed to the
     * welcome screen
     */
    private void onCancelButtonClick() {
        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
        startActivity(intent);
    }
}
