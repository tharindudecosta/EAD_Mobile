package com.example.mobileapplication.api;

import com.example.mobileapplication.entity.LoginRequest;
import com.example.mobileapplication.entity.LoginResponse;
import com.example.mobileapplication.entity.RegisterRequest;
import com.example.mobileapplication.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginApi {

    @POST("api/User/login")
    Call<LoginResponse> login(@Body LoginRequest model);

    @POST("/api/User")
    Call<RegisterRequest> Register(@Body RegisterRequest request);

    @GET("api/User/{id}")
    Call<User> getUser(@Path("id") String customerId);
}
