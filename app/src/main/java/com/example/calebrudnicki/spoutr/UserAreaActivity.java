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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMessage);

        User u = getIntent().getParcelableExtra("SESSION_USER");
        welcomeMessage.setText("Welcome " + u.getName());
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
}
