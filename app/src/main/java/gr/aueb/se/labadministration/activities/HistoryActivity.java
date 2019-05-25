package gr.aueb.se.labadministration.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import gr.aueb.se.labadministration.R;
import gr.aueb.se.labadministration.domain.lab.Session;

public class HistoryActivity extends AppCompatActivity{

    SearchView historySearchView;
    ListView resultListView;
    ArrayAdapter<Session> adapter;
    ArrayList<Session> resultsArrayList = new ArrayList<>();
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
        computerRadioButton = findViewById(R.id.computerRadioButton);
        userRadioButton = findViewById(R.id.userRadioButton);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , resultsArrayList);
        resultListView.setAdapter(adapter);

        historySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
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
                startActivity(new Intent(this, HistoryActivity.class));
                break;
            case R.id.nav_patterns:
                startActivity(new Intent(this, LabActivity.class));
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
    }

    public String getOption(){
        int selectedId = radioGroup.getCheckedRadioButtonId();
        enabledRadioButton = findViewById(selectedId);
        return enabledRadioButton.getText().toString();
    }

    public String getId(){
        return historySearchView.getQuery().toString();
    }

    public void showError(String error){
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    public void showResult(ArrayList<Session> sessions){
        if (sessions.size() == 0){
            Toast.makeText(getApplicationContext(), "There is no session.", Toast.LENGTH_SHORT).show();
            return;
        }
        resultsArrayList.addAll(sessions);
    }
}
