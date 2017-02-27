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
    public static List<User> allUsers;
    public static List<WaterReport> allReports;
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
            for (User u : allUsers) {
                if (u.getUsername().equals(user.getUsername())) {
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
     * This function updates the email and password of the user passed in
     * @param user User the user whose info we are updating
     * @param password String the new password
     * @param email String the new email
     */
    public void updateUser(User user, String password, String email) {
        for (User u : allUsers) {
            if (u.getUsername().equals(user.getUsername())) {
                allUsers.remove(u);
                user.setEmail(email);
                user.setPassword(password);
                allUsers.add(user);
                return;
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
        User caleb = new User("Caleb Rudnicki", "crudnicki@gmail.com", "crudnicki", "crudnicki", "User");
        allUsers.add(caleb);
        allUsers.add(new User("Kendal Lin", "klin@gmail.com", "klin", "klin123", "User"));
        allUsers.add(new User("Chloe Belangia", "cbelangia@gmail.com", "cbelangia", "cbelangia", "User"));
        allUsers.add(new User("Jack McCormack", "jmccormack@gmail.com", "jmccormack", "jmccormack", "User"));
        allUsers.add(new User("Rachel Techau", "rtechau@gmail.com", "rtechau", "rtechau", "User"));
        allReports.add(new WaterReport(caleb, "Yesterday", "New York City", "Well", "Potable", 222));
    }

}
