package com.example.calebrudnicki.spoutr;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by kendallin on 2/21/17.
 */

public class WaterReport implements Parcelable {
    private User submitter;
    private String dateSubmitted;
    private Location location;
    private String locationString;
    private String type;
    private String condition;
    private int reportNumber;
    private static int reportCounter = 0;
    private List<PurityReport> prList = new ArrayList<>();

    /**
     * This function is the constructor to make a new water report
     */
    public WaterReport(User submitter, String dateSubmitted, Location location, String locationString, String type, String condition) {
        this.submitter = submitter;
        this.dateSubmitted = dateSubmitted;
        this.location = location;
        this.locationString = locationString;
        this.type = type;
        this.condition = condition;
        this.reportNumber = reportCounter++;
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
     * This function returns the date that the water report was submitted
     *
     * @return the date it was submitted
     */
    public String getDateSubmitted() {
        return dateSubmitted;
    }

    /**
     * This function returns the string location of the water report
     *
     * @return the string location of water report
     */
    public String getLocationString() {
        return locationString;
    }

    /**
     * This function returns the location of the water report
     *
     * @return the location of water report
     */
    public Location getLocation() {
        return location;
    }

    /**
     * This function returns the type of the water
     *
     * @return the type of water
     */
    public String getType() {
        return type;
    }

    /**
     * This function returns the condition of the water
     *
     * @return the condition of water
     */
    public String getCondition() {
        return condition;
    }

    /**
     * This function returns the report number
     *
     * @return the report number
     */
    public int getReportNumber() {
        return reportNumber;
    }

    /**
     * This function returns the list of purity reports
     *
     * @return the list of purity reports
     */
    public List<PurityReport> getPrList() { return prList; }

    /**
     * This function adds a purity report to a water report
     * @param pr PurityReport of this water report
     */
    public boolean addToPRList(PurityReport pr) {
        if (pr == null) {
            return false;
        } else if (pr.getSubmitter() == null) {
            return false;
        } else if (pr.getDateSubmitted() == null) {
            return false;
        } else if (pr.getLocation() == null) {
            return false;
        } else if (pr.getCondition() == null) {
            return false;
        } else if (pr.getVirusPPM() < 0) {
            return false;
        } else if (pr.getContaminantPPM() < 0) {
            return false;
        } else if (pr.getReportNumber() < 0) {
            return false;
        }
        prList.add(pr);
        return true;
    }

    /**
     * This function overrides the toString method to display water reports in the list view
     * @return the string of some data from the water report
     */
    @Override
    public String toString() {
        return this.getLocationString() + " - " + this.getType() + " - " + this.getCondition();
    }

    //Parcelable Stuff

    /**
     * This function is the parcelable constructor for making a new purity report
     * @param in Parcel the purity report's parcel info
     */
    private WaterReport(Parcel in) {
        submitter = (User) in.readSerializable();
        dateSubmitted = in.readString();
        location = in.readParcelable(Location.class.getClassLoader());
        locationString = in.readString();
        type = in.readString();
        condition = in.readString();
        reportNumber = in.readInt();
        prList = in.readArrayList(PurityReport.class.getClassLoader());
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(submitter);
        dest.writeString(dateSubmitted);
        dest.writeParcelable(location, flags);
        dest.writeString(locationString);
        dest.writeString(type);
        dest.writeString(condition);
        dest.writeInt(reportNumber);
        dest.writeList(prList);
    }

    public static final Parcelable.Creator<WaterReport> CREATOR = new Parcelable.Creator<WaterReport>() {
        public WaterReport createFromParcel(Parcel in) { return new WaterReport(in); }
        public WaterReport[] newArray(int size) { return new WaterReport[size]; }
    };

}
