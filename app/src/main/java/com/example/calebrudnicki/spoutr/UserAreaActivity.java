package com.example.calebrudnicki.spoutr;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    private TextView welcomeMessage;
    private User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMessage);

        u = getIntent().getParcelableExtra("SESSION_USER");
        welcomeMessage.setText("Welcome " + u.getName());
        Log.d("CURRENT USER", "Name: " + u.getName());
        Log.d("CURRENT USER", "Username: " + u.getUsername());
        Log.d("CURRENT USER", "Password: " + u.getPassword());
        Log.d("CURRENT USER", "Account Type: " + u.getAccountType());
    }

    /**
        This function logs a new user out when the logout button is pressed
        @param view View the login button
     */
    protected void onLogoutPressed(View view) {
        Log.d("LOGOUT", "Logout SUCCESSFUL");
        Intent loginActivityIntent = new Intent(UserAreaActivity.this, LoginActivity.class);
        UserAreaActivity.this.startActivity(loginActivityIntent);
    }

    /**
     This function brings a user to the settings page when the settings button is pressed
     @param view View the settings button
     */
    protected void onSettingsPressed(View view) {
        Intent settingsActivityIntent = new Intent(UserAreaActivity.this, SettingsActivity.class);
        settingsActivityIntent.putExtra("SESSION_USER", (Parcelable) u);
        UserAreaActivity.this.startActivity(settingsActivityIntent);
    }

    /**
     This function brings a user to the submit a report page when the submit report button is pressed
     @param view View the settings button
     */
    protected void onSubmitReportPressed(View view) {
        Intent submitReportActivityIntent = new Intent(UserAreaActivity.this, SubmitReportActivity.class);
        submitReportActivityIntent.putExtra("SESSION_USER", (Parcelable) u);
        UserAreaActivity.this.startActivity(submitReportActivityIntent);
    }

}
