package com.example.calebrudnicki.spoutr;

import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class  SettingsActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvUsername;
    private EditText etEmail;
    private EditText etPassword;
    private User u;
    private Model modelHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        tvName = (TextView) findViewById(R.id.tvName);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        modelHelper =  Model.getInstance();

        u = getIntent().getParcelableExtra("SESSION_USER");

        tvName.setText(u.getName());
        tvUsername.setText(u.getUsername());
        etEmail.setText(u.getEmail());
        etPassword.setText(u.getPassword());
        Log.d("SETTINGS", "USER: " + u.getAccountType());
    }

    /**
     * This function updates the user's password and email when the done editing button is pressed and then goes back to the user activity page
     * @param view View the done editing button
     */
    protected void onDoneEditingPressed(View view) {
        if (etPassword.getText().toString().length() > 5 && etEmail.getText().toString().contains("@")) {
            modelHelper.updateUser(u, etPassword.getText().toString(), etEmail.getText().toString());
            Intent homePageIntent = new Intent(SettingsActivity.this, HomePageActivity.class);
            homePageIntent.putExtra("SESSION_USER", (Parcelable) u);
            SettingsActivity.this.startActivity(homePageIntent);
        } else if (etPassword.getText().toString().length() <= 5) {
            Log.d("REGISTRATION FAILED", "Your password needs to be more than 5 characters in length");
        } else {
            Log.d("REGISTRATION FAILED", "Your email needs to include the @ sign");
        }
    }

    /**
     * This function goes back to the user activity page without editing the user's information
     * @param view View the cancel button
     */
    protected void onCancelEditingPressed(View view) {
        Intent homePageIntent = new Intent(SettingsActivity.this, HomePageActivity.class);
        homePageIntent.putExtra("SESSION_USER", (Parcelable) u);
        SettingsActivity.this.startActivity(homePageIntent);
    }

}
