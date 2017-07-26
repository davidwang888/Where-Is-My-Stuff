package cs2340.whereismystuff.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import cs2340.whereismystuff.R;
import cs2340.whereismystuff.model.Item;
import cs2340.whereismystuff.model.Model;

/**
 * Represents the screen tha shows the user the list of all the lost items
 */
public class ViewLostItemsActivity extends AppCompatActivity {
    /**
     * A list view of all the items
     */
    private ListView _viewLostItemsListView;

    /**
     * The button users will push to go back to the welcome screen
     */
    private Button _homeButton;

    /**
     * Singular instance of model that the entire project uses to communicate
     * with the backend
     */
    private static Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lost_items);
        _homeButton = (Button) findViewById(R.id.viewLostItemsHomeButton);
        model = Model.getInstance();

        _viewLostItemsListView = (ListView) findViewById(R.id.viewLostItemsListView);
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,
                model.getLostItems());
        _viewLostItemsListView.setAdapter(adapter);
        _homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHomeButtonClick();
            }
        });

        _viewLostItemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemClicked(position);
            }
        });
    }

    /**
     * Upon the home button being clicked, the screen will change to the
     * welcome screen
     */
    private void onHomeButtonClick() {
        Intent intent = new Intent(ViewLostItemsActivity.this, MainActivity
                .class);
        startActivity(intent);
    }

    private void onItemClicked(int position) {
        model.setCurrentItem((Item) _viewLostItemsListView
                .getItemAtPosition(position));
        Intent intent = new Intent(ViewLostItemsActivity.this,
                MessageActivity.class);
        intent.putExtra("isLostItem", true);
        startActivity(intent);
    }
}
