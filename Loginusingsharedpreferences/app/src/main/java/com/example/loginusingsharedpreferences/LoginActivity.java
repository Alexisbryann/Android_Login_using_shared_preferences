package com.example.loginusingsharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private Button mLogin;
    private TextView mRegister;
    private String mUname;
    private String mPword;

    public static  String MyPreferences = "myPrefs";
    private SharedPreferences mSharedPreferences;
    private final String USERNAME = "username";
    private final String KEY_PASSWORD = "password";
    private String mUname1;
    private String mPword1;
    private String mUname2;
    private String mPword2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activtiy);

        mUsername = findViewById(R.id.edt_username);
        mPassword = findViewById(R.id.edt_password);
        mLogin = findViewById(R.id.button_login);
        mRegister = findViewById(R.id.tv_register);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               validateUser();
            }
        });
    }
    public void validateUser(){

        mSharedPreferences = getSharedPreferences(MyPreferences,MODE_PRIVATE);

        mUname1 = mUsername.getText().toString();
        mPword1 = mPassword.getText().toString();

        mUname2 = mSharedPreferences.getString("Username","");
        mPword2 = mSharedPreferences.getString("Password","");

        if (mUname1.equals(mUname2) && mPword1.equals(mPword2)&&!mUname2.isEmpty()&&!mPword2.isEmpty()){
            Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            mUsername.setError("Invalid Username");
            mPassword.setError("Invalid password");
        }
        if (mUname1.isEmpty()){mUsername.setError("Enter Username");}
        if (mPword1.isEmpty()){mPassword.setError("Enter password");}
    }
}
