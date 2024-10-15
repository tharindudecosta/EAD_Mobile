package com.example.mobileapplication.view.splash;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileapplication.R;
import com.example.mobileapplication.helper.DatabaseHelper;
import com.example.mobileapplication.view.main.MainActivity;
import com.example.mobileapplication.view.signin.SignInActivity;

public class SplashActivity extends AppCompatActivity {

    private Animation topAnim, bottomAnim, rightToCenter;

    private Handler handler = new Handler();
    private MyRunnable myRunnable = new MyRunnable();
    private ImageView appLogo;
    private TextView appTitle;
    private TextView tagLine;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initAnimation();
        iniView();
        sendToNextActivity();
    }

    //Initialisation of all the views
    private void iniView() {

        appLogo = findViewById(R.id.image_logo);
        appTitle = findViewById(R.id.title);
        tagLine = findViewById(R.id.tag_line);

        //Setting the Animation
        appLogo.setAnimation(rightToCenter);
        appTitle.setAnimation(topAnim);
        tagLine.setAnimation(bottomAnim);

        databaseHelper = new DatabaseHelper(SplashActivity.this);

    }

    private void initAnimation() {
        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
        rightToCenter = AnimationUtils.loadAnimation(this, R.anim.right_to_center);
    }

    private void sendToNextActivity() {
        handler.postDelayed(myRunnable, 2000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (handler != null) {
            handler.removeCallbacks(myRunnable);
        }
        finish();
    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {

            Pair[] pairs = new Pair[3];
            pairs[0] = new Pair<View, String>(appLogo, "app_icon2");
            pairs[1] = new Pair<View, String>(appTitle, getString(R.string.hello_there_welcome_back));
            pairs[2] = new Pair<View, String>(tagLine, getString(R.string.sign_in_to_continue));



            if(databaseHelper.isSessionActive()){
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        SplashActivity.this, pairs);
                startActivity(intent, options.toBundle());

                onBackPressed();
            }
        }
    }
}
