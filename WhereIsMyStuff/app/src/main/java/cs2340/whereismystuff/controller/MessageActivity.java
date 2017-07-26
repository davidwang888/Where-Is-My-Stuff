package cs2340.whereismystuff.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cs2340.whereismystuff.R;
import cs2340.whereismystuff.model.Model;

public class MessageActivity extends AppCompatActivity {
    private TextView _title_text_view;
    private EditText _message_edit_text;
    private Button _send_button;
    private Button _home_button;
    private static Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        _title_text_view = (TextView) findViewById(R.id
                .message_title_text_view);
        _message_edit_text = (EditText) findViewById(R.id
                .message_message_edit_text);
        _send_button = (Button) findViewById(R.id.message_send_button);
        _home_button = (Button) findViewById(R.id.message_home_button);
        model = Model.getInstance();

        setUp();

        _send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSendButtonClick();
            }
        });
        _home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHomeButtonClick();
            }
        });
    }

    private void setUp() {
        boolean lostItem = getIntent().getExtras().getBoolean("isLostItem");
        if (lostItem) {
            _title_text_view.setText("Send a message to finder of lost item " +
                    model.getCurrentItem().getName());
        } else {
            _title_text_view.setText("Send a message to finder of found item " +
                    model.getCurrentItem().getName());
        }
    }

    private void onSendButtonClick() {
        model.sendMessage(_message_edit_text.getText().toString());
        Toast.makeText(MessageActivity.this, "Admins notified of message. The"
                + " admins will contact the user.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MessageActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Upon the home button being clicked, the screen will change to the
     * welcome screen
     */
    private void onHomeButtonClick() {
        Intent intent = new Intent(MessageActivity.this, MainActivity
                .class);
        startActivity(intent);
    }
}
