package cs2340.whereismystuff.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cs2340.whereismystuff.R;
import cs2340.whereismystuff.model.Model;

public class ViewFoundItemsActivity extends AppCompatActivity {
    private ListView _viewFoundItemsListView;
    private static Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lost_items);
        model = Model.getInstance();

        _viewFoundItemsListView = (ListView) findViewById(R.id
                .viewFoundItemsListView);
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,
                model.getFoundItems());
        _viewFoundItemsListView.setAdapter(adapter);

    }
}
