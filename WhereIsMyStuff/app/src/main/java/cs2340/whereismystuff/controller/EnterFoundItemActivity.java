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
import cs2340.whereismystuff.model.ItemType;
import cs2340.whereismystuff.model.Model;

public class EnterFoundItemActivity extends AppCompatActivity {
    private EditText _itemNameEditText;
    private Spinner _itemTypeSpinner;
    private EditText _itemDescription;
    private Button _enterItemButton;

    private static Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_lost_item);
        model  = Model.getInstance();
        _itemNameEditText = (EditText) findViewById(R.id
                .enterFoundItemNameEditText);
        _itemTypeSpinner = (Spinner) findViewById(R.id
                .enterFoundItemTypeSpinner);
        _itemDescription = (EditText) findViewById(R.id
                .enterFoundItemDescriptionEditText);
        _enterItemButton = (Button) findViewById(R.id.enterFoundItemButton);
        _enterItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEnterItemClick();
            }
        });
        _itemTypeSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ItemType.values()));
    }

    private void onEnterItemClick() {
        String name = _itemNameEditText.getText().toString();
        ItemType type = (ItemType) _itemTypeSpinner.getSelectedItem();
        String description = _itemDescription.getText().toString();
        model.addFoundItem(name, type, description, model.getCurrentUser());
        Intent intent = new Intent(EnterFoundItemActivity.this,
                ViewFoundItemsActivity.class);
        startActivity(intent);
    }
}
