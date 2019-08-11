package com.example.citrixcommute;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    DatabaseHelper dbHelper;
    private static final String TAG = "SignUp";
    private Button signup_btn;
    private EditText edName;
    private EditText edEmail;
    private EditText edPwd;
    private EditText edPwd2;
    private EditText edAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dbHelper = new DatabaseHelper(this);
        signup_btn = (Button)findViewById(R.id.btn_signup);
        edName = (EditText)findViewById(R.id.ed_name);
        edEmail = (EditText)findViewById(R.id.ed_email);
        edPwd = (EditText)findViewById(R.id.ed_pwd);
        edPwd2 = (EditText)findViewById(R.id.ed_pwd_2);
        edAddress = (EditText)findViewById(R.id.ed_address);


        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = AddData();
                if (result) {
                    Intent intent = new Intent(SignUp.this, Home.class);
                    startActivity(intent);
                }
            }
        });

    }

    public boolean AddData() {

        boolean isInserted = dbHelper.addData(edName.getText().toString(),
                        edPwd.getText().toString(),
                        edEmail.getText().toString(),
                        edAddress.getText().toString(),
                        "null", 0, false);

        if (isInserted)
            Toast.makeText(SignUp.this, "Data is inserted successfully", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(SignUp.this, "Data is NOT inserted successfully", Toast.LENGTH_LONG).show();

        return isInserted;
    }


    @Override
    public void onStop() {
        super.onStop();
        finish();
    }
}
