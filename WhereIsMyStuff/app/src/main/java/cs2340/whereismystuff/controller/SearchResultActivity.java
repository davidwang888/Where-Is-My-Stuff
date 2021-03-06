package cs2340.whereismystuff.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cs2340.whereismystuff.R;

/**
 * Represents the screen that shows user the result of the search
 */
public class SearchResultActivity extends AppCompatActivity {
    /**
     * The text box with the description of the found item
     */
    private TextView _itemDescription;

    /**
     * The button users will push to go back to the welcome screen
     */
    private Button _backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        _itemDescription = (TextView) findViewById(R.id
                .searchResultDescriptionTextView);
        _backButton = (Button) findViewById(R.id.searchResultHomeButton);
        Bundle extra = getIntent().getExtras();
        if (extra.containsKey("description")) {
            _itemDescription.setText(extra.getString("description"));
        }
        _backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackButtonClick();
            }
        });

    }

    /**
     * Upon the back button being clicked, the user will go back to the
     * welcome screen
     */
    private void onBackButtonClick() {
        Intent intent = new Intent(SearchResultActivity.this, MainActivity
                .class);
        startActivity(intent);
    }
}
