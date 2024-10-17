package com.example.mobileapplication.controller;

import android.content.Context;

import com.example.mobileapplication.adapter.ReviewAdapter;
import com.example.mobileapplication.api.VendorApi;
import com.example.mobileapplication.entity.Review;
import com.example.mobileapplication.entity.Vendor;
import com.example.mobileapplication.helper.DatabaseHelper;
import com.example.mobileapplication.helper.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerReviewsController {

    private DatabaseHelper databaseHelper;
    private Context context;


    public CustomerReviewsController(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    private void getReviews(List<Vendor> vendorList, List<Review> reviewList, ReviewAdapter reviewAdapter) {
        String customerId = databaseHelper.getCustomerIdFromSession();
        for (Vendor vendor : vendorList) {
            String vendorId = vendor.getId();
            String vendorName = vendor.getName();
            for (Review review : vendor.getReviews()) {
                if (review.getCustomerId().equals(customerId)) {
                    review.setVendorId(vendorId);
                    review.setVendorName(vendorName);
                    reviewList.add(review);
                }
            }
        }
        reviewAdapter.notifyDataSetChanged();
    }

    public void getVendors(List<Vendor> vendorList, List<Review> reviewList, ReviewAdapter reviewAdapter) {
        RetrofitService retrofitService = new RetrofitService();
        VendorApi vendorApi = retrofitService.getRetrofit().create(VendorApi.class);

        vendorApi.getVendors().enqueue(new Callback<List<Vendor>>() {
            @Override
            public void onResponse(Call<List<Vendor>> call, Response<List<Vendor>> response) {
                vendorList.addAll(response.body());
                getReviews(response.body(),reviewList,reviewAdapter);
            }

            @Override
            public void onFailure(Call<List<Vendor>> call, Throwable t) {
            }
        });
    }
}
