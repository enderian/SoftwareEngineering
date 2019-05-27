package gr.aueb.se.labadministration.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
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
import gr.aueb.se.labadministration.activities.NewConfigurationActivity;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.services.ConfigurationService;

/**
 * The fragment that registers a TerminalConfiguration
 */
public class ConfigurationFragment extends Fragment {

    private List<TerminalConfiguration> configurationList = new ArrayList<>();
    private ArrayAdapter<TerminalConfiguration> adapter;

    private ConfigurationService service;

    /**
     * Method that initiates connection with configuration service
     */
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ConfigurationFragment.this.service = ((ConfigurationService.ConfigurationServiceBinder) service).getService();
            configurationList.clear();
            configurationList.addAll(ConfigurationFragment.this.service.listAllConfigs());
            adapter.notifyDataSetChanged();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            ConfigurationFragment.this.service = null;
        }
    };

    /**
     * Default Android Methods
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_configuration, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, configurationList);
        ListView listView = getView().findViewById(R.id.configurations);
        listView.setAdapter(adapter);

        getActivity().bindService(new Intent(getContext(), ConfigurationService.class), serviceConnection, Context.BIND_AUTO_CREATE);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            TerminalConfiguration configuration = configurationList.get(position);
            Intent intent = new Intent(getContext(), NewConfigurationActivity.class);
            intent.putExtra("configuration", configuration);
            startActivity(intent);
        });

        FloatingActionButton button = getView().findViewById(R.id.fab);
        button.setOnClickListener(click -> {
            startActivity(new Intent(getContext(), NewConfigurationActivity.class));
        });
    }

}


