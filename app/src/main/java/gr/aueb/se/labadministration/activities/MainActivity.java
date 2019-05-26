package gr.aueb.se.labadministration.activities;

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

public class MainActivity extends AppCompatActivity {

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

    public void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_view, fragment);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeActionBar();
        switchFragment(new LabFragment());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
