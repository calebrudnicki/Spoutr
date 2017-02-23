package com.example.calebrudnicki.spoutr;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by calebrudnicki on 2/13/17.
 */

public class Model {

    private static final Model instance = new Model();
    private static List<User> allUsers;
    private static List<WaterReport> allReports;
    public static List<String> accountTypes = Arrays.asList("User", "Worker", "Manager", "Admin");
    public static List<String> waterTypes = Arrays.asList("Well", "Stream", "River", "Spring", "Bottled", "Lake");
    public static List<String> waterConditions = Arrays.asList("Waste", "Treatable Clear", "Treatable Muddy", "Potable");

    /**
     * This function is the constructor for model, making an empty array list for users and reports
     */
    public Model() {
        allUsers = new ArrayList<>();
        allReports = new ArrayList<>();
        loadDummyData();
    }

    /**
     * This function adds a user only if the passed in user's credentials pass all of the requirements
     * @param user User the potential new user
     * @return true if user was added, false if the username was already taken
    */
    public boolean addUser(User user) {
            if (user.getName().length() < 1) {
                Log.d("REGISTRATION FAILED", "Your name needs to be more than 1 character in length");
                return false;
            } else if (user.getUsername().length() < 5) {
                Log.d("REGISTRATION FAILED", "Your username needs to be more than 5 characters in length");
                return false;
            } else if (user.getPassword().length() < 5) {
                Log.d("REGISTRATION FAILED", "Your password needs to be more than 5 characters in length");
                return false;
            }
            for (User u : allUsers) {
                if (u.getUsername().equals(user.getUsername())) {
                    Log.d("REGISTRATION FAILED", "Your username matches one of a current user");
                    return false;
            }
        }
        allUsers.add(user);
        return true;
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
     * This function updates the password of the user passed in
     * @param user User the user whose info we are updating
     * @param password String the new password
     */
    public void updateUser(User user, String password) {
        for (User u : allUsers) {
            if (u.getUsername().equals(user.getUsername())) {
                allUsers.remove(user);
                user.setPassword(password);
                allUsers.add(user);
            }
        }
    }

    /**
     * This function returns the list of all users
     * @return the list of all users
     */
    public List<User> getAllUsers() {
        return allUsers;
    }

    /**
     * This function returns the list of all the water reports
     * @return the list of all reports
     */
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
    public void loadDummyData() {
        User caleb = new User("Caleb Rudnicki", "crudnicki", "crudnicki", "User");
        allUsers.add(caleb);
        allUsers.add(new User("Kendal Lin", "kendallin", "kendallin", "User"));
        allUsers.add(new User("Chloe Belangia", "chloebelangia", "chloebelangia", "User"));
        allUsers.add(new User("Jack McCormack", "jackmccormack", "jackmccormack", "User"));
        allUsers.add(new User("Rachel Techau", "racheltechau", "racheltechau", "User"));
        allReports.add(new WaterReport(caleb, "Yesterday", "New York City", "Well", "Potable", 222));
    }

}
