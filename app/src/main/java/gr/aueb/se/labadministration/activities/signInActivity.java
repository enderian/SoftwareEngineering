package gr.aueb.se.labadministration.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;

import gr.aueb.se.labadministration.R;

public class signInActivity extends AppCompatActivity {


    EditText usernameEditText;
    EditText passwordEditText;
    Button submitButton;
    Button recoveryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        submitButton = findViewById(R.id.submitButton);
        recoveryButton = findViewById(R.id.recoveryButton);


        submitButton.setOnClickListener(view -> {

            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            //TODO check credentials
            if(false){
                // correct credentials
                //TODO access to app
            }else{
                // wrong credentials
                Toast.makeText(getApplicationContext(),"username or/and password are wrong.",Toast.LENGTH_SHORT).show();
                recoveryButton.setVisibility(View.VISIBLE);
                recoveryButton.setText("RECOVER CREDENTIALS");

            }
        });

        recoveryButton.setOnClickListener(view ->{
            URL url;
            Uri uri;
            try {
                url = new URL("https://www.aueb.gr/");
                uri = Uri.parse( url.toURI().toString() );
                Intent browse = new Intent( Intent.ACTION_VIEW , uri );
                startActivity( browse );
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        recoveryButton.setVisibility(View.GONE);
    }
}
