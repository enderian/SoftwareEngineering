package gr.aueb.se.labadministration.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import gr.aueb.se.labadministration.R;
import gr.aueb.se.labadministration.dao.TerminalConfigurationDAO;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.fragments.AddSoftwarePackageFragment;
import gr.aueb.se.labadministration.memorydao.TerminalConfigurationDAOMemory;
import gr.aueb.se.labadministration.services.ConfigurationService;

public class NewConfigurationActivity extends AppCompatActivity {

    private EditText hardwareConfName, os, graphics, processor, storage, ram;

    private ConfigurationService service;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            NewConfigurationActivity.this.service = ((ConfigurationService.ConfigurationServiceBinder) service).getService();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            NewConfigurationActivity.this.service = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_configuration);
        bindService(new Intent(this, ConfigurationService.class), serviceConnection, Context.BIND_AUTO_CREATE);

        Button addButton = findViewById(R.id.addConfigurationButton);
        Button addSoftwareButton = findViewById(R.id.addSoftwareButton);
        hardwareConfName = findViewById(R.id.hardwareConfigurationName);

        os = findViewById(R.id.os);
        graphics = findViewById(R.id.graphicsCard);
        processor = findViewById(R.id.processor);
        storage = findViewById(R.id.storageCapacity);
        ram = findViewById(R.id.ram);

        addSoftwareButton.setOnClickListener(e -> {
            new AddSoftwarePackageFragment().show(getSupportFragmentManager(), "add");
        });

        addButton.setOnClickListener(e->{
            if(!hardwareInputValidation()){return;}

            TerminalConfiguration configuration = new TerminalConfiguration(
                    processor.getText().toString(),
                    graphics.getText().toString(),
                    Integer.parseInt(storage.getText().toString()),
                    Integer.parseInt(ram.getText().toString()),
                    os.getText().toString(),
                    hardwareConfName.getText().toString()
            );

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            Toast.makeText(getApplicationContext(), "hardware addition success", Toast.LENGTH_SHORT).show();
        });

        TerminalConfiguration terminalConfiguration = (TerminalConfiguration) getIntent().getSerializableExtra("configuration");
        if (terminalConfiguration != null) {

        }
    }

    private boolean hardwareInputValidation(){
        if(!storage.getText().toString().trim().matches("\\d+") ||
                !ram.getText().toString().trim().matches("\\d+") ){
            Toast.makeText(getApplicationContext(), "Error: Storage and Ram should be integers", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(processor.getText().toString().trim().equals("") ||
                graphics.getText().toString().trim().equals("") ||
                os.getText().toString().trim().equals("") ||
                hardwareConfName.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "Error: all fields are requiered", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
