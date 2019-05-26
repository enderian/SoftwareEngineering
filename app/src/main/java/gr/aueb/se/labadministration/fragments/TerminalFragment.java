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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import gr.aueb.se.labadministration.R;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.services.LabService;

public class TerminalFragment extends DialogFragment {

    private LabService labService;
    private String terminalName;
    private TextView terminalTitle, terminalStatus;
    private Terminal terminal;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            TerminalFragment.this.labService = ((LabService.LabServiceBinder) iBinder).getService();
            terminal = labService.findTerminal(terminalName);

            terminalTitle.setText(terminal.getName());
            terminalStatus.setText(terminal.getStatus().toString());
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            TerminalFragment.this.labService = null;
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        terminalName = bundle.getString("terminal_name");

        return inflater.inflate(R.layout.fragment_terminal, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().bindService(new Intent(getContext(), LabService.class), serviceConnection, Context.BIND_AUTO_CREATE);

        terminalTitle = getView().findViewById(R.id.terminal_name);
        terminalStatus = getView().findViewById(R.id.terminal_status);

    }
}
