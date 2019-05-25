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
import gr.aueb.se.labadministration.interfaces.Presenter;
import gr.aueb.se.labadministration.interfaces.SignInActivity;
import gr.aueb.se.labadministration.presenter.SignInPresenter;

public class signInActivity extends AppCompatActivity implements SignInActivity {


    EditText usernameEditText;
    EditText passwordEditText;
    Button submitButton;
    Button recoveryButton;

    SignInPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Create and start presenter
        new SignInPresenter(this).start();

        // Initialization of activity components
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        submitButton = findViewById(R.id.submitButton);
        recoveryButton = findViewById(R.id.recoveryButton);


        submitButton.setOnClickListener(view -> {
            presenter.performSignIn();
        });

        // recovery button redirect user to external link for credentials recovery
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
        recoveryButton.setVisibility(View.GONE); // recoveryButton only appears when user submit wrong credentials.
    }

    @Override
    public String getUsername() {
        return (usernameEditText == null) ? null : usernameEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return (passwordEditText == null) ? null : passwordEditText.getText().toString();
    }

    @Override
    public void showFailure() {
        Toast.makeText(getApplicationContext(),"username or/and password are wrong.",Toast.LENGTH_SHORT).show();
        if(recoveryButton != null){ // show button for credentials recovery
            recoveryButton.setVisibility(View.VISIBLE);
            recoveryButton.setText("RECOVER CREDENTIALS");
        }
    }

    @Override
    public void open() {
        // doesn't need implementation because this is launcher activity. It's open.
    }

    @Override
    public void close() { // activity close only if sign in was successful.
        // TODO go to the next activity
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = (SignInPresenter) presenter;
    }
}
