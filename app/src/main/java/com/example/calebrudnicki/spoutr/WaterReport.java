package com.example.calebrudnicki.spoutr;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by kendallin on 2/21/17.
 */

public class WaterReport implements Parcelable {
    private User submitter;
    private String dateSubmitted;
    private String location;
    private String type;
    private String condition;
    private int reportNumber;

    /**
     * This function is the constructor to make a new water report
     */
    public WaterReport(User submitter, String dateSubmitted, String location, String type, String condition, int reportNumber) {
        this.submitter = submitter;
        this.dateSubmitted = dateSubmitted;
        this.location = location;
        this.type = type;
        this.condition = condition;
        this.reportNumber = reportNumber;
    }

    /**
     * This function returns the name of the submitter
     * @return the user who submitted
     */
    public User getSubmitter() { return submitter; }

    /**
     * This function returns the date that the water report was submitted
     * @return the date it was submitted
     */
    public String getDateSubmitted() { return dateSubmitted; }

    /**
     * This function returns the location of the water report
     * @return the location of water report
     */
    public String getLocation() { return location; }

    /**
     * This function returns the type of the water
     * @return the type of water
     */
    public String getType() { return type; }

    /**
     * This function returns the condition of the water
     * @return the condition of water
     */
    public String getCondition() { return condition; }

    /**
     * This function returns the report number
     * @return the report number
     */
    public int getReportNumber() { return reportNumber; }

    /**
     * This function is the parcelable constructor for making a new water report
     * @param in Parcel the water report's parcel info
     */
    private WaterReport(Parcel in) {
        submitter = (User) in.readSerializable();
        dateSubmitted = in.readString();
        location = in.readString();
        type = in.readString();
        condition = in.readString();
        reportNumber = in.readInt();
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(submitter);
        dest.writeString(dateSubmitted);
        dest.writeString(location);
        dest.writeString(type);
        dest.writeString(condition);
        dest.writeInt(reportNumber);
    }

    public static final Parcelable.Creator<WaterReport> CREATOR = new Parcelable.Creator<WaterReport>() {
        public WaterReport createFromParcel(Parcel in) { return new WaterReport(in); }
        public WaterReport[] newArray(int size) { return new WaterReport[size]; }
    };

}
