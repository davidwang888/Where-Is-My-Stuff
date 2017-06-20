package cs2340.whereismystuff.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cs2340.whereismystuff.R;

public class SearchResultActivity extends AppCompatActivity {
    TextView _itemDescription;
    Button _backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        _itemDescription = (TextView) findViewById(R.id
                .searchItemDescriptionTextView);
        _backButton = (Button) findViewById(R.id.searchBackButton);
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

    private void onBackButtonClick() {
        Intent intent = new Intent(SearchResultActivity.this, SearchActivity
                .class);
        startActivity(intent);
    }
}
