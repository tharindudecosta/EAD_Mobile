package com.example.mobileapplication.view.signup;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileapplication.R;
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
            email.setError("Please enter your username");
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

        Toast.makeText(this, "Continue for Sign up", Toast.LENGTH_SHORT).show();
        successAlertBox();

    }

    private void successAlertBox() {
//        dialog = new Dialog(SignUpActivity.this);
//        dialog.setContentView(R.layout.success_alert);
//        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
//        dialog.setCancelable(false);
//
//        okButton = dialog.findViewById(R.id.okButton);
//
//        okButton.setOnClickListener(this);
//        dialog.show();

        AlertBoxUtil.showSuccessAlertBox(this, "Sign up successful", new AlertBoxUtil.DialogCallback() {
            @Override
            public void onOkClick() {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
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
