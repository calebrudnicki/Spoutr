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
