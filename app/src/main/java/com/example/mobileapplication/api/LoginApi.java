package com.example.mobileapplication.api;

import com.example.mobileapplication.entity.LoginRequest;
import com.example.mobileapplication.entity.LoginResponse;
import com.example.mobileapplication.entity.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {

    @POST("api/User/login")
    Call<LoginResponse> login(@Body LoginRequest model);

    @POST("/api/User")
    Call<RegisterRequest> Register(@Body RegisterRequest request);
}
