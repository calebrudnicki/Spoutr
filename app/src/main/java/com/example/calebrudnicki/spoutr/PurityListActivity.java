package com.example.calebrudnicki.spoutr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PurityListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private List<PurityReport> listPurityReports = new ArrayList<>();
    private ListView lvPurityReports;
    private User u;
    private PurityReport pr;
    private Model modelHelper;
    private TextView tvName;
    private TextView tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //This block of code sends the user to a map of all locations when the floating button is tapped
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapActivityIntent = new Intent(PurityListActivity.this, MapActivity.class);
                mapActivityIntent.putExtra("ONE_LOCATION", false);
                mapActivityIntent.putExtra("ALL_LOCATIONS", (Serializable) listPurityReports);
                PurityListActivity.this.startActivity(mapActivityIntent);
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
        listPurityReports = new ArrayList<>();
        for (PurityReport pr : Model.allPurityReports) {
            listPurityReports.add(pr);
        }

        lvPurityReports = (ListView) findViewById(R.id.lvWaterReports);
        ArrayAdapter<WaterReport> listViewAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listPurityReports);
        lvPurityReports.setAdapter(listViewAdapter);

        lvPurityReports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("HOME", "Purity Report List Size: " + ((WaterReport) parent.getItemAtPosition(position)).getPrList().size());
                Intent mapActivityIntent = new Intent(PurityListActivity.this, MapActivity.class);
                mapActivityIntent.putExtra("ONE_LOCATION", true);
                mapActivityIntent.putExtra("SELECTED_LOCATION", (Parcelable) parent.getItemAtPosition(position));
                PurityListActivity.this.startActivity(mapActivityIntent);
            }
        });

        u = getIntent().getParcelableExtra("SESSION_USER");
        modelHelper = Model.getInstance();

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_submitReport) {
            Intent submitReportActivityIntent = new Intent(PurityListActivity.this, SubmitReportActivity.class);
            submitReportActivityIntent.putExtra("SESSION_USER", (Parcelable) u);
            PurityListActivity.this.startActivity(submitReportActivityIntent);
        } else if (id == R.id.nav_submitPurityReport) {
            if (!u.getAccountType().equals("User")) {
                Intent submitPurityReportActivityIntent = new Intent(PurityListActivity.this, SubmitPurityReportActivity.class);
                submitPurityReportActivityIntent.putExtra("SESSION_USER", (Parcelable) u);
                PurityListActivity.this.startActivity(submitPurityReportActivityIntent);
            } else {
                Toast toast = Toast.makeText(this, "As a User, you cannot submit a purity report", Toast.LENGTH_LONG);
                toast.show();
            }
        } else if (id == R.id.nav_settings) {
            Intent settingsActivityIntent = new Intent(PurityListActivity.this, SettingsActivity.class);
            settingsActivityIntent.putExtra("SESSION_USER", (Parcelable) u);
            PurityListActivity.this.startActivity(settingsActivityIntent);
        } else if (id == R.id.nav_logout) {
            Intent loginActivityIntent = new Intent(PurityListActivity.this, LoginActivity.class);
            loginActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PurityListActivity.this.startActivity(loginActivityIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
