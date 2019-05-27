package gr.aueb.se.labadministration.activities;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import gr.aueb.se.labadministration.R;
import gr.aueb.se.labadministration.fragments.ConfigurationFragment;
import gr.aueb.se.labadministration.fragments.HistoryFragment;
import gr.aueb.se.labadministration.fragments.LabFragment;

/**
 * The main activity of the application, redirects to sign in activity
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The bottom navigation bar, in case of admin the nav_history and nav_configuration options appear
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.nav_history:
                        switchFragment(new HistoryFragment());
                        break;
                    case R.id.nav_labs:
                        switchFragment(new LabFragment());
                        break;
                    case R.id.nav_configuration:
                        switchFragment(new ConfigurationFragment());
                        break;
                    default: return false;
                }
                return true;
            };

    /**
     * Fragment transaction method
     * @param fragment to switch to
     */
    public void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_view, fragment);
        transaction.commit();
    }

    /**
     * Default Android Methods
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeActionBar();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (getIntent().getBooleanExtra("configurations", false)) {
            navView.setSelectedItemId(R.id.nav_configuration);
            switchFragment(new ConfigurationFragment());
        } else {
            switchFragment(new LabFragment());
        }

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        boolean administrator = sharedPreferences.getBoolean("administrator", false);

        navView.getMenu().findItem(R.id.nav_history).setVisible(administrator);
        navView.getMenu().findItem(R.id.nav_configuration).setVisible(administrator);
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStack();
    }

    // makes the action bar.
    private void makeActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.app_icon);
        actionBar.setDisplayUseLogoEnabled(true);// display app_icon.
        actionBar.setDisplayShowHomeEnabled(true);// display back button.
    }

}
