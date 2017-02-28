package com.example.calebrudnicki.spoutr;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SubmitReportActivity extends AppCompatActivity {

    private EditText etLocation;
    private Spinner spWaterTypes;
    private Spinner spWaterConditions;
    private Button bSubmitReport;
    private Button bCancelReport;
    private User u;
    private Model modelHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report);

        etLocation = (EditText) findViewById(R.id.etLocation);
        spWaterTypes = (Spinner) findViewById(R.id.spWaterTypes);
        spWaterConditions = (Spinner) findViewById(R.id.spWaterConditions);
        bSubmitReport = (Button) findViewById(R.id.bSubmitReport);
        bCancelReport = (Button) findViewById(R.id.bCancelReport);
        modelHelper = Model.getInstance();

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
     * This function creates a new water report once the submit water report is pressed
     * @param view View the register button
     */
    protected void onSubmitReportPressed(View view) {
        String date = new SimpleDateFormat("MM/dd/yyyy - HH:mm:ss").format(Calendar.getInstance().getTime());
        WaterReport newReport = new WaterReport(u, date, etLocation.getText().toString(), spWaterTypes.getSelectedItem().toString(), spWaterConditions.getSelectedItem().toString(), 111);
        if (modelHelper.addWaterReport(newReport)) {
            Intent homePageIntent = new Intent(SubmitReportActivity.this, HomePageActivity.class);
            homePageIntent.putExtra("SESSION_USER", (Parcelable) u);
            SubmitReportActivity.this.startActivity(homePageIntent);
        }
    }

    /**
     * Takes the user back to the home screen without creating a new report
     * @param view View the cancel button
     */
    protected void onCancelReportPressed(View view) {
        Intent homePageIntent = new Intent(SubmitReportActivity.this, HomePageActivity.class);
        homePageIntent.putExtra("SESSION_USER", (Parcelable) u);
        SubmitReportActivity.this.startActivity(homePageIntent);
    }

}
