package cs2340.whereismystuff.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import cs2340.whereismystuff.R;
import cs2340.whereismystuff.model.Model;

/**
 * Represents the screen to enter a found item
 *
 * Contains a text box for the user to enter the item's name, a spinner for
 * the item's type, and a text box for the item's description and allows them
 * to add a new found item
 */
public class EnterFoundItemActivity extends AppCompatActivity {
    /**
     * Text box user will enter item name in
     */
    private EditText _itemNameEditText;

    /**
     * Spinner user will use to select item's type
     */
    private Spinner _itemTypeSpinner;

    /**
     * Text box user will enter item description in
     */
    private EditText _itemDescription;

    /**
     * Button user will click when they want to add item
     */
    private Button _enterItemButton;

    /**
     * Singular instance of model that the entire project uses to communicate
     * with the backend
     */
    private static Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_found_item);
        model  = Model.getInstance();
        _itemNameEditText = (EditText) findViewById(R.id
                .enterFoundItemNameEditText);
        _itemTypeSpinner = (Spinner) findViewById(R.id
                .enterFoundItemTypeSpinner);
        _itemDescription = (EditText) findViewById(R.id
                .enterFoundItemDescriptionEditText);
        _enterItemButton = (Button) findViewById(R.id
                .enterFoundItemEnterItemButton);
        _enterItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEnterItemClick();
            }
        });
        _itemTypeSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout
                .simple_spinner_item, model.getItemTypes()));
    }

    /**
     * Upon the enter item button clicked, item is entered and screen is
     * changed to the view found items screen
     */
    private void onEnterItemClick() {
        String name = _itemNameEditText.getText().toString();
        int type = _itemTypeSpinner.getSelectedItemPosition();
        String description = _itemDescription.getText().toString();
        model.addFoundItem(name, type, description, model.getCurrentUser());
        Intent intent = new Intent(EnterFoundItemActivity.this,
                ViewFoundItemsActivity.class);
        startActivity(intent);
    }
}
