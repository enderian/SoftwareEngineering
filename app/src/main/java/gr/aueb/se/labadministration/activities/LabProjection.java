package gr.aueb.se.labadministration.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import gr.aueb.se.labadministration.R;

public class LabProjection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_projection);

        makeActionBar();
    }

    // this method shows menu at main_activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //this method is called when user press any button of navigation bar(wifi, cellular, refresh)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_history:
                startActivity(new Intent(this, HistoryProjection.class));
                break;
            case R.id.nav_patterns:
                startActivity(new Intent(this, LabProjection.class));
                break;
            default:


        }
        return super.onOptionsItemSelected(item);
    }

    // makes the action bar.
    private void makeActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.app_icon);
        actionBar.setDisplayUseLogoEnabled(true);// display app_icon.
        actionBar.setDisplayShowHomeEnabled(true);// display back button.
    }

}
