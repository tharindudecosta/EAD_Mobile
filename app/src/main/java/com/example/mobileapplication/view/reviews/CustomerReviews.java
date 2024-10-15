package com.example.mobileapplication.view.reviews;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.adapter.ReviewAdapter; // Assuming you have this adapter class
import com.example.mobileapplication.api.VendorApi;
import com.example.mobileapplication.entity.Review;
import com.example.mobileapplication.entity.Vendor;
import com.example.mobileapplication.helper.DatabaseHelper;
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
    private List<Vendor> vendorList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_reviews);
        databaseHelper = new DatabaseHelper(this);

        reviewsRecyclerView = findViewById(R.id.allReviews);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        reviewList = new ArrayList<>();
        vendorList = new ArrayList<>();
        getReviews();
        reviewAdapter = new ReviewAdapter(reviewList);
        reviewsRecyclerView.setAdapter(reviewAdapter);

    }

    private void getReviews(){
        String customerId = databaseHelper.getCustomerIdFromSession();
        getVendors();
        for (Vendor vendor:vendorList){
            String vendorId = vendor.getId();
            String vendorName = vendor.getName();
            for(Review review:vendor.getReviews()){
                if(review.getCustomerId().equals(customerId)){
                    review.setVendorId(vendorId);
                    review.setVendorName(vendorName);
                    reviewList.add(review);
                }
            }
        }

        reviewAdapter.notifyDataSetChanged();

    }

    private void getVendors(){
        RetrofitService retrofitService = new RetrofitService();
        VendorApi vendorApi = retrofitService.getRetrofit().create(VendorApi.class);

        vendorApi.getVendors().enqueue(new Callback<List<Vendor>>() {
            @Override
            public void onResponse(Call<List<Vendor>> call, Response<List<Vendor>> response) {
                vendorList.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Vendor>> call, Throwable t) {

            }
        });

    }


}
