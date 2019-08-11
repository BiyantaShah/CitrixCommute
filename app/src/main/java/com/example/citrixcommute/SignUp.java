package com.example.citrixcommute;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private static final String TAG = "SignUp";
    DatabaseHelper mDatabaseHelper;
    private Button signup_btn;
    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup_btn = findViewById(R.id.btn_signup);
        etName = findViewById(R.id.ed_name);
        mDatabaseHelper = new DatabaseHelper(this);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEntry = etName.getText().toString();

                if(etName.length() != 0) {

                }
                else {
                    toastMessage("You must put something in the name field");
                }
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Home.class);
                startActivity(intent);
            }
        });

    }

    public void AddData (String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Data successfully inserted");
        }
        else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        finish();
    }
}
