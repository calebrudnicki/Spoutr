package com.example.calebrudnicki.spoutr;

import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by calebrudnicki on 2/13/17.
 */

public class Model {

    private static final Model instance = new Model();
    public static List<WaterReport> allReports;
    public static List<PurityReport> allPurityReports;
    public static List<String> accountTypes = Arrays.asList("User", "Worker", "Manager", "Admin");
    public static List<String> waterTypes = Arrays.asList("Well", "Stream", "River", "Spring", "Bottled", "Lake");
    public static List<String> waterConditions = Arrays.asList("Waste", "Treatable Clear", "Treatable Muddy", "Potable");
    public static List<String> purityConditions = Arrays.asList("Safe", "Treatable", "Unsafe");

    /**
     * This function is the constructor for model, making an empty array list for users and reports
     */
    public Model() {
        allReports = new ArrayList<>();
        allPurityReports = new ArrayList<>();
    }

    /**
     * This function adds a water report to the list of water reports
     * @param report WaterReport the new water report
     * @return true when the water report was added
     */
    public boolean addWaterReport(WaterReport report) {
        allReports.add(report);
        return true;
    }

    /**
     * This function adds a purity report to the list of water reports
     * @param pr PurityReport the new purity report
     * @return true when the purity report was added
     */
    public boolean addPurityReport(PurityReport pr) {
        if (pr.getLocation() == null) {
            return false;
        }
        allPurityReports.add(pr);
        return true;
    }

    public List<WaterReport> getAllReports() {
        return allReports;
    }

    /**
     * This function returns an instance of the model class
     * @return the list of all users
     */
    public static Model getInstance() { return instance; }

    /**
     * This functions loads dummy data into the model to allow for smooth testing
     */

}
