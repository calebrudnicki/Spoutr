package com.example.calebrudnicki.spoutr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by calebrudnicki on 2/13/17.
 */

public class Model {

    private static List<User> allUsers;

    public Model() {
        allUsers = new ArrayList<>();
    }

    public boolean addUser(User user) {
        for (User u : allUsers) {
            if (u.getUsername().toString().equals(user.getUsername().toString())) {
                return false;
            }
        }
        allUsers.add(user);
        return true;
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

}
