package gr.aueb.se.labadministration.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gr.aueb.se.labadministration.R;
import gr.aueb.se.labadministration.dao.LaboratoryDAO;
import gr.aueb.se.labadministration.domain.lab.Laboratory;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.memorydao.LaboratoryDAOMemory;
import gr.aueb.se.labadministration.utilities.ExpandableListAdapter;

public class LabFragment extends Fragment {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listHeader;
    private HashMap<String, List<String>>  listHashMap;
    private TextView listItem;

    @Override
    public void onStart() {
        super.onStart();
        listView = getView().findViewById(R.id.labExpandableListView);
        initData();
        listAdapter = new ExpandableListAdapter(getContext(), listHeader, listHashMap);
        listView.setAdapter(listAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lab, container, false);
    }

    public void initData(){
        listHeader = new ArrayList<>();
        listHashMap = new HashMap<>();

        LaboratoryDAO laboratoryDAO = new LaboratoryDAOMemory();
        List<Laboratory> labs = laboratoryDAO.listAll();
        for(Laboratory lab: labs){
            listHeader.add(lab.getName());
            List<String> terminals = new ArrayList<>();
            for(Terminal t: lab.getTerminals()){
                terminals.add(t.getName());
            }
            listHashMap.put(listHeader.get(listHeader.size()-1), terminals);
        }

    }
}
