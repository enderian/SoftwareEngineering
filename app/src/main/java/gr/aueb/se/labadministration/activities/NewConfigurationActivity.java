package gr.aueb.se.labadministration.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import gr.aueb.se.labadministration.R;
import gr.aueb.se.labadministration.dao.SoftwarePackageDAO;
import gr.aueb.se.labadministration.dao.TerminalConfigurationDAO;
import gr.aueb.se.labadministration.domain.configurations.SoftwarePackage;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.memorydao.SoftwarePackageDAOMemory;
import gr.aueb.se.labadministration.memorydao.TerminalConfigurationDAOMemory;
import gr.aueb.se.labadministration.services.ConfigurationService;

public class NewConfigurationActivity extends AppCompatActivity {

    private LinearLayout softwareLayout, hardwareLayout;
    private Button addButton;
    private RadioGroup confRadioGroup;
    private RadioButton hardwareRadioButton;
    private EditText hardwareConfName, softwareConfName, os, graphics, processor, storage, ram, creationCommand, deleteCommand;

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

        softwareLayout = findViewById(R.id.softwareLayout);
        hardwareLayout = findViewById(R.id.hardwareLayout);
        addButton = findViewById(R.id.addConfigurationButton);
        confRadioGroup = findViewById(R.id.configurationRadioGroup);
        hardwareRadioButton = findViewById(R.id.hardwareRadioButton);
        hardwareConfName = findViewById(R.id.hardwareConfigurationName);
        softwareConfName = findViewById(R.id.softwareConfigurationName);
        os = findViewById(R.id.os);
        graphics = findViewById(R.id.graphicsCard);
        processor = findViewById(R.id.processor);
        storage = findViewById(R.id.storageCapacity);
        ram = findViewById(R.id.ram);
        creationCommand = findViewById(R.id.creationCommand);
        deleteCommand = findViewById(R.id.deleteCommand);

        confRadioGroup.setOnCheckedChangeListener( (group, checkedId) ->
        {
            RadioButton checkedRadioButton = group.findViewById(checkedId);
            boolean isChecked = checkedRadioButton.isChecked();
            if (isChecked)
            {
                if(checkedRadioButton.getText().toString().startsWith("Hardware")){
                    softwareLayout.setVisibility(View.GONE);
                    hardwareLayout.setVisibility(View.VISIBLE);
                }else{
                    hardwareLayout.setVisibility(View.GONE);
                    softwareLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        addButton.setOnClickListener(e->{
            if(hardwareRadioButton.isChecked()){
                if(!hardwareInputValidation()){return;}
                TerminalConfigurationDAO terminalDAO = new TerminalConfigurationDAOMemory();
                terminalDAO.save(new TerminalConfiguration(
                        processor.getText().toString(),
                        graphics.getText().toString(),
                        Integer.parseInt(storage.getText().toString()),
                        Integer.parseInt(ram.getText().toString()),
                        os.getText().toString(),
                        hardwareConfName.getText().toString()));
                confRadioGroup.check(R.id.hardwareRadioButton);
                finish();
                startActivity(getIntent());
                Toast.makeText(getApplicationContext(), "hardware addition success", Toast.LENGTH_SHORT).show();
            }else{
                if(!softwareInputValidation()) return;
                SoftwarePackageDAO softwareDAO = new SoftwarePackageDAOMemory();
                softwareDAO.save(new SoftwarePackage(
                        softwareConfName.getText().toString(),
                        creationCommand.getText().toString(),
                        deleteCommand.getText().toString()
                ));
                confRadioGroup.check(R.id.softwareRadioButton);
                finish();
                startActivity(getIntent());
                Toast.makeText(getApplicationContext(), "software addition success", Toast.LENGTH_SHORT).show();

            }
        });

        confRadioGroup.check(R.id.hardwareRadioButton);

        TerminalConfiguration terminalConfiguration = (TerminalConfiguration) getIntent().getSerializableExtra("configuration");
        if (terminalConfiguration != null) {

        }
    }

    private boolean hardwareInputValidation(){
        if(!storage.getText().toString().trim().matches("\\d+") ||
                !ram.getText().toString().trim().matches("\\d+") ){
            Toast.makeText(getApplicationContext(), "Erorr: Storage and Ram should be integers", Toast.LENGTH_SHORT).show();
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

    private boolean softwareInputValidation(){
        if( softwareConfName.getText().toString().trim().equals("") ||
            creationCommand.getText().toString().trim().equals("") ||
            deleteCommand.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "Error: all fields are requiered", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
