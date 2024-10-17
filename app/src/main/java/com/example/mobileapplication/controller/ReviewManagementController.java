package com.example.mobileapplication.controller;

import android.content.Context;

import com.example.mobileapplication.api.VendorApi;
import com.example.mobileapplication.entity.Review;
import com.example.mobileapplication.entity.Vendor;
import com.example.mobileapplication.helper.DatabaseHelper;
import com.example.mobileapplication.helper.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewManagementController {
    private DatabaseHelper databaseHelper;
    private Context context;

    RetrofitService retrofitService = new RetrofitService();
    VendorApi vendorApi = retrofitService.getRetrofit().create(VendorApi.class);

    public ReviewManagementController(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    public void createReview(Review review){
        vendorApi.createVendorReview(review.getVendorId()).enqueue(new Callback<Vendor>() {
            @Override
            public void onResponse(Call<Vendor> call, Response<Vendor> response) {

            }

            @Override
            public void onFailure(Call<Vendor> call, Throwable t) {

            }
        });

    }

    public void updateReview(Review review){
        vendorApi.updateVendorReview(review.getVendorId(), review.getReviewId(),review).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response.body());
                response.body();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
}
