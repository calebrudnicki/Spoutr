package com.example.calebrudnicki.spoutr;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Boolean showingOneLocation;
    private WaterReport selectedReport;
    private List<WaterReport> allReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //This takes in a value and decides whether to show one location or many locations
        showingOneLocation = getIntent().getBooleanExtra("ONE_LOCATION", true);
        if (showingOneLocation) {
            selectedReport = getIntent().getParcelableExtra("SELECTED_LOCATION");
        } else {
            allReports = getIntent().getParcelableArrayListExtra("ALL_LOCATIONS");
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (showingOneLocation) {
            String markerTitle = selectedReport.getLocationString() + " - Submitted by " + selectedReport.getSubmitter().getName();
            LatLng shownLocation = new LatLng(selectedReport.getLocation().getLatitude(), selectedReport.getLocation().getLongitude());
            mMap.addMarker(new MarkerOptions().position(shownLocation).title(markerTitle).snippet(selectedReport.getType() + " - " + selectedReport.getCondition()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shownLocation, 15));
        } else {
            for (WaterReport wr : allReports) {
                String markerTitle = wr.getLocationString() + " - Submitted by " + wr.getSubmitter().getName();
                LatLng shownLocation = new LatLng(wr.getLocation().getLatitude(), wr.getLocation().getLongitude());
                mMap.addMarker(new MarkerOptions().position(shownLocation).title(markerTitle).snippet(wr.getType() + " - " + wr.getCondition()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(findAverageLatLng(allReports)));
            }
        }
    }

    /**
     * This functions takes in a list of water reports and find the average latitude and longitude of the points
     * @param reports List of Water Report objects
     * @return the average latitude and longitude points of all the locations
     */
    private LatLng findAverageLatLng(List<WaterReport> reports) {
        double lat = 0.0;
        double lng = 0.0;
        for (WaterReport wr : reports) {
            lat += wr.getLocation().getLatitude();
            lng += wr.getLocation().getLongitude();
        }
        lat = lat / reports.size();
        lng = lng / reports.size();
        LatLng averageLatLng = new LatLng(lat, lng);
        return averageLatLng;
    }

}
