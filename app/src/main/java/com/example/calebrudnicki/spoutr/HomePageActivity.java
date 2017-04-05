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
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
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
    private List<WaterReport> listWaterReports;

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
                Intent mapActivityIntent = new Intent(HomePageActivity.this, MapActivity.class);
                mapActivityIntent.putExtra("ONE_LOCATION", false);
                mapActivityIntent.putExtra("ALL_LOCATIONS", (Serializable) listWaterReports);
                HomePageActivity.this.startActivity(mapActivityIntent);
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
            listWaterReports.add(wr);
        }

        lvWaterReports = (ListView) findViewById(R.id.lvWaterReports);
        ArrayAdapter<WaterReport> listViewAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listWaterReports);
        lvWaterReports.setAdapter(listViewAdapter);

        lvWaterReports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mapActivityIntent = new Intent(HomePageActivity.this, MapActivity.class);
                mapActivityIntent.putExtra("ONE_LOCATION", true);
                mapActivityIntent.putExtra("SELECTED_LOCATION", (Parcelable) parent.getItemAtPosition(position));
                HomePageActivity.this.startActivity(mapActivityIntent);
            }
        });

        lvWaterReports.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                WaterReport chosenReport = (WaterReport) parent.getItemAtPosition(position);
                if (u.getAccountType().equals("Manager") && chosenReport.getPrList().size() > 0) {
                    Intent purityInfoActivityIntent = new Intent(HomePageActivity.this, PurityInfoActivity.class);
                    purityInfoActivityIntent.putExtra("SELECTED_LOCATION", chosenReport);
                    HomePageActivity.this.startActivity(purityInfoActivityIntent);
                    return true;
                }
                return false;
            }
        });

        u = getIntent().getParcelableExtra("SESSION_USER");
        modelHelper = Model.getInstance();

        Toast toast = Toast.makeText(this, "Click to see map. Long click to see the purity reports page.", Toast.LENGTH_LONG);
        toast.show();

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_submitReport) {
            Intent submitReportActivityIntent = new Intent(HomePageActivity.this, SubmitReportActivity.class);
            submitReportActivityIntent.putExtra("SESSION_USER", (Parcelable) u);
            HomePageActivity.this.startActivity(submitReportActivityIntent);
        } else if (id == R.id.nav_submitPurityReport) {
            if (!u.getAccountType().equals("User")) {
                Intent submitPurityReportActivityIntent = new Intent(HomePageActivity.this, SubmitPurityReportActivity.class);
                submitPurityReportActivityIntent.putExtra("SESSION_USER", (Parcelable) u);
                HomePageActivity.this.startActivity(submitPurityReportActivityIntent);
            } else {
                Toast toast = Toast.makeText(this, "As a User, you cannot submit a purity report", Toast.LENGTH_LONG);
                toast.show();
            }
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

