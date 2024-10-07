package com.example.mobileapplication.api;

import com.example.mobileapplication.entity.Product;
import com.example.mobileapplication.entity.Review;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VendorApi {

    @GET("api/Vendor/{vendorId}/review")
    Call<List<Review>> getReviews(@Path("id") String productId);

}
