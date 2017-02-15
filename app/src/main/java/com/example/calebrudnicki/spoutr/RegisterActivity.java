package com.example.calebrudnicki.spoutr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etUsername;
    private EditText etPassword;
    private Button bRegister;
    private Model modelHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);
        modelHelper = new Model();
    }


    /**
        This function registers in a new user when the register button is pressed as long as the username is unique
        @param view View the register button
     */
    protected void onRegisterPressed(View view) {
        User u = new User(etName.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString());
        if (modelHelper.addUser(u)) {
            Log.d("SUCCESS", "Registration SUCCESSFUL");
            Intent userAreaIntent = new Intent(RegisterActivity.this, UserAreaActivity.class);
            RegisterActivity.this.startActivity(userAreaIntent);
        } else {
            Log.d("FAILURE", "Please choose another username");
        }
    }

}
