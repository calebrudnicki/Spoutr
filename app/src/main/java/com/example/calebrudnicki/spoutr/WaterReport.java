package com.example.calebrudnicki.spoutr;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by kendallin on 2/21/17.
 */

public class WaterReport {
    private String submitter;
    private String dateSubmitted;
    private String location;
    private String typeWater;
    private String condition;
    private int reportNumber;

    /**
     *
     * @param submitter String the user who submitted
     * @param dateSubmitted String date water report submitted
     * @param location String location of water report
     * @param typeWater String type of water
     * @param condition String condition of water
     * @param reportNumber int report number
     */
    public WaterReport(String submitter, String dateSubmitted,
                       String location, String typeWater,
                       String condition, int reportNumber) {
        this. submitter = submitter;
        this.dateSubmitted = dateSubmitted;
        this.location = location;
        this.typeWater = typeWater;
        this.condition = condition;
        this.reportNumber = reportNumber;
    }

    /**
     * This function returns the name of the submitter
     * @return user who submitted
     */
    public String getSubmitter() { return submitter; }

    /**
     * This function returns the date that the water report was submitted
     * @return date it was submitted
     */
    public String getDateSubmitted() { return dateSubmitted; }

    /**
     * This function returns the location of the water report
     * @return location of water report
     */
    public String getLocation() { return location; }

    /**
     * This function returns the type of the water
     * @return type of water
     */
    public String getTypeWater() { return typeWater; }

    /**
     * This function returns the condition of the water
     * @return condition of water
     */
    public String getCondition() { return condition; }

    /**
     * This function returns the report number
     * @return Report number
     */
    public int getReportNumber() { return reportNumber; }

    /**
     * This function is the parcelable constructor for making a new water report
     * @param in Parcel the water report's parcel info
     */
    private WaterReport(Parcel in) {
        submitter = in.readString();
        dateSubmitted = in.readString();
        location = in.readString();
        typeWater = in.readString();
        condition = in.readString();
        reportNumber = in.readInt();
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(submitter);
        dest.writeString(dateSubmitted);
        dest.writeString(location);
        dest.writeString(typeWater);
        dest.writeString(condition);
        dest.writeInt(reportNumber);
    }

    public static final Parcelable.Creator<WaterReport> CREATOR = new Parcelable.Creator<WaterReport>() {
        public WaterReport createFromParcel(Parcel in) { return new WaterReport(in); }
        public WaterReport[] newArray(int size) { return new WaterReport[size]; }
    };

}
