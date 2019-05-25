package gr.aueb.se.labadministration.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import gr.aueb.se.labadministration.R;

public class HistoryProjection extends AppCompatActivity {

    SearchView historySearchView;
    ListView resultListView;
    ArrayAdapter<String> adapter;
    ArrayList<String> resultsArrayList = new ArrayList<>();
    RadioGroup radioGroup;
    RadioButton computerRadioButton;
    RadioButton userRadioButton;
    RadioButton enabledRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_projection);

        makeActionBar();

        historySearchView = findViewById(R.id.historySearchView);
        resultListView = findViewById(R.id.resultListView);
        radioGroup = findViewById(R.id.radioGroup);
        computerRadioButton = findViewById(R.id.userRadioButton);
        userRadioButton = findViewById(R.id.userRadioButton);

        resultsArrayList.add("Test");
        resultsArrayList.add("Kostas");
        resultsArrayList.add("Kokoras");
        resultsArrayList.add("test");
        resultsArrayList.add("stop");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , resultsArrayList);
        resultListView.setAdapter(adapter);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                enabledRadioButton = findViewById(selectedId);
                Toast.makeText(getApplicationContext(), enabledRadioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });


        historySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        historySearchView.setIconified(false);
        historySearchView.clearFocus();

    }

    // this method shows menu at main_activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //this method is called when user press any button of navigation bar(wifi, cellular, refresh)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_history:
                startActivity(new Intent(this, HistoryProjection.class));
                break;
            case R.id.nav_patterns:
                startActivity(new Intent(this, LabProjection.class));
                break;
            default:


        }
        return super.onOptionsItemSelected(item);
    }

    // makes the action bar.
    private void makeActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.app_icon);
        actionBar.setDisplayUseLogoEnabled(true);// display app_icon.
        actionBar.setDisplayShowHomeEnabled(true);// display back button.
    }
}
