package com.example.mobileapplication.view.signup;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileapplication.R;
import com.example.mobileapplication.utils.SystemUtils;
import com.example.mobileapplication.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/*
 * References
 *
 * https://github.com/kumaar8293/Simple-Login-Page-for-Android
 *
 * */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText editUserName, editPassword, editFullName, editPhoneNumber, editEmail;
    private TextInputLayout username, password, fullName, phoneNumber, emailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
    }

    private void initView() {

        editFullName = findViewById(R.id.editFullname);
        editUserName = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        editPhoneNumber = findViewById(R.id.editPhoneNumber);
//        editEmail = findViewById(R.id.editEmailId);
        username = findViewById(R.id.email);
        password = findViewById(R.id.password);
        fullName = findViewById(R.id.fullName);
        phoneNumber = findViewById(R.id.phoneNumber);
        emailId = findViewById(R.id.email);

        findViewById(R.id.alreadyHaveAccount).setOnClickListener(this);
        findViewById(R.id.signUpButton).setOnClickListener(this);
    }

    private void inputValidation() {
        SystemUtils.hideKeyBoard(this);

        if (!Utils.inputValidation(editFullName)) {
            fullName.setError("Please enter your Fullname");
            return;
        } else {
            fullName.setErrorEnabled(false);
        }
        if (!Utils.inputValidation(editUserName)) {
            username.setError("Please enter your username");
            return;
        } else {
            username.setErrorEnabled(false);
        }
//        if (!Utils.inputValidation(editEmail)) {
//            emailId.setError("Please enter your email id");
//            return;
//        } else {
//            emailId.setErrorEnabled(false);
//        }
        if (!Utils.inputValidation(editPhoneNumber)) {
            phoneNumber.setError("Please enter your phone no");
            return;
        } else {
            phoneNumber.setErrorEnabled(false);
        }
        if (!Utils.inputValidation(editPassword)) {
            password.setError("Please enter your password");
            return;
        } else {
            password.setErrorEnabled(false);

        }

        Toast.makeText(this, "Continue for Sign up", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {

        int viewId = view.getId();
        if (viewId == R.id.signUpButton) {
            inputValidation();
        } else if (viewId == R.id.alreadyHaveAccount) {
//            onBackPressed();
            getOnBackPressedDispatcher().onBackPressed();
        }

    }
}
