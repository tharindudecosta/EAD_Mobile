package com.example.mobileapplication.view.signup;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileapplication.R;
import com.example.mobileapplication.helper.DatabaseHelper;
import com.example.mobileapplication.utils.AlertBoxUtil;
import com.example.mobileapplication.utils.SystemUtils;
import com.example.mobileapplication.utils.Utils;
import com.example.mobileapplication.view.signin.SignInActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/*
 * References
 *
 * https://github.com/kumaar8293/Simple-Login-Page-for-Android
 *
 * */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText editEmail, editPassword, editFullName, editPhoneNumber;
    private TextInputLayout email, password, fullName, phoneNumber;

    private Dialog dialog;
    private Button okButton;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
    }

    private void initView() {

        editFullName = findViewById(R.id.editFullname);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editPhoneNumber = findViewById(R.id.editPhoneNumber);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        fullName = findViewById(R.id.fullName);
        phoneNumber = findViewById(R.id.phoneNumber);

        databaseHelper = new DatabaseHelper(this);

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
        if (!Utils.inputValidation(editEmail)) {
            email.setError("Please enter your email");
            return;
        } else {
            email.setErrorEnabled(false);
        }
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


        boolean registered = databaseHelper.insert(editEmail.getText().toString(),editPassword.getText().toString(),editFullName.getText().toString(),editPhoneNumber.getText().toString());

        if(registered){
            Toast.makeText(this, "Continue for Sign up", Toast.LENGTH_SHORT).show();
            successAlertBox();

            editEmail.setText("");
            editPassword.setText("");
            editFullName.setText("");
            editPhoneNumber.setText("");
        } else {
            failureAlertBox();
        }



    }

    private void successAlertBox() {

        AlertBoxUtil.showSuccessAlertBox(this, "Sign up successful", new AlertBoxUtil.DialogCallback() {
            @Override
            public void onOkClick() {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }

    private void failureAlertBox() {

        AlertBoxUtil.showFailureAlertBox(this, "Sign up successful", new AlertBoxUtil.DialogCallback() {
            @Override
            public void onOkClick() {

            }
        });
    }

    @Override
    public void onClick(View view) {

        int viewId = view.getId();
        if (viewId == R.id.signUpButton) {
            inputValidation();
        } else if (viewId == R.id.alreadyHaveAccount) {
            getOnBackPressedDispatcher().onBackPressed();
        }

    }
}
