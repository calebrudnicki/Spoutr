package com.example.calebrudnicki.spoutr;

/**
 * Created by Jack on 3/14/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.util.Log;

import java.util.List;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Spoutr Database";

    private static final String TABLE_USERINFO = "userinfo";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ACCOUNT = "account";
    private static final String KEY_BAN = "ban";
    private static final String KEY_ID = "id";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This function will create the USER INFO and WATER REPORTS tables in the database
     * @param db an instance of the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERINFO_TABLE = "CREATE TABLE " + TABLE_USERINFO + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT," + KEY_NAME + " TEXT," + KEY_EMAIL
                + " TEXT," + KEY_ACCOUNT + " TEXT," + KEY_BAN + " TEXT" + ")";
        db.execSQL(CREATE_USERINFO_TABLE);
    }

    /**
     * This function will upgrade the database
     * @param db an instance of the database
     * @param oldVersion the old version number
     * @param newVersion the new version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERINFO);

        // Create tables again
        onCreate(db);
    }

    /**
     * This function attempts to add a User into the USER INFO table
     * @param user the user being added to the database
     * @return a boolean showing whether the user was added or not
     */
    public boolean addUser(User user) {
        //Check if Username is already taken
        if (hasUser(user)) {
            return false;
        }
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_ACCOUNT, user.getAccountType());
        values.put(KEY_BAN, "false");

        // Inserting Row
        db.insert(TABLE_USERINFO, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
        return true;
    }

    /**
     * This function takes a User and checks to see if it is contained in the table
     * @param user the User being checked in the USER INFO table
     * @return a boolean telling whether the User is in the table or not
     */
    public boolean hasUser(User user) {
        String username = user.getUsername();
        boolean hasUser = false;
        String selectQuery = "SELECT * FROM " + TABLE_USERINFO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String tempUsername = cursor.getString(1);
                if (tempUsername.equals(username)) {
                    hasUser = true;
                }
            } while (cursor.moveToNext());
        }
        db.close();
        return hasUser;
    }

    /**
     * This function takes two string, username and password, and attempts to validate
     * the login by checking them against the data in the USER INFO table.
     * @param username the username of the person attempting to log in
     * @param password the password of the person attempting to log in
     * @return a boolean telling whether the username and password are a match in the table
     */
    public boolean validateLogin(String username, String password) {
        String selectQuery = "SELECT * FROM " + TABLE_USERINFO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String tempUsername = cursor.getString(1);
                if (tempUsername.equals(username)) {
                    String tempPassword = cursor.getString(2);
                    if (tempPassword.equals(password)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } while (cursor.moveToNext());
        }
        db.close();
        return false;
    }

    /**
     * This function will update the password and email address of a User
     * @param user the user who is trying to update their info
     * @param password the new password of the user
     * @param email the new email of the user
     * @return the number of rows affected
     */
    public int updateInfo(User user, String password, String email, String ban) {
        String username = user.getUsername();
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PASSWORD, password);
        values.put(KEY_EMAIL, email);
        values.put(KEY_BAN, ban);

        //Updating row
        return db.update(TABLE_USERINFO, values, KEY_USERNAME + " = ?", new String[]{username});
    }

    /**
     * This function takes in a username and searches the USER INFO table to find the associated User
     * @param username the username of the user we are trying to get
     * @return the User that belongs to the username passed in
     */
    public User getUser(String username) {
        String selectQuery = "SELECT * FROM " + TABLE_USERINFO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        User u = new User(null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                if (username.equals(cursor.getString(1))) {
                    u = new User(cursor.getString(3), cursor.getString(4),
                                      cursor.getString(1), cursor.getString(2),
                                      cursor.getString(5));
                }
            } while (cursor.moveToNext());
        }
        db.close();
        return u;
    }

    public int banUser(String username, boolean ban) {
        User u = getUser(username);
        if (u.getUsername() != null) {
            if (ban) {
                updateInfo(u, u.getPassword(), u.getEmail(), "true");
                return 1;
            } else {
                updateInfo(u, u.getPassword(), u.getEmail(), "false");
                return 1;
            }
        } else {
            return -5;
        }
    }

    public boolean getBannedStatus(User user) {
        String selectQuery = "SELECT * FROM " + TABLE_USERINFO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String username = user.getUsername();
        boolean isBanned = false;

        if (cursor.moveToFirst()) {
            do {
                if (username.equals(cursor.getString(1))) {
                    String x = cursor.getString(6);
                    isBanned = Boolean.parseBoolean(x);
                }
            } while (cursor.moveToNext());
        }
        db.close();
        return isBanned;
    }
}