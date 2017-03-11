package com.example.calebrudnicki.spoutr;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by calebrudnicki on 3/11/17.
 */

public class PurityReport {

    private User submitter;
    private String dateSubmitted;
    private Location location;
    private String locationString;
    private String condition;
    private int virusPPM;
    private int contaminantPPM;
    private static int reportNumber = 0;

    /**
     * This function is the constructor to make a new purity report
     */
    public PurityReport(User submitter, String dateSubmitted, Location location, String condition, int virusPPM, int contaminantPPM) {
        this.submitter = submitter;
        this.dateSubmitted = dateSubmitted;
        this.location = location;
        this.condition = condition;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
        reportNumber++;
    }

    /**
     * This function returns the name of the submitter
     *
     * @return the user who submitted
     */
    public User getSubmitter() {
        return submitter;
    }

    /**
     * This function returns the date that the purity report was submitted
     *
     * @return the date it was submitted
     */
    public String getDateSubmitted() {
        return dateSubmitted;
    }

    /**
     * This function returns the location of the purity report
     *
     * @return the location of purity report
     */
    public Location getLocation() {
        return location;
    }

    /**
     * This function returns the condition of the purity report
     *
     * @return the condition of water
     */
    public String getCondition() {
        return condition;
    }

    /**
     * This function returns the virus PPM of the purity report
     *
     * @return the virus PPM of the purity report
     */
    public int getVirusPPM() {
        return virusPPM;
    }

    /**
     * This function returns the contiminant PPM of the purity report
     *
     * @return the contaminant PPM of the purity report
     */
    public int getContaminantPPM() {
        return contaminantPPM;
    }

    /**
     * This function returns the report number
     *
     * @return the report number
     */
    public int getReportNumber() {
        return reportNumber;
    }

}
