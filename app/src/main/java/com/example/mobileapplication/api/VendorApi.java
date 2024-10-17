package com.example.mobileapplication.api;

import com.example.mobileapplication.entity.Review;
import com.example.mobileapplication.entity.Vendor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface VendorApi {

    @GET("api/Vendor")
    Call<List<Vendor>> getVendors();

    @GET("api/Vendor/{id}")
    Call<Vendor> getVendor(@Path("id") String vendorId);

    @POST("api/Vendor/{vendorId}/review")
    Call<Vendor> createVendorReview(@Path("vendorId") String vendorId);

    @PUT("api/Vendor/{vendorId}/review/{reviewId}")
    Call<String> updateVendorReview(@Path("vendorId") String vendorId, @Path("reviewId") String reviewId, @Body Review review);

}
