package com.example.mobileapplication.api;

import com.example.mobileapplication.entity.OrderSummary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OrderApi {

    @GET("api/Order/customer/{id}")
    Call<List<OrderSummary>> getCustomerOrders(@Path("id") String customerId);

    @GET("api/Order")
    Call<List<OrderSummary>> getAllOrders();

    @GET("api/Order/{id}")
    Call<OrderSummary> getOrderById(@Path("id") String orderId);

    @POST("api/Order")
    Call<OrderSummary> createCustomerOrder();

    @PUT("api/Order/{id}")
    Call<OrderSummary> updateCustomerOrder(@Path("id") String orderId);



}
