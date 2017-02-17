package com.example.calebrudnicki.spoutr;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by calebrudnicki on 2/13/17.
 */

public class User implements Parcelable {

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


    //Parcelable stuff

    /**
        This function is the parcelable constructor for making a new user
        @param in Parcel the user's parcel info
     */
    private User(Parcel in) {
        name = in.readString();
        username = in.readString();
        password = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(username);
        dest.writeString(password);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }
        public User[] newArray(int size) {
            return new User[size];
        }
    };

}
