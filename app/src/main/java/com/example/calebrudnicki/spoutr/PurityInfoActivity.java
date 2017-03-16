package com.example.calebrudnicki.spoutr;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PurityInfoActivity extends AppCompatActivity {

    private List<PurityReport> listPurityReports = new ArrayList<>();
    private ListView lvPurityReports;
    private WaterReport selectedReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purity_info);

        selectedReport = getIntent().getParcelableExtra("SELECTED_LOCATION");

        //This block of code sets up the list view
        listPurityReports = new ArrayList<>();
        for (PurityReport pr : selectedReport.getPrList()) {
            listPurityReports.add(pr);
        }

        lvPurityReports = (ListView) findViewById(R.id.lvPurityReports);
        ArrayAdapter<WaterReport> listViewAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listPurityReports);
        lvPurityReports.setAdapter(listViewAdapter);

    }
}
