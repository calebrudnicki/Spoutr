package com.example.calebrudnicki.spoutr;

/**
 * Created by calebrudnicki on 2/13/17.
 */

public class User {

    private String name;
    private String username;
    private String password;

    /**
        This function is the constructor for making a new user
        @param name String the user's name
        @param username String the user's username
        @param password String the user's password
     */
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    /**
        This function returns the name of the user
        @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
        This function returns the username of the user
        @return the user's usernname
     */
    public String getUsername() {
        return username;
    }

    /**
        This function returns the password of the user
        @return the user's password
     */
    public String getPassword() {
        return password;
    }

}
