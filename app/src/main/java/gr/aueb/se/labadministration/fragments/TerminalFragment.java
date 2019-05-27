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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import gr.aueb.se.labadministration.R;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.services.LabService;

public class TerminalFragment extends DialogFragment {

    private LabService labService;
    private String terminalName;
    private TextView terminalTitle, terminalStatus;
    private Terminal terminal;
    private ImageButton shutdown, restart, signout;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            TerminalFragment.this.labService = ((LabService.LabServiceBinder) iBinder).getService();
            terminal = labService.findTerminal(terminalName);

            terminalTitle.setText(terminal.getName());
            terminalStatus.setText(terminal.getStatus().toString());

            if(labService.isTerminalOffline(terminal) || labService.isTerminalInMaintenance(terminal)){
                shutdown.setVisibility(View.GONE);
                restart.setVisibility(View.GONE);
                signout.setVisibility(View.GONE);
            }

            if(labService.isTerminalInUse(terminal)){
                signout.setVisibility(View.VISIBLE);
            }
            else signout.setVisibility(View.GONE);
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
        shutdown = getView().findViewById(R.id.terminal_shut_off);
        restart = getView().findViewById(R.id.terminal_reboot);
        signout = getView().findViewById(R.id.terminal_sign_out);

        shutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                labService.terminalAction(terminalName, Terminal.TerminalStatus.OFFLINE);

                refresh();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                labService.terminalAction(terminalName, Terminal.TerminalStatus.IN_MAINTENANCE);

                labService.terminalAction(terminalName, Terminal.TerminalStatus.AVAILABLE);

                refresh();
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                labService.terminalAction(terminalName, Terminal.TerminalStatus.AVAILABLE);

                refresh();
            }
        });
    }

    private void refresh(){
        FragmentTransaction manager = getFragmentManager().beginTransaction();
        manager.detach(this);
        TerminalFragment terminalFragment = new TerminalFragment();
        Bundle bundle = new Bundle();
        bundle.putString("terminal_name", terminalName);
        terminalFragment.setArguments(bundle);
        manager.add(terminalFragment, "Terminal");
        manager.commit();
    }
}
