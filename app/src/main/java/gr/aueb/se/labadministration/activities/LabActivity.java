package gr.aueb.se.labadministration.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gr.aueb.se.labadministration.R;
import gr.aueb.se.labadministration.dao.LaboratoryDAO;
import gr.aueb.se.labadministration.domain.lab.Laboratory;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.memorydao.LaboratoryDAOMemory;
import gr.aueb.se.labadministration.utilities.ExpandableListAdapter;

public class LabActivity extends AppCompatActivity {

    private ExpandableListView listViewLabs, listViewComputers, listViewShedules;
    private ExpandableListAdapter listAdapterLabs, listAdapterComputers, listAdapterShedules;
    private List<String> listLabs, listComputers, listShedules;
    private HashMap<String, List<String>>  listHashMapLabs, listHashMapComputers, listHashMapShedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);
        makeActionBar();

        // Labs List Configuration
        listViewLabs = findViewById(R.id.labListParrent);
        initListHashLabs();
        listAdapterLabs = new ExpandableListAdapter(this, listLabs, listHashMapLabs);
        listViewLabs.setAdapter(listAdapterLabs);



    }

    void initListComputers(){
        listComputers = new ArrayList<>();
        listHashMapComputers = new HashMap<>();

    }

    void initListHashShedule(){
        listShedules = new ArrayList<>();
        listHashMapShedules = new HashMap<>();

    }

    void initListHashLabs(){
        listLabs = new ArrayList<>();
        listHashMapLabs = new HashMap<>();

        LaboratoryDAO laboratoryDAO = new LaboratoryDAOMemory();
        List<Laboratory> labs = laboratoryDAO.listAll();
        for(Laboratory lab: labs){
            listLabs.add(lab.getName());
            List<String> terminals = new ArrayList<>();
            for(Terminal t: lab.getTerminals()){
                terminals.add(t.getName());
            }
            listHashMapLabs.put(listLabs.get(listHashMapLabs.size()-1), terminals);
        }

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
