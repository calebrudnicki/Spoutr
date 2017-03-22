package com.example.calebrudnicki.spoutr;

import android.content.Intent;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class PurityInfoActivity extends AppCompatActivity {

    private List<PurityReport> listPurityReports = new ArrayList<>();
    private ListView lvPurityReports;
    private GraphView gHistoricalPPM;
    private Spinner spGraphType;
    private Button bUpdateGraph;
    private WaterReport selectedReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purity_info);

        gHistoricalPPM = (GraphView) findViewById(R.id.gHistoricalPPM);
        spGraphType = (Spinner) findViewById(R.id.spGraphType);
        bUpdateGraph = (Button) findViewById(R.id.bUpdateGraph);

        selectedReport = getIntent().getParcelableExtra("SELECTED_LOCATION");

        //This block of code sets up the list view
        listPurityReports = new ArrayList<>();
        for (PurityReport pr : selectedReport.getPrList()) {
            listPurityReports.add(pr);
        }

        lvPurityReports = (ListView) findViewById(R.id.lvPurityReports);
        ArrayAdapter<WaterReport> listViewAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listPurityReports);
        lvPurityReports.setAdapter(listViewAdapter);

        //This block of code sets up the adapter to display the allowable graph types in the spinner
        ArrayAdapter<String> graphTypeAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Model.purityGraphTypes);
        graphTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGraphType.setAdapter(graphTypeAdapter);

    }

    /**e
     * This function updates the graph based on what option is selected in the spinner
     * @param view View the update graph button
     */
    protected void onUpdateGraphPressed(View view) {
        int count = 0;
        gHistoricalPPM.removeAllSeries();
        if (spGraphType.getSelectedItem() == "Virus") {
            DataPoint[] dpArray = new DataPoint[selectedReport.getPrList().size()];
            for (PurityReport pr : selectedReport.getPrList()) {
                DataPoint dp = new DataPoint(pr.getMonthSubmitted(), pr.getVirusPPM());
                dpArray[count++] = dp;
            }
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dpArray);
            gHistoricalPPM.addSeries(series);
        } else {
            DataPoint[] dpArray = new DataPoint[selectedReport.getPrList().size()];
            for (PurityReport pr : selectedReport.getPrList()) {
                DataPoint dp = new DataPoint(pr.getMonthSubmitted(), pr.getContaminantPPM());
                dpArray[count++] = dp;
            }
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dpArray);
            gHistoricalPPM.addSeries(series);
        }
        gHistoricalPPM.getViewport().setMinX(1);
        gHistoricalPPM.getViewport().setMaxX(12);
        gHistoricalPPM.getViewport().setXAxisBoundsManual(true);
    }

}
