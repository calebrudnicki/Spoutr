package com.example.calebrudnicki.spoutr;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.view.View;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private TextView badLogin;
    private Button bLogin;
    private TextView registerLink;
    private Model modelHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        badLogin = (TextView) findViewById(R.id.badLogin);
        badLogin.setVisibility(View.INVISIBLE);
        bLogin = (Button) findViewById(R.id.bLogin);
        registerLink = (TextView) findViewById(R.id.tvRegisterHere);
        modelHelper = Model.getInstance();

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
        This function logs in a new user when the login button is pressed as long as the data is verified
        @param view View the login button
     */
    protected void onLoginPressed(View view) {
        boolean hasLogged = false;
        for (User u : modelHelper.getAllUsers()) {
            if ((etUsername.getText().toString().equals(u.getUsername())) && (etPassword.getText().toString().equals(u.getPassword()))) {
                hasLogged = true;
                Intent homePageIntent = new Intent(LoginActivity.this, HomePageActivity.class);
                homePageIntent.putExtra("SESSION_USER", (Parcelable) u);
                LoginActivity.this.startActivity(homePageIntent);
            }
        }
        //If the user failed to log in, display a message
        if (!hasLogged) {
            badLogin.setVisibility(View.VISIBLE);
            etPassword.setText(null);
        }
    }

    @Override
    public void onResume() {
        //Resets the login page after back button has been pressed
        super.onResume();
        etUsername.setText(null);
        badLogin.setVisibility(View.INVISIBLE);
        etUsername.requestFocus();
    }
}
