package com.example.citrixcommute;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class SignUp extends AppCompatActivity {

//    DatabaseHelper dbHelper;
    private static final String TAG = "SignUp";
    private Button signup_btn;
    private EditText edName;
    private EditText edEmail;
    private EditText edPwd;
    private EditText edPwd2;
    private EditText edAddress;
    private EditText edphone;
    private Session session;//global variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signup_btn = findViewById(R.id.btn_signup);

        signup_btn = (Button)findViewById(R.id.btn_signup);
        edName = (EditText)findViewById(R.id.ed_name);
        edEmail = (EditText)findViewById(R.id.ed_email);
        edPwd = (EditText)findViewById(R.id.ed_pwd);
        edPwd2 = (EditText)findViewById(R.id.ed_pwd_2);
        edAddress = (EditText)findViewById(R.id.ed_address);
        edphone = (EditText)findViewById(R.id.ed_phone);


        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = edName.getText().toString();
                final String password = edPwd.getText().toString();
                final String verifyPass = edPwd2.getText().toString();
                String emailid = edEmail.getText().toString();
                final String homeaddress = edAddress.getText().toString();
                final String phone = edphone.getText().toString();

                ProfileValue profileValue = new ProfileValue();
                profileValue.setuserName(name);
                profileValue.setuserAddress(homeaddress);
                profileValue.setuserEmail(emailid);
                profileValue.setuserPhone(phone);


                final Map<String, Object> dataMap = new HashMap<String, Object>();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Users");
               // myRef.child("kiausbflkdnf").setValue("creditcarfhggggggggds");
                String[] email = emailid.split("@");
                if(!email[1].equals("citrix.com")) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                        builder.setMessage("Please Enter a Citrix Email ID")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();

                }
                else {
                    dataMap.put(email[0], profileValue.toMap());
                    myRef.updateChildren(dataMap);
                    session = new Session(getApplicationContext());
                    session.setusename(name);
                    session.setuserEmail(emailid);
                    session.setuserAddress(homeaddress);
                    session.setuserPhone(phone);
                    Intent intent = new Intent(SignUp.this, Home.class);
                    startActivity(intent);
                }
            }
        });

    }



    @Override
    public void onStop() {
        super.onStop();
        finish();
    }
}
