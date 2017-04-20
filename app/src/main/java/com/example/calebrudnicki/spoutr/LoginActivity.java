package com.example.calebrudnicki.spoutr;

import android.content.Intent;
import android.media.Image;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button bLogin;
    private ImageView ivLogo;
    private TextView registerLink;
    private TextView tFacebook;
    private TextView tRecover;
    private Model modelHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        ivLogo = (ImageView) findViewById(R.id.ivLogo);
        registerLink = (TextView) findViewById(R.id.tvRegisterHere);
        tRecover = (TextView) findViewById(R.id.tRecover);
        tFacebook = (TextView) findViewById(R.id.tFacebook);
        modelHelper = Model.getInstance();

        //Animation on the logo
        android.view.animation.Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
        ivLogo.startAnimation(animation);

        //This function segues the view to the register page upon clicking the register here button
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginPressed(v);
            }
        });
        tRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final GMailSender sender = new GMailSender("spoutr.team@gmail.com", "cs2340thespouts");
                DatabaseHandler db = new DatabaseHandler(LoginActivity.this);
                final User tempUser = db.getUser(etUsername.getText().toString());
                if (tempUser.getUsername() == null ) {
                    etPassword.setText("");
                    Toast toast = Toast.makeText(LoginActivity.this, "Please enter a valid username in the username field above.", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    try {
                        String subject = "Spoutr Password Recover";
                        String message = "Hello, " + tempUser.getName() + "! You have requested the Password Recovery" +
                                " option. Your current password is: " + tempUser.getPassword();
                        String email = tempUser.getEmail();
                        sender.sendMail(subject, message, "spoutr.team@gmail.com", email);
                        Toast toast = Toast.makeText(LoginActivity.this, "Email has successfully sent!", Toast.LENGTH_SHORT);
                        toast.show();
                    } catch (Exception e) {
                        Toast toast = Toast.makeText(LoginActivity.this, "The email tied to your account is invalid or the eail failed to send.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
        tFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFb(v);
            }
        });
    }

    public void goToFb (View view) {
        goToUrl( "https://www.facebook.com/Spoutr-966680500151122/");
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
    /**
     * This function logs in a new user when the login button is pressed as long as the data is verified
     * @param view View the login button
     */
    protected void onLoginPressed(View view) {
        DatabaseHandler db = new DatabaseHandler(this);
        if (db.validateLogin(etUsername.getText().toString(), etPassword.getText().toString())) {
            User u = db.getUser(etUsername.getText().toString());
            Intent homePageIntent = new Intent(LoginActivity.this, HomePageActivity.class);
            homePageIntent.putExtra("SESSION_USER", (Parcelable) u);
            LoginActivity.this.startActivity(homePageIntent);
        } else {
            Toast toast = Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT);
            toast.show();
            etPassword.setText(null);
        }
    }

}
