package gr.aueb.se.labadministration.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.UnknownHostException;

import gr.aueb.se.labadministration.R;
import gr.aueb.se.labadministration.domain.builder.TerminalBuilder;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.domain.lab.Laboratory;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.services.LabService;

public class NewTerminalActivity extends AppCompatActivity {

    private LabService labService;
    private EditText term_name, term_hostname, term_ip, term_posx, term_posy;
    private Spinner term_config, lab_select;
    private TerminalConfiguration configuration;
    private Laboratory laboratory;
    private Button term_sumbit;
    private int posx, posy;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            NewTerminalActivity.this.labService = ((LabService.LabServiceBinder) iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            NewTerminalActivity.this.labService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_terminal);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(this, LabService.class), serviceConnection, Context.BIND_AUTO_CREATE);

        term_name = findViewById(R.id.terminal_name);
        term_hostname = findViewById(R.id.terminal_hostname);
        term_ip = findViewById(R.id.ip_address);
        term_posx = findViewById(R.id.term_posx);
        term_posy = findViewById(R.id.term_posy);
        term_config = findViewById(R.id.terminal_config);
        lab_select = findViewById(R.id.lab_selection);
        term_sumbit = findViewById(R.id.terminal_submit);

        term_sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posx = Integer.parseInt(term_posx.getText().toString());
                posy = Integer.parseInt(term_posy.getText().toString());

                configuration = (TerminalConfiguration) term_config.getSelectedItem();

                laboratory = (Laboratory) lab_select.getSelectedItem();

                if(terminalInputValidation()){
                    InetAddress ip = null;
                    try {
                        ip = InetAddress.getByName(term_ip.getText().toString());
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }

                    Terminal terminal = new TerminalBuilder()
                            .setName(term_name.getText().toString())
                            .setHostname(term_hostname.getText().toString())
                            .setIpAddress(ip)
                            .setPositionX(posx)
                            .setPositionY(posy)
                            .setConfiguration(configuration)
                            .createTerminal();

                    labService.saveTerminal(laboratory, terminal);
                }
            }
        });


    }

    private boolean terminalInputValidation() {

        if(posx <= 0 || posy <= 0){
            Toast.makeText(this, "Error: Terminal coordinates must be positive", Toast.LENGTH_LONG).show();
            return false;
        }

        if(configuration == null){
            Toast.makeText(this, "Error: Please select a configuration", Toast.LENGTH_LONG).show();
            return false;
        }

        if(laboratory == null){
            Toast.makeText(this, "Error: Please select a laboratory", Toast.LENGTH_LONG).show();
            return false;
        }

        InetAddress ip = null;
        try {
            ip = InetAddress.getByName(term_ip.getText().toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        Terminal terminal = new TerminalBuilder()
                .setName(term_name.getText().toString())
                .setHostname(term_hostname.getText().toString())
                .setIpAddress(ip)
                .setPositionX(posx)
                .setPositionY(posy)
                .setConfiguration(configuration)
                .createTerminal();
        if(terminal != null)return true;

        Toast.makeText(this, "Error: Unknown error", Toast.LENGTH_LONG).show();
        return false;
    }
}
