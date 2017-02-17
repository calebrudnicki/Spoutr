package com.example.calebrudnicki.spoutr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by calebrudnicki on 2/13/17.
 */

public class Model {

    private static final Model instance = new Model();
    private static List<User> allUsers;

    /**
        This function is the constructor for model, making an empty array list
     */
    public Model() {
        allUsers = new ArrayList<>();
    }

    /**
        This function adds a user only if the passed in user's username does not already exist
        @param user User the potential new user
        @return true if user was added, false if the username was already taken
    */
    public boolean addUser(User user) {
        for (User u : allUsers) {
            if (u.getUsername().toString().equals(user.getUsername().toString())) {
                return false;
            }
        }
        allUsers.add(user);
        return true;
    }

    /**
        This function returns the list of all users
        @return the list of all users
     */
    public List<User> getAllUsers() {
        return allUsers;
    }


    /**
        This function returns an instance of the model class
        @return the list of all users
     */
    public static Model getInstance() { return instance; }



}
