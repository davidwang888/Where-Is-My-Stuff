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

/**
 * Represents the screen that allows the user to search for an item by
 * entering whether the item is a lost or found item and the item's name
 *
 * Contains a radio button for selecting whether the item is lost or found
 * and a text box for the user to enter the item name
 */
public class SearchActivity extends AppCompatActivity {
    /**
     * Text box user will enter the item's name
     */
    EditText _itemName;

    /**
     * The boolean representing whether the user is an admin or not
     */
    boolean _foundItem;

    /**
     * Button user will click when they want to search for the item
     */
    Button _searchButton;

    /**
     * Button user will click when they want to cancel the search and go back
     * to the main screen
     */
    Button _cancelButton;

    /**
     * Singular instance of model that the entire project uses to communicate
     * with the backend
     */
    private static Model model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        _itemName = (EditText) findViewById(R.id.searchNameEditText);
        _searchButton = (Button) findViewById(R.id.searchSearchButton);
        _cancelButton = (Button) findViewById(R.id.searchCancelButton);
        model = Model.getInstance();
        _searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchButtonClick();
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
     * Upon the search button being clicked, the item is searched for. If the
     * item is found, a new screen with the item information is shown. If the
     * item is not found, shows a warning that the item is not found
     */
    private void onSearchButtonClick() {
        if (model.searchFound(_foundItem, _itemName.getText().toString())) {
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

    /**
     * Upon the cancel button being clicked, the user is brought back to the
     * main screen
     */
    private void onCancelButtonClick() {
        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Upon the radio button being changed, the found item status change will be
     * reflected in the instance data
     *
     * @param view the found / lost radio button
     */
    public void onRadioButtonClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.searchFoundRadioButton:
                if (checked)
                    _foundItem = true;
                break;
            case R.id.searchLostRadioButton:
                if (checked)
                    _foundItem = false;
                break;
        }
    }
}
