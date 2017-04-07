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
import android.widget.Toast;

import org.w3c.dom.Text;

public class  SettingsActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        Model modelHelper = Model.getInstance();

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
        if (etPassword.getText().toString().length() > 5 && (etEmail.getText().length() > 7 && etEmail.getText().toString().contains("@"))) {
            DatabaseHandler db = new DatabaseHandler(this);
            db.updateInfo(u, etPassword.getText().toString(), etEmail.getText().toString());
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
     * @param view View the cancel button
     */
    protected void onCancelEditingPressed(View view) {
        Intent homePageIntent = new Intent(SettingsActivity.this, HomePageActivity.class);
        homePageIntent.putExtra("SESSION_USER", (Parcelable) u);
        SettingsActivity.this.startActivity(homePageIntent);
    }

}
