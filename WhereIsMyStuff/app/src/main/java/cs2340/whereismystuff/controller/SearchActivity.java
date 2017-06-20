package cs2340.whereismystuff.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import cs2340.whereismystuff.R;
import cs2340.whereismystuff.model.Model;

public class SearchActivity extends AppCompatActivity {
    boolean _foundItem;
    EditText _itemName;
    Button _searchButton;
    Button _backButton;
    private static Model model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        _itemName = (EditText) findViewById(R.id.searchItemNameEditText);
        _searchButton = (Button) findViewById(R.id.searchSearchButton);
        _backButton = (Button) findViewById(R.id.searchBackButton);
        model = Model.getInstance();
        _searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchButtonClick();
            }
        });
        _backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackButtonClick();
            }
        });
    }

    private void onSearchButtonClick() {
        if (model.search(_foundItem, _itemName.getText().toString())) {
            Intent intent = new Intent(SearchActivity.this,
                    SearchResultActivity.class);
            intent.putExtra("description", model.searchResult(_foundItem,
                    _itemName.getText().toString()));
            startActivity(intent);
        } else {
            Toast.makeText(SearchActivity.this, "Item Not Found", Toast
                    .LENGTH_SHORT).show();
        }
    }

    private void onBackButtonClick() {
        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onRadioButtonClick(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.foundRadioButton:
                if (checked)
                    _foundItem = true;
                break;
            case R.id.lostRadioButton:
                if (checked)
                    _foundItem = false;
                break;
        }
    }
}
