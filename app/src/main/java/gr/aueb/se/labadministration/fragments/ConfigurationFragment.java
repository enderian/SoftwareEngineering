package gr.aueb.se.labadministration.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.se.labadministration.R;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;


public class ConfigurationFragment extends Fragment {

    private List<TerminalConfiguration> configurationList = new ArrayList<>();

    /*private ConfigurationService configurationService;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ConfigurationFragment.this.service = ((HistoryService.HistoryServiceBinder) service).getService();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            ConfigurationFragment.this.service = null;
        }
    };*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_configuration, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        ArrayAdapter configurationAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, configurationList);
        ListView listView = getView().findViewById(R.id.configurations);
        listView.setAdapter(configurationAdapter);

        FloatingActionButton button = getView().findViewById(R.id.fab);
        button.setOnClickListener(click -> {
            startActivity(new Intent(getContext(), NewConfigurationActivity.class));
        });
    }
}


