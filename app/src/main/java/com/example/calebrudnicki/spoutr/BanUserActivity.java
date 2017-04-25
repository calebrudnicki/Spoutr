package com.example.calebrudnicki.spoutr;

/**
 * Created by Jack on 4/24/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class BanUserActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Spinner ban;
    private Button bSubmit;
    private Button bCancel;
    private User u;
    private Model modelHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_user);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        ban = (Spinner) findViewById(R.id.spinner3);
        bSubmit = (Button) findViewById(R.id.button3);
        bCancel = (Button) findViewById(R.id.button2);
        modelHelper = Model.getInstance();

        u = getIntent().getParcelableExtra("SESSION_USER");

        ArrayList<String> list = new ArrayList<>();
        list.add("Ban");
        list.add("Unban");
        ArrayAdapter<String> banAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, list);
        banAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ban.setAdapter(banAdapter);

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelPressed(v);
            }
        });

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmitPressed(v);
            }
        });
    }

    /**
     * This function creates a new water report once the submit water report is pressed
     * @param view View the register button
     */
    protected void onSubmitPressed(View view){
        String x = (String) ban.getSelectedItem();
        boolean bool = false;
        if (x.equals("Ban")) {
            bool = true;
        } else if (x.equals("Unban")) {
            bool = false;
        }
        String username = editText.getText().toString();
        DatabaseHandler db = new DatabaseHandler(this);
        int y = db.banUser(username, bool);
        if (y == -5) {
            Toast toast = Toast.makeText(this, "Username does not exist!", Toast.LENGTH_SHORT);
            toast.show();
            editText.setText("");
        } else {
            if (bool) {
                Toast toast = Toast.makeText(this, "The User has been banned!", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Toast toast = Toast.makeText(this, "The User has been unbanned!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    /**
     * Takes the user back to the home screen without creating a new report
     * @param view View the cancel button
     */
    protected void onCancelPressed(View view) {
        Intent homePageIntent = new Intent(BanUserActivity.this, HomePageActivity.class);
        homePageIntent.putExtra("SESSION_USER", (Parcelable) u);
        BanUserActivity.this.startActivity(homePageIntent);
    }
}
