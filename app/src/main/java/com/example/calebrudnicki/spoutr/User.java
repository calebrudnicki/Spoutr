package com.example.calebrudnicki.spoutr;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by calebrudnicki on 2/13/17.
 */

public class User implements Parcelable, Serializable {

    private String name;
    private String username;
    private String password;
    private String accountType;

    /**
     * This function is the constructor for making a new user
     * @param name String the user's name
     * @param username String the user's username
     * @param password String the user's password
     * @param accountType String the user's type of account
     */
    public User(String name, String username, String password, String accountType) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    /**
     * This function returns the name of the user
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * This function returns the username of the user
     * @return the user's usernname
     */
    public String getUsername() {
        return username;
    }

    /**
     * This function returns the password of the user
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * This function sets the password of the user
     * @param password String the user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This function returns the account type of the user
     * @return the user's account type
     */
    public String getAccountType() {
        return accountType;
    }


    //Parcelable stuff

    /**
     * This function is the parcelable constructor for making a new user
     * @param in Parcel the user's parcel info
     */
    private User(Parcel in) {
        name = in.readString();
        username = in.readString();
        password = in.readString();
        accountType = in.readString();
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
        dest.writeString(accountType);
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
