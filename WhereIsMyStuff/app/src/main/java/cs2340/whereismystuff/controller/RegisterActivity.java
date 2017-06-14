package cs2340.whereismystuff.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cs2340.whereismystuff.R;
import cs2340.whereismystuff.model.Model;

public class RegisterActivity extends AppCompatActivity {
    private EditText _firstName;
    private EditText _lastName;
    private EditText _email;
    private EditText _username;
    private EditText _password1;
    private EditText _password2;
    private Button _registerButton;
    private Button _cancelButton;
    private static Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        _firstName = (EditText) findViewById(R.id.registerFirstNameEditText);
        _lastName = (EditText) findViewById(R.id.registerLastNameEditText);
        _email = (EditText) findViewById(R.id.registerEmailEditText);
        _username = (EditText) findViewById(R.id.registerUsernameEditText);
        _password1 = (EditText) findViewById(R.id.registerPasswordEditText1);
        _password2 = (EditText) findViewById(R.id.registerPasswordEditText2);
        _registerButton = (Button) findViewById(R.id.registerRegisterButton);
        _cancelButton = (Button) findViewById(R.id.registerCancelButton);
        model = Model.getInstance();
        _registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterButtonClick();
            }
        });
        _cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelButtonClick();
            }
        });
    }

    private void onRegisterButtonClick() {
        String firstName = _firstName.getText().toString();
        String lastName = _lastName.getText().toString();
        String email = _email.getText().toString();
        String username = _username.getText().toString();
        String password1 = _password1.getText().toString();
        String password2 = _password2.getText().toString();
        int code = model.addUser(firstName, lastName, email, username,
                password1, password2);
        if (code == 0) {
            Toast.makeText(RegisterActivity.this, "User Added!", Toast
                    .LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this,
                    MainActivity.class);
            intent.putExtra("usernameEmail", username);
            startActivity(intent);
        } else if (code == 1) {
            Toast.makeText(RegisterActivity.this, "Name Invalid", Toast
                    .LENGTH_SHORT).show();
        } else if (code == 2) {
            Toast.makeText(RegisterActivity.this, "Email Invalid",
                    Toast.LENGTH_SHORT).show();
        } else if (code == 3) {
            Toast.makeText(RegisterActivity.this, "Username Invalid",
                    Toast.LENGTH_SHORT).show();
        } else if (code == 4) {
            Toast.makeText(RegisterActivity.this, "Username Cannot Contain "
                    + "Whitespace Characters", Toast.LENGTH_SHORT).show();
        } else if (code == 5) {
            Toast.makeText(RegisterActivity.this, "Username Cannot Contain "
                    + "'@' Character", Toast.LENGTH_SHORT).show();
        } else if (code == 6) {
            Toast.makeText(RegisterActivity.this, "Password Invalid",
                    Toast.LENGTH_SHORT).show();
        } else if (code == 7) {
            Toast.makeText(RegisterActivity.this, "Passwords Do Not "
                    + "Match", Toast.LENGTH_SHORT).show();
        } else if (code == 8) {
            Toast.makeText(RegisterActivity.this, "Email Already in Database",
                    Toast.LENGTH_SHORT).show();
        } else if (code == 9) {
            Toast.makeText(RegisterActivity.this, "Username Already in "
                    + "Database", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(RegisterActivity.this, "User NOT Added",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void onCancelButtonClick() {
        Intent intent = new Intent(RegisterActivity.this,
                WelcomeActivity.class);
        startActivity(intent);
    }
}
