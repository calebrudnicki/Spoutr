package com.example.calebrudnicki.spoutr;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etUsername;
    private EditText etPassword;
    private TextView badRegister;
    private Spinner spAccountType;
    private Button bRegister;
    private Button bCancelRegister;
    private Model modelHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        spAccountType = (Spinner) findViewById(R.id.spAccountType);
        badRegister = (TextView) findViewById(R.id.badRegister);
        badRegister.setVisibility(View.INVISIBLE);
        bRegister = (Button) findViewById(R.id.bRegister);
        bCancelRegister = (Button) findViewById(R.id.bCancelRegister);
        modelHelper =  Model.getInstance();

        //This block of code sets up the adapter to display the allowable account types in the spinner
        ArrayAdapter<String> accountTypeAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Model.accountTypes);
        accountTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAccountType.setAdapter(accountTypeAdapter);
    }


    /**
     * This function registers in a new user when the register button is pressed as long as the username is unique
     * @param view View the register button
     */
    protected void onRegisterPressed(View view) {
        if (etPassword.getText().toString().length() < 2) {
            badRegister.setText("Failed to register user. Password must be longer.");
            badRegister.setVisibility(View.VISIBLE);
        }
        if (etUsername.getText().toString().length() < 2) {
            badRegister.setText("Failed to register user. Username must be longer.");
            badRegister.setVisibility(View.VISIBLE);
        }
        if (etEmail.getText().toString().length() < 2 || !etEmail.getText().toString().contains("@")) {
            badRegister.setText("Failed to register user. Invalid email address.");
            badRegister.setVisibility(View.VISIBLE);
        }
        if (etFirstName.getText().toString().length() < 2 || etLastName.getText().toString().length() < 2) {
            badRegister.setText("Failed to register user. Name must be longer.");
            badRegister.setVisibility(View.VISIBLE);
        }
        User u = new User(etFirstName.getText().toString() + " " + etLastName.getText().toString(),
                etEmail.getText().toString(), etUsername.getText().toString(),
                etPassword.getText().toString(), (String) spAccountType.getSelectedItem());
        if (modelHelper.addUser(u)) {
            badRegister.setVisibility(View.INVISIBLE);
            Log.d("SUCCESS", "Registration SUCCESSFUL");
            Intent homePageIntent = new Intent(RegisterActivity.this, HomePageActivity.class);
            homePageIntent.putExtra("SESSION_USER", (Parcelable) u);
            RegisterActivity.this.startActivity(homePageIntent);
        } else {
            badRegister.setText("Failed to register user. Username already taken.");
            badRegister.setVisibility(View.VISIBLE);
        }
    }

    /**e
     * This function takes the user back to the login screen without registering a new user
     * @param view View the cancel button
     */
//    protected void onCancelRegisterPressed(View view) {
//        Intent homePageIntent = new Intent(RegisterActivity.this, HomePageActivity.class);
//        RegisterActivity.this.startActivity(homePageIntent);
//    }

}
