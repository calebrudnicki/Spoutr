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

public class SubmitReportActivity extends AppCompatActivity {

    private EditText etLocation;
    private Spinner spWaterTypes;
    private Spinner spWaterConditions;
    private Button bSubmitReport;
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
        This function creates a new water report once the submit water report is pressed
        @param view View the register button
     */
    protected void onSubmitReportPressed(View view) {
        Log.d("WATER REPORT SUBMITTED", "Location: " + etLocation.getText().toString());
        Log.d("WATER REPORT SUBMITTED", "Water Type: " + (String) spWaterTypes.getSelectedItem());
        Log.d("WATER REPORT SUBMITTED", "Water Condition: " + (String) spWaterConditions.getSelectedItem());
    }

}
