package gr.aueb.se.labadministration.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gr.aueb.se.labadministration.R;
import gr.aueb.se.labadministration.services.SignInService;
import gr.aueb.se.labadministration.utilities.RequestResult;

public class SignInActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    Button submitButton;

    SignInService service;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            SignInActivity.this.service = ((SignInService.SignInBinder) service).getService();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            SignInActivity.this.service = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        bindService(new Intent(this, SignInService.class), serviceConnection, Context.BIND_AUTO_CREATE);

        // Initialization of activity components
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        submitButton = findViewById(R.id.submitButton);

        passwordEditText.setOnEditorActionListener((view, id, button) -> submitButton.callOnClick());
        submitButton.setOnClickListener(view -> {
            RequestResult requestResult = this.service.signInRequest(getUsername(), getPassword());
            if (requestResult.isSuccessful()) {
                Intent myIntent = new Intent(this, LabActivity.class);
                startActivity(myIntent);
            } else {
                Toast.makeText(getApplicationContext(), requestResult.getReasonOfFailure(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getUsername() {
        return (usernameEditText == null) ? null : usernameEditText.getText().toString();
    }

    public String getPassword() {
        return (passwordEditText == null) ? null : passwordEditText.getText().toString();
    }
}
