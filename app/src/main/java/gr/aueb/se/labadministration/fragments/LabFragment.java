package gr.aueb.se.labadministration.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gr.aueb.se.labadministration.R;
import gr.aueb.se.labadministration.dao.LaboratoryDAO;
import gr.aueb.se.labadministration.domain.lab.Laboratory;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.domain.schedule.DaySchedule;
import gr.aueb.se.labadministration.memorydao.LaboratoryDAOMemory;
import gr.aueb.se.labadministration.services.LabService;
import gr.aueb.se.labadministration.utilities.ExpandableListAdapter;

public class LabFragment extends Fragment {

    private ExpandableListView listViewComputers, listViewShedules;
    private ExpandableListAdapter listAdapterComputers, listAdapterShedules;
    private List<String> listComputers, listShedules;
    private HashMap<String, List<String>>  listHashMapComputers, listHashMapShedules;
    private RadioGroup labsRadioGroup;

    private LabService service;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LabFragment.this.service = ((LabService.LabServiceBinder) iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            LabFragment.this.service = null;
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        getActivity().bindService(new Intent(getContext(), LabService.class), serviceConnection, Context.BIND_AUTO_CREATE);

        listComputers = new ArrayList<>();
        listHashMapComputers = new HashMap<>();

        listShedules= new ArrayList<>();
        listHashMapShedules = new HashMap<>();

        labsRadioGroup = getView().findViewById(R.id.labsRadioGroup);
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
        listViewComputers = getView().findViewById(R.id.labListComputers);
        listAdapterComputers = new ExpandableListAdapter(getContext(), listComputers, listHashMapComputers);
        listViewComputers.setAdapter(listAdapterComputers);

        // Computer List Configuration
        listViewShedules = getView().findViewById(R.id.labListShedules);
        listAdapterShedules = new ExpandableListAdapter(getContext(), listShedules, listHashMapShedules);
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lab, container, false);
    }
}
