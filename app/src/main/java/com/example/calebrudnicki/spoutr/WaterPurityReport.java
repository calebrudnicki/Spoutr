package com.example.calebrudnicki.spoutr;

import java.util.Date;
import android.location.Location;

/**
 * Created by Rachel on 3/7/2017.
 */

public class WaterPurityReport {
    private User submitter;
    private static int reportNumber = 0;
    private Date dateSubmitted;
    private Location location;
    private String locationString;
    private String type;
    private String condition;
    private int virusPPM;
    private int contaminantPPM;

    /**
     * TMakes a new water purity report
     */
    public WaterPurityReport(User submitter, Date dateSubmitted, Location location, String locationString,
                             String type, String condition, int reportNumber, int virusPPM, int contaminantPPM) {
        this.submitter = submitter;
        this.dateSubmitted = dateSubmitted;
        this.location = location;
        this.locationString = locationString;
        this.type = type;
        this.condition = condition;
        this.reportNumber = reportNumber;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
        reportNumber++;
    }

    /**
     * Gets the account that submitted the report
     * @return the account that submitted the report
     */
    public User getSubmitter() {
        return submitter;
    }

    /**
     * Sets the submitter of the report
     * @param submitter the account that submits the report
     */
    public void setSubmitter(User submitter) {
        this.submitter = submitter;
    }

    /**
     * Gets number of reports made
     * @return the number of reports made
     */
    public int getNumberOfReports() {
        return reportNumber;
    }

    /**
     * Gets the date that the report was submitted
     * @return the date submitted
     */
    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    /**
     * Sets the submitter of the report
     * @param dateSubmitted the account that submits the report
     */
    public void setDate(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    /**
     * Gets the location that the report applies to
     * @return the location from the report
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location in the report
     * @param location the location the report
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gets the location that the report applies to as a string
     * @return the location as a string from the report
     */
    public String getLocationString() {
        return locationString;
    }

    /**
     * Sets the location in the report as a string
     * @param locationString the location the report
     */
    public void setLocation(String locationString) {
        this.locationString = locationString;
    }

    /**
     * Gets the type the water source
     * @return the type of the water source
     */
    public String type() {
        return type;
    }

    /**
     * Sets the type of the water source
     * @param type the type of the water source
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the condition of the water stated in the report
     * @return the condition from the report
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets the condition of the water of the report
     * @param condition the condition of the water the report
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Gets the virus PPM
     * @return virus ppm
     */
    public int getVirusPPM() {
        return virusPPM;
    }

    /**
     * Sets the virus ppm
     * @param virusPPM the virus ppm to set it to
     */
    public void setVirusPPM(int virusPPM){
        this.virusPPM = virusPPM;
    }

    /**
     * Gets the contaminant PPM
     * @return contaminant ppm
     */
    public int getContaminantPPM() {
        return contaminantPPM;
    }

    /**
     * Sets the contaminant ppm
     * @param contaminantPPM the virus ppm to set it to
     */
    public void setContaminantPPM(int contaminantPPM){
        this.contaminantPPM = contaminantPPM;
    }
}
