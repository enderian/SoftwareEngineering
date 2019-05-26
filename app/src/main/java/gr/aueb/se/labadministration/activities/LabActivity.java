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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gr.aueb.se.labadministration.R;
import gr.aueb.se.labadministration.dao.LaboratoryDAO;
import gr.aueb.se.labadministration.domain.lab.Laboratory;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.domain.schedule.DaySchedule;
import gr.aueb.se.labadministration.memorydao.LaboratoryDAOMemory;
import gr.aueb.se.labadministration.utilities.ExpandableListAdapter;

public class LabActivity extends AppCompatActivity {

    private ExpandableListView listViewComputers, listViewShedules;
    private ExpandableListAdapter listAdapterComputers, listAdapterShedules;
    private List<String> listComputers, listShedules;
    private HashMap<String, List<String>>  listHashMapComputers, listHashMapShedules;
    private RadioGroup labsRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);
        makeActionBar();


        listComputers = new ArrayList<>();
        listHashMapComputers = new HashMap<>();

        listShedules= new ArrayList<>();
        listHashMapShedules = new HashMap<>();

        labsRadioGroup = findViewById(R.id.labsRadioGroup);
        labsRadioGroup.setOnCheckedChangeListener( (group, checkedId) ->
        {
            RadioButton checkedRadioButton = group.findViewById(checkedId);
            boolean isChecked = checkedRadioButton.isChecked();
            if (isChecked)
            {
                updateComputers(checkedRadioButton.getText().toString());
                updateShedule(checkedRadioButton.getText().toString());
                listAdapterComputers.notifyDataSetChanged();
                listAdapterShedules.notifyDataSetChanged();
            }
        });

        updateComputers("Lab1");
        updateShedule("Lab1");

        // Computer List Configuration
        listViewComputers = findViewById(R.id.labListComputers);
        listAdapterComputers = new ExpandableListAdapter(this, listComputers, listHashMapComputers);
        listViewComputers.setAdapter(listAdapterComputers);

        // Computer List Configuration
        listViewShedules = findViewById(R.id.labListShedules);
        listAdapterShedules = new ExpandableListAdapter(this, listShedules, listHashMapShedules);
        listViewShedules.setAdapter(listAdapterShedules);

        labsRadioGroup.check(R.id.lab1RadioButton);

    }

    void updateComputers(String labName){

        listComputers.clear();
        listHashMapComputers.clear();

        LaboratoryDAO laboratoryDAO = new LaboratoryDAOMemory();
        Laboratory lab = laboratoryDAO.findByName(labName);
        listComputers.add(lab.getName());
        List<String> terminals = new ArrayList<>();
        for(Terminal t: lab.getTerminals()){
            terminals.add(t.getName());
        }
        listHashMapComputers.put(lab.getName(), terminals);
    }


    void updateShedule(String labName){
        listShedules.clear();
        listHashMapShedules.clear();

        LaboratoryDAO laboratoryDAO = new LaboratoryDAOMemory();
        Laboratory lab = laboratoryDAO.findByName(labName);
        listShedules.add(lab.getName());
        ArrayList<String> schedules = new ArrayList<>();
        for(DaySchedule s: lab.getSchedule()){
            schedules.add("Day: " + s.getDay());
        }
        listHashMapShedules.put(lab.getName(), schedules);
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
