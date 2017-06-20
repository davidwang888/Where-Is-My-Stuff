package cs2340.whereismystuff.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import cs2340.whereismystuff.R;

public class SearchActivity extends AppCompatActivity {
    boolean _foundItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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
