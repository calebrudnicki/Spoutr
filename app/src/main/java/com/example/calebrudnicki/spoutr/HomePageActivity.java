package com.example.calebrudnicki.spoutr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HomePageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tvName;
    private TextView tvUsername;
    private Button bSettings;
    private Button bSubmitReport;
    private Button bLogout;
    private User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bSettings = (Button) findViewById(R.id.bSettings);
        bSubmitReport = (Button) findViewById(R.id.bSubmitReport);
        bLogout = (Button) findViewById(R.id.bLogout);

        u = getIntent().getParcelableExtra("SESSION_USER");
        Log.d("CURRENT USER", "Name: " + u.getName());
        Log.d("CURRENT USER", "Username: " + u.getUsername());
        Log.d("CURRENT USER", "Password: " + u.getPassword());
        Log.d("CURRENT USER", "Account Type: " + u.getAccountType());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        tvName = (TextView) findViewById(R.id.tvName);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvName.setText(u.getName());
        tvUsername.setText(u.getUsername());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
//            Log.d("NAVBAR", "Heading to the submit a report page");
//            Intent submitReportActivityIntent = new Intent(HomePageActivity.this, SubmitReportActivity.class);
//            submitReportActivityIntent.putExtra("SESSION_USER", (Parcelable) u);
//            HomePageActivity.this.startActivity(submitReportActivityIntent);
        } else if (id == R.id.nav_slideshow) {
//            Log.d("NAVBAR", "Going to the settings page");
//            Intent settingsActivityIntent = new Intent(HomePageActivity.this, SettingsActivity.class);
//            settingsActivityIntent.putExtra("SESSION_USER", (Parcelable) u);
//            HomePageActivity.this.startActivity(settingsActivityIntent);
        } else if (id == R.id.nav_manage) {
//            Log.d("NAVBAR", "Logging the user out");
//            Intent loginActivityIntent = new Intent(HomePageActivity.this, LoginActivity.class);
//            HomePageActivity.this.startActivity(loginActivityIntent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
        This function brings a user to the settings page when the settings button is pressed
        @param view View the settings button
     */
    protected void onSettingsPressed(View view) {
        Intent settingsActivityIntent = new Intent(HomePageActivity.this, SettingsActivity.class);
        settingsActivityIntent.putExtra("SESSION_USER", (Parcelable) u);
        HomePageActivity.this.startActivity(settingsActivityIntent);
    }

    /**
        This function brings a user to the submit a report page when the submit report button is pressed
        @param view View the settings button
     */
    protected void onSubmitReportPressed(View view) {
        Intent submitReportActivityIntent = new Intent(HomePageActivity.this, SubmitReportActivity.class);
        submitReportActivityIntent.putExtra("SESSION_USER", (Parcelable) u);
        HomePageActivity.this.startActivity(submitReportActivityIntent);
    }

    /**
        This function logs a new user out when the logout button is pressed
        @param view View the login button
     */
    protected void onLogoutPressed(View view) {
        Log.d("LOGOUT", "Logout SUCCESSFUL");
        Intent loginActivityIntent = new Intent(HomePageActivity.this, LoginActivity.class);
        HomePageActivity.this.startActivity(loginActivityIntent);
    }

}

