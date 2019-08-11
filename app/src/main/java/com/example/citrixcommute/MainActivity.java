package com.example.citrixcommute;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
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

        mDatabaseHelper = new DatabaseHelper(this);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
        
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = login();
                if (result) {
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Email or password is incorrect", Toast.LENGTH_LONG).show();

                }
            }
        });
}

    private boolean login() {

        Cursor result = mDatabaseHelper.queryData(edEmail.getText().toString(),
                edPassword.getText().toString());
        boolean canLogin = false;

        if (result.getCount() > 0)
            canLogin = true;
        
        return canLogin;
    }

    @Override
    public void onStop() {
        super.onStop();
        finish();
    }


}
