package com.example.citrixcommute;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signup_btn = findViewById(R.id.btn_signup);
        signup_btn = (Button)findViewById(R.id.btn_signup);

//        dbHelper = new DatabaseHelper(this);
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
                final String emailid = edEmail.getText().toString();
                final String homeaddress = edAddress.getText().toString();
                final String phone = edphone.getText().toString();

                ProfileValue profileValue = new ProfileValue();
                profileValue.setuserName(name);
                profileValue.setuserAddress(homeaddress);
                profileValue.setuserEmail(emailid);
                profileValue.setuserPhone(phone);


                final Map<String, Object> dataMap = new HashMap<String, Object>();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("djka");
                myRef.child("kiausbflkdnf").setValue("creditcarfhggggggggds");

                //myRef.updateChildren(dataMap);

                Intent intent = new Intent(SignUp.this, Home.class);
                startActivity(intent);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            if (!password.equals(verifyPass)) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                                builder.setMessage("Both passwords need to match")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(SignUp.this, Home.class);
                                SignUp.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                SignupRequest registerRequest = new SignupRequest(name, password, emailid, homeaddress, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignUp.this);
                queue.add(registerRequest);

            }
        });

    }

//    public boolean AddData() {
//
//        boolean isInserted = dbHelper.addData(edName.getText().toString(),
//                        edPwd.getText().toString(),
//                        edEmail.getText().toString(),
//                        edAddress.getText().toString(),
//                        "null", 0, false);
//
//        if (isInserted)
//            Toast.makeText(SignUp.this, "Data is inserted successfully", Toast.LENGTH_LONG).show();
//        else
//            Toast.makeText(SignUp.this, "Data is NOT inserted successfully", Toast.LENGTH_LONG).show();
//
//        return isInserted;
//    }


    @Override
    public void onStop() {
        super.onStop();
        finish();
    }
}
