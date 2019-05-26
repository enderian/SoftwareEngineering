package gr.aueb.se.labadministration.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SearchView;

import java.util.ArrayList;

import gr.aueb.se.labadministration.R;
import gr.aueb.se.labadministration.domain.lab.Session;
import gr.aueb.se.labadministration.services.HistoryService;

public class HistoryActivity extends AppCompatActivity{

    private SearchView historySearchView;
    private ListView resultListView;

    private ArrayAdapter<Session> adapter;
    private ArrayList<Session> resultsArrayList = new ArrayList<>();
    private RadioGroup radioGroup;

    private HistoryService service;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            HistoryActivity.this.service = ((HistoryService.HistoryServiceBinder) service).getService();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            HistoryActivity.this.service = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        makeActionBar();
        bindService(new Intent(this, HistoryService.class), serviceConnection, Context.BIND_AUTO_CREATE);

        historySearchView = findViewById(R.id.historySearchView);
        resultListView = findViewById(R.id.resultListView);
        radioGroup = findViewById(R.id.radioGroup);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , resultsArrayList);
        resultListView.setAdapter(adapter);

        historySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                resultsArrayList.clear();
                if (s.isEmpty()) {
                    resultsArrayList.addAll(service.allSessions());
                    adapter.notifyDataSetChanged();
                    return true;
                }
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.userRadioButton: {
                        resultsArrayList.addAll(service.findSessionsByUser(s));
                        adapter.notifyDataSetChanged();
                        break;
                    }
                    case R.id.computerRadioButton: {
                        resultsArrayList.addAll(service.findSessionsByComputer(s));
                        adapter.notifyDataSetChanged();
                        break;
                    }
                    default: return false;
                }
                return true;
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
            case R.id.nav_labs:
                startActivity(new Intent(this, LabActivity.class));
                break;
            default: // R.id.nav_configuration
                startActivity(new Intent(this, ConfigurationActivity.class));
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
