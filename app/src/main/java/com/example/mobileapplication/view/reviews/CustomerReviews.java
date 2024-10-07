package com.example.mobileapplication.view.reviews;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.adapter.ReviewAdapter; // Assuming you have this adapter class
import com.example.mobileapplication.entity.Review;

import java.util.ArrayList;
import java.util.List;

public class CustomerReviews extends AppCompatActivity {

    private RecyclerView reviewsRecyclerView;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_reviews);

        // Initialize the RecyclerView
        reviewsRecyclerView = findViewById(R.id.allReviews);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up the review list and adapter
        reviewList = getSampleReviews(); // You can replace this with actual data fetching logic
        reviewAdapter = new ReviewAdapter(reviewList);
        reviewsRecyclerView.setAdapter(reviewAdapter);

//        // Handle window insets
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.reviewsPage), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    // Sample data generation (replace with actual data fetching logic)
    private List<Review> getSampleReviews() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("Vendor A", "Great product! Highly recommend for anyone looking for quality.", 5));
        reviews.add(new Review("Vendor B", "Decent quality but a bit overpriced for what you get.", 3));
        reviews.add(new Review("Vendor C", "Not what I expected. The quality could be much better.", 2));
        reviews.add(new Review("Vendor D", "Excellent service and product. Will buy again!", 4));
        reviews.add(new Review("Vendor E", "Terrible experience, the item was damaged on arrival.", 1));
        return reviews;
    }
}
