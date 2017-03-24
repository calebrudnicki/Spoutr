package com.example.calebrudnicki.spoutr;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by calebrudnicki on 2/13/17.
 */

public class User implements Parcelable, Serializable {

    private String name;
    private String email;
    private String username;
    private String password;
    private String accountType;
    private int attemptedLogins;

    /**
     * This function is the constructor for making a new user
     * @param name String the user's name
     * @param email String the user's email
     * @param username String the user's username
     * @param password String the user's password
     * @param accountType String the user's type of account
     */
    public User(String name, String email, String username, String password, String accountType) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.accountType = accountType;
        this.attemptedLogins = 0;
    }

    /**
     * This function returns the name of the user
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * This function returns the email of the user
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * This function sets the email of the user
     * @param email String the user's email
     */
    public void setEmail(String email) {
        this.email = email;
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

    /**
     * This function returns the amount of login attempts by the user
     * @return the user's number of attempted logins
     */
    public int getAttemptedLogins() {
        return attemptedLogins;
    }

    /**
     * This function sets the the amount of login attempts by the user
     * @param loginAttempts int the user's amount of login attempts
     */
    public void setAttemptedLogins(int loginAttempts) {
        this.attemptedLogins = attemptedLogins++;
    }

    //Parcelable Stuff

    /**
     * This function is the parcelable constructor for making a new user
     * @param in Parcel the user's parcel info
     */
    private User(Parcel in) {
        name = in.readString();
        email = in.readString();
        username = in.readString();
        password = in.readString();
        accountType = in.readString();
        attemptedLogins = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(accountType);
        dest.writeInt(attemptedLogins);
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
