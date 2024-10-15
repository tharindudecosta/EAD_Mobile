package com.example.mobileapplication.api;

import com.example.mobileapplication.entity.InventoryItem;
import com.example.mobileapplication.entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductApi {

    @GET("api/Product")
    Call<List<Product>> getProducts();

    @GET("api/Product/{id}")
    Call<Product> getProductById(@Path("id") String productId);

    @GET("api/Inventory")
    Call<List<InventoryItem>> getInventory();




}
