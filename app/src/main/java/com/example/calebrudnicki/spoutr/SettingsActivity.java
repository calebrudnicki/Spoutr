package com.example.calebrudnicki.spoutr;

import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvUsername;
    private EditText etEmail;
    private EditText etPassword;
    private Button bCancel;
    private Button bDone;
    private User u;
    private Model modelHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        tvName = (TextView) findViewById(R.id.tvName);
        bCancel = (Button) findViewById(R.id.bCancelSettings);
        bDone = (Button) findViewById(R.id.bDoneEditing);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        modelHelper = Model.getInstance();

        u = getIntent().getParcelableExtra("SESSION_USER");

        tvName.setText(u.getName());
        tvUsername.setText(u.getUsername());
        etEmail.setText(u.getEmail());
        etPassword.setText(u.getPassword());
        Log.d("SETTINGS", "USER: " + u.getAccountType());

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelEditingPressed(v);
            }
        });

        bDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDoneEditingPressed(v);
            }
        });
    }

    /**
     * This function updates the user's password and email when the done editing button is pressed and then goes back to the user activity page
     *
     * @param view View the done editing button
     */
    protected void onDoneEditingPressed(View view) {
        if (etPassword.getText().toString().length() > 5 && (etEmail.getText().length() > 7 && etEmail.getText().toString().contains("@"))) {
            DatabaseHandler db = new DatabaseHandler(this);
            db.updateInfo(u, etPassword.getText().toString(), etEmail.getText().toString(), "false");
            u = db.getUser(u.getUsername());
            Intent homePageIntent = new Intent(SettingsActivity.this, HomePageActivity.class);
            homePageIntent.putExtra("SESSION_USER", (Parcelable) u);
            SettingsActivity.this.startActivity(homePageIntent);
        } else if (etPassword.getText().toString().length() <= 5) {
            Toast toast = Toast.makeText(this, "Editing Failed. Your password needs to be at least 6 characters in length", Toast.LENGTH_SHORT);
            toast.show();
            etPassword.setText(null);
        } else {
            Toast toast = Toast.makeText(this, "Editing Failed. Your email is either not 7 characters in length or there is no @ sign", Toast.LENGTH_SHORT);
            toast.show();
            etEmail.setText(null);
        }
    }

    /**
     * This function goes back to the user activity page without editing the user's information
     *
     * @param view View the cancel button
     */
    protected void onCancelEditingPressed(View view) {
        Intent homePageIntent = new Intent(SettingsActivity.this, HomePageActivity.class);
        homePageIntent.putExtra("SESSION_USER", (Parcelable) u);
        SettingsActivity.this.startActivity(homePageIntent);
    }
}
