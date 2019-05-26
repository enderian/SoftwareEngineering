package gr.aueb.se.labadministration.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import gr.aueb.se.labadministration.R;

public class AddSoftwarePackageFragment extends DialogFragment {

    private EditText softwareConfName;
    private EditText creationCommand;
    private EditText deleteCommand;

    private boolean softwareInputValidation(){
        if( softwareConfName.getText().toString().trim().equals("") ||
                creationCommand.getText().toString().trim().equals("") ||
                deleteCommand.getText().toString().trim().equals("")){
            Toast.makeText(getContext(), "Error: all fields are requiered", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_software_package, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        softwareConfName = getView().findViewById(R.id.softwareConfigurationName);
        creationCommand = getView().findViewById(R.id.creationCommand);
        deleteCommand = getView().findViewById(R.id.deleteCommand);

        Button addButton = getView().findViewById(R.id.addConfigurationButton);
        addButton.setOnClickListener(event -> {
            if (softwareInputValidation()) {

            }
        });
    }
}
