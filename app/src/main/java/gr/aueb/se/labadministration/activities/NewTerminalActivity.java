package gr.aueb.se.labadministration.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import gr.aueb.se.labadministration.R;
import gr.aueb.se.labadministration.domain.builder.TerminalBuilder;
import gr.aueb.se.labadministration.domain.configurations.SoftwarePackage;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.domain.lab.Laboratory;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.services.ConfigurationService;
import gr.aueb.se.labadministration.services.LabService;

/**
 * The activity that registers a new Terminal
 */
public class NewTerminalActivity extends AppCompatActivity {

    private LabService labService;
    private ConfigurationService configurationService;
    private EditText term_name, term_hostname, term_ip, term_posx, term_posy;
    private Spinner term_config, lab_select;
    private TerminalConfiguration configuration;
    private Laboratory laboratory;
    private Button term_sumbit;
    private List<Laboratory> labs;
    private Collection<TerminalConfiguration> configurations;
    private ArrayAdapter<Laboratory> adapter;
    private ArrayAdapter<TerminalConfiguration> adapter1;
    private int posx, posy;

    /**
     * Method that initiates connection with lab service
     */
    private ServiceConnection labServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            NewTerminalActivity.this.labService = ((LabService.LabServiceBinder) iBinder).getService();

            adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, labService.listLabs());
            lab_select.setAdapter(adapter);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            NewTerminalActivity.this.labService = null;
        }
    };

    /**
     * Method that initiates connection with configuration service
     */
    private ServiceConnection configurationServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            NewTerminalActivity.this.configurationService = ((ConfigurationService.ConfigurationServiceBinder) iBinder).getService();

            adapter1 = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, new ArrayList<>(configurationService.listAllConfigs()));
            term_config.setAdapter(adapter1);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            NewTerminalActivity.this.configurationService = null;
        }
    };

    /**
     * Default Android Method
     * After submission the method closes the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_terminal);

        bindService(new Intent(this, LabService.class), labServiceConnection, Context.BIND_AUTO_CREATE);
        bindService(new Intent(this, ConfigurationService.class), configurationServiceConnection, Context.BIND_AUTO_CREATE);

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

                    terminal.setStatus(Terminal.TerminalStatus.AVAILABLE);

                    labService.saveTerminal(laboratory, terminal);

                    Toast.makeText(getApplicationContext(), "Successful Addition", Toast.LENGTH_LONG).show();

                    finish();
                }
            }
        });

    }

    /**
     * Method that checks if all the data inputs are correct
     * @return true or false based on the data given
     */
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

    /**
     * Default Android Method
     * Unbinds services in order to avoid leakages
     */
    @Override
    protected void onStop() {
        super.onStop();
        stopService(getIntent());
        unbindService(labServiceConnection);
        unbindService(configurationServiceConnection);
    }
}
