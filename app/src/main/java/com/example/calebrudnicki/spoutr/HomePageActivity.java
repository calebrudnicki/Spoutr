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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tvName;
    private TextView tvUsername;
    private ListView lvWaterReports;
    private User u;
    private WaterReport wr;
    private Model modelHelper;
    private List<String> listWaterReports;

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

        //This block of code sets up the list view
        listWaterReports = new ArrayList<>();
        for (WaterReport wr : Model.allReports) {
            listWaterReports.add(wr.getLocation() + " - " + wr.getType() + " - " + wr.getCondition() + " - " + wr.getDateSubmitted() + " - " + wr.getSubmitter().getName());
        }
        lvWaterReports = (ListView) findViewById(R.id.lvWaterReports);
        ArrayAdapter<WaterReport> listViewAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listWaterReports);
        lvWaterReports.setAdapter(listViewAdapter);

        lvWaterReports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("LIST", parent.getItemAtPosition(position).toString());
                //Log.d("LIST", parent.getItemAtPosition(position));
            }
        });

        u = getIntent().getParcelableExtra("SESSION_USER");
        Log.d("TESTERREG4", u.getEmail());
        Log.d("CURRENT USER", "Name: " + u.getName());
        Log.d("CURRENT USER", "Email: " + u.getEmail());
        Log.d("CURRENT USER", "Username: " + u.getUsername());
        Log.d("CURRENT USER", "Password: " + u.getPassword());
        Log.d("CURRENT USER", "Account Type: " + u.getAccountType());

        modelHelper = Model.getInstance();
        int counter = 1;
        for (WaterReport wr : modelHelper.getAllReports()) {
            Log.d("WATER REPORT #" + counter, "Location: " + wr.getLocation());
            Log.d("WATER REPORT #" + counter, "Submitter: " + wr.getSubmitter().getUsername());
            Log.d("WATER REPORT #" + counter, "Date: " + wr.getDateSubmitted());
            Log.d("WATER REPORT #" + counter, "Water Type: " + wr.getType());
            Log.d("WATER REPORT #" + counter, "Water Condition : " + wr.getCondition());
            Log.d("WATER REPORT #" + counter, "ID ##: " + wr.getReportNumber());
            counter++;
        }
        Log.d("WATER REPORTS", "All Reports: " + modelHelper.getAllReports().size());
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

        if (id == R.id.nav_submitReport) {
            Intent submitReportActivityIntent = new Intent(HomePageActivity.this, SubmitReportActivity.class);
            submitReportActivityIntent.putExtra("SESSION_USER", (Parcelable) u);
            HomePageActivity.this.startActivity(submitReportActivityIntent);
        } else if (id == R.id.nav_settings) {
            Intent settingsActivityIntent = new Intent(HomePageActivity.this, SettingsActivity.class);
            settingsActivityIntent.putExtra("SESSION_USER", (Parcelable) u);
            HomePageActivity.this.startActivity(settingsActivityIntent);
        } else if (id == R.id.nav_logout) {
            Intent loginActivityIntent = new Intent(HomePageActivity.this, LoginActivity.class);
            loginActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            HomePageActivity.this.startActivity(loginActivityIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

