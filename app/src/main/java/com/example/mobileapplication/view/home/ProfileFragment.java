package com.example.mobileapplication.view.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.view.reviews.CustomerReviews; // Make sure to import your CustomerReviews activity

public class ProfileFragment extends Fragment {

    TextView reviews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        reviews = view.findViewById(R.id.account_reviews_tv);

        reviews.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CustomerReviews.class);
            startActivity(intent);
        });

        return view;
    }
}
