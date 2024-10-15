package com.example.mobileapplication.view.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.helper.DatabaseHelper;
import com.example.mobileapplication.utils.AlertBoxUtil;
import com.example.mobileapplication.view.main.MainActivity;
import com.example.mobileapplication.view.reviews.CustomerReviews; // Make sure to import your CustomerReviews activity
import com.example.mobileapplication.view.signin.SignInActivity;

public class ProfileFragment extends Fragment {

    TextView reviews, signOut;
    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        reviews = view.findViewById(R.id.account_reviews_tv);
        signOut = view.findViewById(R.id.account_sign_out_tv);

        databaseHelper = new DatabaseHelper(getContext());

        reviews.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CustomerReviews.class);
            startActivity(intent);
        });

        signOut.setOnClickListener(v -> {
            successAlertBox();
        });

        return view;
    }

    private void successAlertBox() {

        AlertBoxUtil.showSignOutAlertBox(getContext(), "Sign out successful", new AlertBoxUtil.DialogCallback() {
            @Override
            public void onOkClick() {
                Boolean clear = databaseHelper.clearSessionTable();
                if(clear) {
                    Intent intent = new Intent(getContext(), SignInActivity.class);
                    startActivity(intent);
                } else {
                    failureAlertBox();
                }
            }

            @Override
            public void onCancelClick() {
            }
        });
    }

    private void failureAlertBox() {

        AlertBoxUtil.showFailureAlertBox(getContext(), "Sign up successful", new AlertBoxUtil.DialogCallback() {
            @Override
            public void onOkClick() {
            }

            @Override
            public void onCancelClick() {
            }
        });
    }
}
