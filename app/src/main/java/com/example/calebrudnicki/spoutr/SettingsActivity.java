package com.example.calebrudnicki.spoutr;

import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SettingsActivity extends AppCompatActivity {

    private TextView etName;
    private TextView etUsername;
    private TextView etPassword;
    private User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        etName = (TextView) findViewById(R.id.etName);
        etUsername = (TextView) findViewById(R.id.etUsername);
        etPassword = (TextView) findViewById(R.id.etPassword);

        u = getIntent().getParcelableExtra("SESSION_USER");

        etName.setText(u.getName());
        etUsername.setText(u.getUsername());
        etPassword.setText(u.getPassword());
        Log.d("SETTINGS", "USER: " + u.getAccountType());
    }

    /**
        This function updates the user's information when the done editing button is pressed and then goes back to the user activity page
        @param view View the done editing button
     */
    protected void onDoneEditingPressed(View view) {
        u.setName(etName.getText().toString());
        u.setUsername(etUsername.getText().toString());
        u.setPassword(etPassword.getText().toString());
        Intent userAreaIntent = new Intent(SettingsActivity.this, UserAreaActivity.class);
        userAreaIntent.putExtra("SESSION_USER", (Parcelable) u);
        SettingsActivity.this.startActivity(userAreaIntent);
    }
}
