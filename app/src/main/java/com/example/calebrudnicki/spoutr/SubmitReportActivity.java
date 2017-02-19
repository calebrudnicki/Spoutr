package com.example.calebrudnicki.spoutr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SubmitReportActivity extends AppCompatActivity {

    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report);

        u = getIntent().getParcelableExtra("SESSION_USER");
        Log.d("SUBMIT", "User to submit: " + u.getUsername());
    }
}
