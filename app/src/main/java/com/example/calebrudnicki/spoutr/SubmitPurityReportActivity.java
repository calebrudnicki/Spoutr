package com.example.calebrudnicki.spoutr;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SubmitPurityReportActivity extends AppCompatActivity {

    private ListView lvWaterReports;
    private List<WaterReport> listWaterReports;
    private TextView tvSelectedWaterReport;
    private Spinner spPurityConditions;
    private EditText etVirusPPM;
    private EditText etContaminantPPM;
    private Button bSubmitPurityReport;
    private Button bCancelPurityReport;
    private User u;
    private WaterReport selectedReport;
    private Model modelHelper;
    private boolean hasSelectedWaterReport = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_purity_report);

        tvSelectedWaterReport = (TextView) findViewById(R.id.tvSelectedWaterReport);
        spPurityConditions = (Spinner) findViewById(R.id.spPurityConditions);
        etVirusPPM = (EditText) findViewById(R.id.etVirusPPM);
        etContaminantPPM = (EditText) findViewById(R.id.etContaminantPPM);
        bSubmitPurityReport = (Button) findViewById(R.id.bSubmitPurityReport);
        bCancelPurityReport = (Button) findViewById(R.id.bCancelPurityReport);
        modelHelper = Model.getInstance();

        u = getIntent().getParcelableExtra("SESSION_USER");

        //This block of code sets up the adapter to display the allowable account types in the spinner
        ArrayAdapter<String> purityConditionAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Model.purityConditions);
        purityConditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPurityConditions.setAdapter(purityConditionAdapter);

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
                selectedReport = (WaterReport) parent.getItemAtPosition(position);
                tvSelectedWaterReport.setText("Selected Water Report: " + selectedReport.getLocationString());
                hasSelectedWaterReport = true;
            }
        });
    }

    /**
     * This function creates a new purity report once the submit water report is pressed
     * @param view View the register button
     */
    protected void onSubmitPurityReportPressed(View view) {
        String date = new SimpleDateFormat("MM/dd/yyyy - HH:mm:ss").format(Calendar.getInstance().getTime());
        if (hasSelectedWaterReport == true) {
            PurityReport pr = new PurityReport(u, date, selectedReport.getLocation(), spPurityConditions.getSelectedItem().toString(), Integer.valueOf(etVirusPPM.getText().toString()), Integer.valueOf(etContaminantPPM.getText().toString()));
            if (modelHelper.addPurityReport(pr)) {
                selectedReport.addToPRList(pr);
                Intent homePageIntent = new Intent(SubmitPurityReportActivity.this, HomePageActivity.class);
                homePageIntent.putExtra("SESSION_USER", (Parcelable) u);
                SubmitPurityReportActivity.this.startActivity(homePageIntent);
            }
        } else {
            Toast toast = Toast.makeText(this, "You didn't enter all the necessary info. Try again.", Toast.LENGTH_SHORT);
            toast.show();
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
