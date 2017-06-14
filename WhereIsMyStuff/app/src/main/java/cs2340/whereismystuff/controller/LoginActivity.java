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

public class LoginActivity extends AppCompatActivity {
    private EditText _usernameEmail;
    private EditText _password;
    private Button _loginButton;
    private Button _cancelButton;
    private static Model model;

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

    private void onLoginButtonClick() {
        int code = model.loginUser(_usernameEmail.getText().toString(),
                _password.getText().toString());
        if (code == 0) {
            Toast.makeText(LoginActivity.this, "Login Successful!",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity
                    .class);
            intent.putExtra("usernameEmail", _usernameEmail.getText()
                    .toString());
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
        } else {
            Toast.makeText(LoginActivity.this, "ERROR", Toast
                    .LENGTH_SHORT).show();
        }
    }

    private void onCancelButtonClick() {
        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
        startActivity(intent);
    }
}
