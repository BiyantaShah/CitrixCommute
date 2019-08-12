package com.example.citrixcommute;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

//    DatabaseHelper mDatabaseHelper;
    private Button login_btn, signup_btn;
    private EditText edEmail, edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        signup_btn = (Button)findViewById(R.id.btn_signup);
        login_btn = (Button) findViewById(R.id.btn_login);
        edEmail = (EditText)findViewById(R.id.ed_username);
        edPassword = (EditText) findViewById(R.id.ed_password); 

//        mDatabaseHelper = new DatabaseHelper(this);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
        
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailId = edEmail.getText().toString();
                final String password = edPassword.getText().toString();


            }
        });
}

    @Override
    public void onStop() {
        super.onStop();
        finish();
    }


}
