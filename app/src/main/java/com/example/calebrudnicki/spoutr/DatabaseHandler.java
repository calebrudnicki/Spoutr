package com.example.calebrudnicki.spoutr;

/**
 * Created by Jack on 3/14/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Spoutr Database";
    private static final String TABLE_USERINFO = "userinfo";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ACCOUNT = "account";
    private static final String KEY_ID = "id";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERINFO_TABLE = "CREATE TABLE " + TABLE_USERINFO + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT," + KEY_NAME + " TEXT," + KEY_EMAIL
                + " TEXT, " + KEY_ACCOUNT + " TEXT" + ")";
        db.execSQL(CREATE_USERINFO_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERINFO);

        // Create tables again
        onCreate(db);
    }

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

        // Inserting Row
        db.insert(TABLE_USERINFO, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
        return true;
    }

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

    public boolean validateLogin(String username, String password) {
        String selectQuery = "SELECT * FROM " + TABLE_USERINFO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                System.out.println(cursor.getString(1));
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

    public int updateInfo(User user, String password, String email) {
        String username = user.getUsername();
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PASSWORD, password);
        values.put(KEY_EMAIL, email);

        //Updating row
        return db.update(TABLE_USERINFO, values, KEY_USERNAME + " = ?", new String[]{username});
    }

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
}