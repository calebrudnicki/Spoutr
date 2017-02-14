package com.example.calebrudnicki.spoutr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button bLogin;
    private TextView registerLink;
    private Model modelHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        registerLink = (TextView) findViewById(R.id.tvRegisterHere);
        modelHelper = new Model();

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

    }

    protected void onLoginPressed(View view) {
        for (User u : modelHelper.getAllUsers()) {
            if ((etUsername.getText().toString().equals(u.getUsername())) && (etPassword.getText().toString().equals(u.getPassword()))) {
                Log.d("SUCCESS", "Login SUCCESSFUL");
                Intent userAreaIntent = new Intent(LoginActivity.this, UserAreaActivity.class);
                LoginActivity.this.startActivity(userAreaIntent);
            } else {
                Log.d("FAILURE", "Login FAILED");
            }
        }
    }
}
