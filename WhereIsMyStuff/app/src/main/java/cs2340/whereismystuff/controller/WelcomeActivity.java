package cs2340.whereismystuff.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cs2340.whereismystuff.R;

public class WelcomeActivity extends AppCompatActivity {
    private Button _loginButton;
    private Button _registerButton;

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
    }

    private void onLoginButtonClick() {
        Intent intent = new Intent(WelcomeActivity.this, LoginActivity
                .class);
        startActivity(intent);
    }

    private void onRegisterButtonClick() {
        Intent intent = new Intent(WelcomeActivity.this,
                RegisterActivity.class);
        startActivity(intent);
    }
}
