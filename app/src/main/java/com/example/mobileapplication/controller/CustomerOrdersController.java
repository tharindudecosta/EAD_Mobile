//package com.example.mobileapplication.controller;
//
//import android.content.Context;
//import android.view.View;
//
//import androidx.annotation.NonNull;
//
//import com.example.mobileapplication.R;
//import com.example.mobileapplication.api.OrderApi;
//import com.example.mobileapplication.api.ProductApi;
//import com.example.mobileapplication.entity.CartItem;
//import com.example.mobileapplication.entity.OrderSummary;
//import com.example.mobileapplication.entity.Product;
//import com.example.mobileapplication.helper.DatabaseHelper;
//import com.example.mobileapplication.helper.RetrofitService;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class CustomerOrdersController {
//
//    private DatabaseHelper databaseHelper;
//    private Context context;
//
//    public CustomerOrdersController(Context context) {
//        this.context = context;
//        this.databaseHelper = new DatabaseHelper(context);
//    }
//
//    private void loadCustomerOrderData() {
//        RetrofitService retrofitService = new RetrofitService();
//        OrderApi orderApi = retrofitService.getRetrofit().create(OrderApi.class);
//        sampleData = new ArrayList<>();
//
//        orderApi.getAllOrders().enqueue(new Callback<List<OrderSummary>>() {
//            @Override
//            public void onResponse(@NonNull Call<List<OrderSummary>> call, @NonNull Response<List<OrderSummary>> response) {
//                if(response.body()!=null) {
//                    List<OrderSummary> orderSummaries = response.body();
//                    Collections.sort(orderSummaries, (p1, p2) -> p2.getOrderDate().compareTo(p1.getOrderDate()));
//
//                    cleanOrders(orderSummaries);
//                    orderSummaryAdapter.notifyDataSetChanged();
//                }
//            }
//            @Override
//            public void onFailure(Call<List<OrderSummary>> call, Throwable t) {
//                System.out.println(t);
//
//            }
//        });
//    }
//
//    private void cleanOrders(List<OrderSummary> orderSummaryList){
//        RetrofitService retrofitService = new RetrofitService();
//        ProductApi productApi = retrofitService.getRetrofit().create(ProductApi.class);
//
//        String customerId = databaseHelper.getCustomerIdFromSession();
//        List<OrderSummary> orderSummaryListOutput = new ArrayList<>();
//
//        for(OrderSummary orderSummary: orderSummaryList){
//            List<Product> productList = new ArrayList<>();
//            List<CartItem> cartItemList = new ArrayList<>();
//
//            if (orderSummary.getCustomerId().equals(customerId)) {
//                for (String productId : orderSummary.getProductIds()) {
//                    productApi.getProductById(productId).enqueue(new Callback<Product>() {
//                        @Override
//                        public void onResponse(Call<Product> call, Response<Product> response) {
//                            Product product = response.body();
//                            CartItem cartItem = new CartItem();
//
//                            if (product != null) {
//
//                                cartItem.setCartItemId(product.getId());
//                                cartItem.setTitle(product.getProductName());
//                                cartItem.setTotalPrice(product.getQuantity());
//                                cartItem.setImageResource(R.drawable.ad_3);
//
//                                product.setImageResource(R.drawable.ad_3);
//                            }
//
//                            productList.add(product);
//                            cartItemList.add(cartItem);
//                        }
//
//                        @Override
//                        public void onFailure(Call<Product> call, Throwable t) {
//                            System.out.println(t);
//                        }
//                    });
//                }
//            }
//            orderSummary.setProductList(productList);
//            orderSummary.setCartItems(cartItemList);
//            orderSummaryListOutput.add(orderSummary);
//        }
//
//        sampleData.addAll(orderSummaryListOutput);
//        if (sampleData.isEmpty()) {
//            textView.setVisibility(View.VISIBLE);
//        }
//        circleLoader.setVisibility(View.GONE);
//
//    }
//}
