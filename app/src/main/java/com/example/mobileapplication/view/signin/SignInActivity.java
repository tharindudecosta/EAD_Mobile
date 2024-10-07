package com.example.mobileapplication.view.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.api.LoginApi;
import com.example.mobileapplication.entity.LoginRequest;
import com.example.mobileapplication.entity.LoginResponse;
import com.example.mobileapplication.entity.User;
import com.example.mobileapplication.helper.DatabaseHelper;
import com.example.mobileapplication.helper.RetrofitService;
import com.example.mobileapplication.utils.AlertBoxUtil;
import com.example.mobileapplication.utils.SystemUtils;
import com.example.mobileapplication.utils.Utils;
import com.example.mobileapplication.view.main.MainActivity;
import com.example.mobileapplication.view.signup.SignUpActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * References
 *
 * https://github.com/kumaar8293/Simple-Login-Page-for-Android
 *
 * */
public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView appLogo;
    private TextView tvWelcomeThere;
    private TextView tvSignInToContinue;
    private TextInputLayout username, password;
    private Button goButton, signUpButton;
    private TextInputEditText editUserName, editPassword;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initView();
    }

    private void initView() {
        appLogo = findViewById(R.id.logo_image);
        tvWelcomeThere = findViewById(R.id.welcome_message);
        tvSignInToContinue = findViewById(R.id.sign_in_message);
        username = findViewById(R.id.email);
        password = findViewById(R.id.password);
        goButton = findViewById(R.id.goButton);
        signUpButton = findViewById(R.id.signUpButton);

        editPassword = findViewById(R.id.editPassword);
        editUserName = findViewById(R.id.editEmail);

        goButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);

    }

    private void openSignUpPage() {

        //Attach all the elements those you want to animate in design
        Pair[] pairs = new Pair[7];
        pairs[0] = new Pair<View, String>(appLogo, "app_icon2");
        pairs[1] = new Pair<View, String>(tvWelcomeThere, getString(R.string.welcome));
        pairs[2] = new Pair<View, String>(tvSignInToContinue, getString(R.string.signup_to_start_your_new_journey));
        pairs[3] = new Pair<View, String>(username, getString(R.string.username));

        pairs[4] = new Pair<View, String>(password, getString(R.string.password));
        pairs[5] = new Pair<View, String>(goButton, getString(R.string.sign_up));

        pairs[6] = new Pair<View, String>(signUpButton, getString(R.string.already_have_an_account_login));

        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                SignInActivity.this, pairs);


        startActivity(intent, options.toBundle());
    }

    @Override
    public void onClick(View view) {

        int viewId = view.getId();
        if (viewId == R.id.goButton) {
            loginValidation();
        } else if (viewId == R.id.signUpButton) {
            openSignUpPage();
        }
    }

    private void loginValidation() {
        if (Utils.inputValidation(editUserName)) {
            username.setErrorEnabled(false);

            if (Utils.inputValidation(editPassword)) {
                SystemUtils.hideKeyBoard(this);
                password.setErrorEnabled(false);

                LoginRequest loginRequest = new LoginRequest(editUserName.getText().toString(), editPassword.getText().toString());
                executeLogin(loginRequest);

                successAlertBox();


            } else {
                password.setError("Please enter your password");
            }
        } else {
            username.setError("Please enter your username");

        }

    }

    private void successAlertBox() {

        AlertBoxUtil.showSuccessAlertBox(this, "Sign up successful", new AlertBoxUtil.DialogCallback() {
            @Override
            public void onOkClick() {
                editUserName.setText("");
                editPassword.setText("");
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancelClick() {
            }
        });
    }

    private void failureAlertBox() {

        AlertBoxUtil.showFailureAlertBox(this, "Sign up successful", new AlertBoxUtil.DialogCallback() {
            @Override
            public void onOkClick() {
            }

            @Override
            public void onCancelClick() {
            }
        });
    }

    private void executeLogin(LoginRequest loginRequest) {

        RetrofitService retrofitService = new RetrofitService();
        LoginApi productsApi = retrofitService.getRetrofit().create(LoginApi.class);

        productsApi.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    getCustomer(loginResponse.getToken());
                } else {
                    failureAlertBox();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println(t);
            }
        });

    }

    private void getCustomer(String customerId) {
        RetrofitService retrofitService = new RetrofitService();
        LoginApi loginApi = retrofitService.getRetrofit().create(LoginApi.class);
        loginApi.getUser(customerId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                boolean registered = databaseHelper.createSession(user.getId(), user.getEmail(), user.getPassword(), user.getName(), user.getContactNo());
                if (registered) {
                    successAlertBox();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                failureAlertBox();
            }
        });

    }

}
