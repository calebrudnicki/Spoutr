package com.example.calebrudnicki.spoutr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    private TextView welcomeMessage;
    private EditText etUsername;
    private EditText etAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMessage);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etAge = (EditText) findViewById(R.id.etAge);

        Log.d("WELCOME", "Greetings new user");
        //welcomeMessage.setText("Welcome");
        User u = getIntent().getParcelableExtra("EXTRA_SESSION_ID");
        welcomeMessage.setText("Welcome: " + u.getName());

    }
}
