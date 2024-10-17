package com.example.mobileapplication.view.reviews;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.adapter.ReviewAdapter; // Assuming you have this adapter class
import com.example.mobileapplication.controller.CustomerReviewsController;
import com.example.mobileapplication.entity.Review;
import com.example.mobileapplication.entity.Vendor;

import java.util.ArrayList;
import java.util.List;

public class CustomerReviews extends AppCompatActivity {

    private RecyclerView reviewsRecyclerView;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewList;
    private List<Vendor> vendorList;
    private CustomerReviewsController customerReviewsController;
    private TextView reviewsEmptyTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_reviews);

        customerReviewsController = new CustomerReviewsController(this);

        reviewsRecyclerView = findViewById(R.id.allReviews);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        reviewsEmptyTv = findViewById(R.id.reviews_empty_text_view);
        reviewsEmptyTv.setVisibility(View.GONE);

        reviewList = new ArrayList<>();
        vendorList = new ArrayList<>();

        reviewAdapter = new ReviewAdapter(reviewList, CustomerReviews.this);
        reviewsRecyclerView.setAdapter(reviewAdapter);

        customerReviewsController.getVendors(vendorList, reviewList, reviewAdapter);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                if (reviewList.isEmpty() || reviewList == null) {
                    reviewsEmptyTv.setVisibility(View.VISIBLE);
                }

            }
        }, 2000);

    }

}
