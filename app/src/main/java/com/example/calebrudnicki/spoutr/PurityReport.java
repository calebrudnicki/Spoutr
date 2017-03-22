package com.example.calebrudnicki.spoutr;

import android.icu.text.PluralRules;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by calebrudnicki on 3/11/17.
 */

public class PurityReport implements Parcelable {

    private User submitter;
    private String dateSubmitted;
    private Location location;
    private String locationString;
    private String condition;
    private int virusPPM;
    private int contaminantPPM;
    private int reportNumber;
    private static int reportCounter = 0;

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

    /**
     * This function overrides the toString method to display purity reports in the list view
     * @return the string of some data from the purity report
     */
    @Override
    public String toString() {
        return this.getCondition() + " - " + this.getVirusPPM() + " - " + this.getContaminantPPM();
    }

    /**
     * This function returns the month that the purity report was submitted
     *
     * @return the month it was submitted
     */
    public int getMonthSubmitted() {
        int monthSubmitted = 0;
        String str = String.valueOf(this.getDateSubmitted().charAt(0));
        if (str.equals("0")) {
            str = String.valueOf(this.getDateSubmitted().charAt(1));
            monthSubmitted = Integer.parseInt(str);
        } else {
            str += String.valueOf(this.getDateSubmitted().charAt(1));
            monthSubmitted = Integer.parseInt(str);
        }
        return monthSubmitted;
    }

    //Parcelable Stuff

    /**
     * This function is the parcelable constructor for making a new user
     * @param in Parcel the user's parcel info
     */
    private PurityReport(Parcel in) {
        submitter = (User) in.readSerializable();
        dateSubmitted = in.readString();
        location = in.readParcelable(Location.class.getClassLoader());
        locationString = in.readString();
        condition = in.readString();
        virusPPM = in.readInt();
        contaminantPPM = in.readInt();
        reportNumber = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(submitter);
        dest.writeString(dateSubmitted);
        dest.writeParcelable(location, flags);
        dest.writeString(locationString);
        dest.writeString(condition);
        dest.writeInt(virusPPM);
        dest.writeInt(contaminantPPM);
        dest.writeInt(reportNumber);
    }

    public static final Parcelable.Creator<PurityReport> CREATOR = new Parcelable.Creator<PurityReport>() {
        public PurityReport createFromParcel(Parcel in) {
            return new PurityReport(in);
        }
        public PurityReport[] newArray(int size) {
            return new PurityReport[size];
        }
    };

}
