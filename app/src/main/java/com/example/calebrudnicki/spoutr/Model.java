package com.example.calebrudnicki.spoutr;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by calebrudnicki on 2/13/17.
 */

public class Model {

    private static final Model instance = new Model();
    public static List<User> allUsers;
    public static List<PurityReport> allPurityReports;
    public static List<String> accountTypes = Arrays.asList("User", "Worker", "Manager", "Admin");
    public static List<String> waterTypes = Arrays.asList("Well", "Stream", "River", "Spring", "Bottled", "Lake");
    public static List<String> waterConditions = Arrays.asList("Waste", "Treatable Clear", "Treatable Muddy", "Potable");
    public static List<String> purityConditions = Arrays.asList("Safe", "Treatable", "Unsafe");
    public static List<String> purityGraphTypes = Arrays.asList("Virus", "Contaminant");

    /**
     * This function is the constructor for model, making an empty array list for users and reports
     */
    public Model() {
        allUsers = new ArrayList<>();
        allPurityReports = new ArrayList<>();
        loadDummyData();
    }

    /**
     * This function adds a user only if the passed in user's credentials pass all of the requirements
     * @param user User the potential new user
     * @return true if user was added, false if the username was already taken
     */
    public boolean addUser(User user) {
        if (user.getName().length() < 5) {
            return false;
        } else if (user.getUsername().length() < 6) {
            return false;
        } else if (user.getEmail().length() < 7 && user.getEmail().contains("@")) {
            return false;
        } else if (user.getPassword().length() < 6) {
            return false;
        }
        for (User u : allUsers) {
            if (u.getUsername().equals(user.getUsername())) {
                return false;
            }
        }
        allUsers.add(user);
        return true;
    }

    /**
     * This returns the list of all users
     * @return the list of all users
     */
    public List<User> getAllUsers() {
        return allUsers;
    }

    /**
     * This function adds a purity report to the list of water reports
     * @param pr PurityReport the new purity report
     * @return true when the purity report was added
     */
    public static boolean addPurityReport(PurityReport pr) {
        if (pr.getLocation() == null) {
            return false;
        }
        allPurityReports.add(pr);
        return true;
    }

    /**
     * This function returns an instance of the model class
     * @return the list of all users
     */
    public static Model getInstance() { return instance; }

    /**
     * This functions loads dummy data into the model to allow for smooth testing
     */
    private void loadDummyData() {
        User bob = new User("Bob Waters", "bwater@gatech.edu", "bobwaters", "csiffun", "User");

        Location coopersquare = new Location("Cooper Square");
        coopersquare.setLatitude(40.728484);
        coopersquare.setLongitude(-73.990653);

        Location ocala = new Location("Ocala");
        ocala.setLatitude(29.187199);
        ocala.setLongitude(-82.140092);

        Location loomischaffee = new Location("Loomis Chaffee");
        loomischaffee.setLatitude(41.844137);
        loomischaffee.setLongitude(-72.639610);

//        allReports.add(new WaterReport(bob, "12/15/1996 14:15:32", coopersquare, "New York City", "Well", "Potable"));
//        allReports.add(new WaterReport(bob, "04/29/1997 10:42:54", ocala, "Ocala", "Bottled", "Muddy"));
        WaterReport lcWR = new WaterReport(bob, "03/09/2001 23:37:01", loomischaffee, "Windsor", "River", "Potable");

        PurityReport lcPR1 = new PurityReport(bob, "01/21/2017", loomischaffee, "Safe", 23, 45);
        PurityReport lcPR2 = new PurityReport(bob, "03/21/2017", loomischaffee, "Safe", 33, 10);
        PurityReport lcPR3 = new PurityReport(bob, "05/21/2017", loomischaffee, "Safe", 11, 82);
        PurityReport lcPR4 = new PurityReport(bob, "08/21/2017", loomischaffee, "Safe", 36, 32);

        allPurityReports.add(lcPR1);
        allPurityReports.add(lcPR2);
        allPurityReports.add(lcPR3);
        allPurityReports.add(lcPR4);

        lcWR.addToPRList(lcPR1);
        lcWR.addToPRList(lcPR2);
        lcWR.addToPRList(lcPR3);
        lcWR.addToPRList(lcPR4);
//        allReports.add(lcWR);

    }
}
