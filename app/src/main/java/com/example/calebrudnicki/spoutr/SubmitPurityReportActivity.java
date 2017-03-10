package com.example.calebrudnicki.spoutr;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Parcelable;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class SubmitPurityReportActivity extends AppCompatActivity {

    private EditText etPurityLocation;
    private Spinner spWaterTypes;
    private Spinner spWaterConditions;
    private Button bSubmitPurityReport;
    private Button bCancelPurityReport;
    private User u;
    private Model modelHelper;
    private Location enteredLocation;
    private int etVirusPPM;
    private int etContaminantPPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report);

        etPurityLocation = (EditText) findViewById(R.id.etPurityLocation);
        spWaterTypes = (Spinner) findViewById(R.id.spWaterTypes);
        spWaterConditions = (Spinner) findViewById(R.id.spWaterConditions);
        bSubmitPurityReport = (Button) findViewById(R.id.bSubmitPurityReport);
        bCancelPurityReport = (Button) findViewById(R.id.bCancelReport);
        modelHelper = Model.getInstance();
        etVirusPPM = (EditText) findViewById(R.id.etVirusPPM);
        etContaminantPPM = (EditText) findViewById(R.id.etContaminantPPM);

        u = getIntent().getParcelableExtra("SESSION_USER");
        Log.d("SUBMIT", "User to submit: " + u.getUsername());

        //This function sets up the adapter to display the allowable water types in the spinner
        ArrayAdapter<String> waterTypeAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Model.waterTypes);
        waterTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spWaterTypes.setAdapter(waterTypeAdapter);

        //This function sets up the adapter to display the allowable water condtions in the spinner
        ArrayAdapter<String> waterConditionAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Model.waterConditions);
        waterConditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spWaterConditions.setAdapter(waterConditionAdapter);
    }

    /**
     * This function uses the text field to geocode the address into a set of coordinates
     * @return the location of the coordinates
     */
    private Location findCoordinates() throws IOException {
        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(etLocation.getText().toString(), 1);
        Address add = list.get(0);
        double lat = add.getLatitude();
        double lng = add.getLongitude();
        Location location = new Location("ENTERED_LOCATION");
        location.setLatitude(lat);
        location.setLongitude(lng);
        return location;
    }

    /**
     * This functions takes in a location object and returns the location's city
     * @param location the location that you want to find the city of
     * @return the city of the location
     * @throws IOException thrown if the object passed in is invalid
     */
    private String findAddress(Location location) throws IOException {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        //String address = addresses.get(0).getAddressLine(0);
        String city = addresses.get(0).getLocality();
        //String state = addresses.get(0).getAdminArea();
        //String country = addresses.get(0).getCountryName();
        //String postalCode = addresses.get(0).getPostalCode();
        //String knownName = addresses.get(0).getFeatureName();
        return city;
    }

    /**
     * This function creates a new water report once the submit water report is pressed
     * @param view View the register button
     */
    protected void onSubmitPurityReportPressed(View view) throws IOException {
        String date = new SimpleDateFormat("MM/dd/yyyy - HH:mm:ss").format(Calendar.getInstance().getTime());
        Location location = findCoordinates();
        String locationString = findAddress(location);
        WaterPurityReport newPurityReport = new WaterPurityReport(u, date, location,
                locationString, spWaterTypes.getSelectedItem().toString(),
                spWaterConditions.getSelectedItem().toString(), 111, virusPPM, contaminantPPM);
        if (modelHelper.addWaterReport(newReport)) {
            Intent homePageIntent = new Intent(SubmitPurityReportActivity.this, HomePageActivity.class);
            homePageIntent.putExtra("SESSION_USER", (Parcelable) u);
            SubmitPurityReportActivity.this.startActivity(homePageIntent);
        }
    }

    /**
     * Takes the user back to the home screen without creating a new report
     * @param view View the cancel button
     */
    protected void onCancelPurityReportPressed(View view) {
        Intent homePageIntent = new Intent(SubmitPurityReportActivity.this, HomePageActivity.class);
        homePageIntent.putExtra("SESSION_USER", (Parcelable) u);
        SubmitPurityReportActivity.this.startActivity(homePageIntent);
    }

}