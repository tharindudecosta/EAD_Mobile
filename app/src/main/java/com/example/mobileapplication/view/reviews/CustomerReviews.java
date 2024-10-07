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
import com.example.mobileapplication.api.LoginApi;
import com.example.mobileapplication.api.VendorApi;
import com.example.mobileapplication.entity.Review;
import com.example.mobileapplication.helper.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerReviews extends AppCompatActivity {

    private RecyclerView reviewsRecyclerView;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_reviews);

        reviewsRecyclerView = findViewById(R.id.allReviews);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        reviewList = new ArrayList<>();
        getReviews();
        reviewAdapter = new ReviewAdapter(reviewList);
        reviewsRecyclerView.setAdapter(reviewAdapter);

    }


    private void getReviews(){
        RetrofitService retrofitService = new RetrofitService();
        VendorApi vendorApi = retrofitService.getRetrofit().create(VendorApi.class);

        vendorApi.getReviews("1").enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                reviewList.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {

            }
        });

    }


}
