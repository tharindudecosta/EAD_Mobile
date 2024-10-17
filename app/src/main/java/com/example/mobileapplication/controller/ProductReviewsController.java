package com.example.mobileapplication.controller;

import android.content.Context;

import com.example.mobileapplication.api.VendorApi;
import com.example.mobileapplication.entity.Product;
import com.example.mobileapplication.entity.Review;
import com.example.mobileapplication.entity.Vendor;
import com.example.mobileapplication.helper.DatabaseHelper;
import com.example.mobileapplication.helper.RetrofitService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductReviewsController {

    private DatabaseHelper databaseHelper;
    private Context context;


    public ProductReviewsController(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    private Review getReviews(Vendor vendor) {
        String customerId = databaseHelper.getCustomerIdFromSession();
        String vendorId = vendor.getId();
        String vendorName = vendor.getName();
        for (Review review : vendor.getReviews()) {

            if (review.getCustomerId().equals(customerId)) {
                review.setVendorId(vendorId);
                review.setVendorName(vendorName);
                return review;
            }
        }
        return null;
    }

    public void getVendorReviews(Product product, ReviewCallback callback) {
        new Thread(() -> {
            RetrofitService retrofitService = new RetrofitService();
            VendorApi vendorApi = retrofitService.getRetrofit().create(VendorApi.class);
            vendorApi.getVendor(product.getVendor()).enqueue(new Callback<Vendor>() {
                @Override
                public void onResponse(Call<Vendor> call, Response<Vendor> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Review review = getReviews(response.body());

                        callback.onReviewFetched(review,response.body());
                    }
                }

                @Override
                public void onFailure(Call<Vendor> call, Throwable t) {
                    callback.onReviewFetched(null, null);
                }
            });
        }).start();
    }


    public interface ReviewCallback {
        void onReviewFetched(Review review, Vendor body);
    }
}
