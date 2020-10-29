package com.example.loginusingsharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText mFirstName;
    private EditText mSecondName;
    private EditText mUsername;
    private EditText mEmail;
    private EditText mEnterPassword;
    private EditText mConfirmPassword;
    private Button mRegister;
    private String mFName;
    private String mSName;
    private String mUName;
    private String mEmail1;
    private String mEnterPass;
    private String mConfPass;

    public static  String MyPreferences = "myPrefs";
    public static final String Fname = "First name";
    public static final String Sname = "Second name";
    public static final String Uname = "Username";
    public static final String Email = "E-mail";
    public static final String Pass = "Password";
    private SharedPreferences mSharedPreferences;
    private String mUname2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        mFirstName = findViewById(R.id.edt_fname);
        mSecondName = findViewById(R.id.edt_sname);
        mUsername = findViewById(R.id.edt_username);
        mEmail = findViewById(R.id.edt_email);
        mEnterPassword = findViewById(R.id.edt_enter_pass);
        mConfirmPassword = findViewById(R.id.edt_confirm_pass);

        mRegister = findViewById(R.id.button_register);
        mSharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserDetails();
            }
        });
    }

    private boolean checkPassMatch() {
        if (!mEnterPass.equals(mConfPass)) {
            mConfirmPassword.setError(getString(R.string.pass_not_match));
        }
        return false;
    }

    private boolean checkFirstName() {
        mFName = mFirstName.getText().toString();
        boolean fnameEmpty = mFName.isEmpty();
        if (fnameEmpty)
            mFirstName.setError(getString(R.string.fname_error));
        mFirstName.requestFocus();
        return fnameEmpty;
    }

    private boolean checkSecondName() {
        mSName = mSecondName.getText().toString();
        boolean snameEmpty = mSName.isEmpty();
        if (snameEmpty)
            mSecondName.setError(getString(R.string.sname_error));
        mSecondName.requestFocus();

        return snameEmpty;
    }

    private boolean checkUserName() {
        mUName = mUsername.getText().toString();
        boolean usernameEmpty = mUName.isEmpty();
        if (usernameEmpty)
            mUsername.setError(getString(R.string.username_error));
        mUsername.requestFocus();

        return usernameEmpty;
    }

    private boolean checkEmail() {
        mEmail1 = mEmail.getText().toString();
        boolean emailEmpty = mEmail1.isEmpty();
        if (emailEmpty)
            mEmail.setError(getString(R.string.email_error));
        mEmail.requestFocus();
        return emailEmpty;
    }

    private boolean checkPass() {
        mEnterPass = mEnterPassword.getText().toString();
        boolean enterPassEmpty = mEnterPass.isEmpty();
        if (enterPassEmpty)
            mEnterPassword.setError(getString(R.string.password_error));
        mEnterPassword.requestFocus();

        return enterPassEmpty;
    }

    private boolean checkConfPass() {
        mConfPass = mConfirmPassword.getText().toString();
        boolean confirmPassEmpty = mConfPass.isEmpty();
        if (confirmPassEmpty)
            mConfirmPassword.setError(getString(R.string.confirm_password_error));
            mConfirmPassword.requestFocus();

        return confirmPassEmpty;
    }

    private void saveUserDetails()
    {
        boolean checkValid = (!checkFirstName() && !checkSecondName() && !checkUserName() &&
                !checkEmail() && !checkPass() && !checkConfPass() && !checkPassMatch());
        if(!checkValid) {
            Toast.makeText(RegisterActivity.this, "Please complete your registration",
                    Toast.LENGTH_LONG).show();
        }
        else
            {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(Fname, mFName);
            editor.putString(Sname, mSName);
            editor.putString(Uname, mUName);
            editor.putString(Email, mEmail1);
            editor.putString(Pass, mEnterPass);
            editor.apply();

            Toast.makeText(RegisterActivity.this, "Profile created, please Login.",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
