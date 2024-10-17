package com.example.mobileapplication.view.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.adapter.OrderSummaryAdapter;
import com.example.mobileapplication.api.OrderApi;
import com.example.mobileapplication.api.ProductApi;
import com.example.mobileapplication.entity.CartItem;
import com.example.mobileapplication.entity.OrderSummary;
import com.example.mobileapplication.entity.Product;
import com.example.mobileapplication.helper.DatabaseHelper;
import com.example.mobileapplication.helper.RetrofitService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private OrderSummaryAdapter orderSummaryAdapter;
    private List<OrderSummary> sampleData;
    private TextView textView;
    private View circleLoader;
    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_orders, container, false);

        return view;
    }

    private void initView() {
        recyclerView = view.findViewById(R.id.order_all_orders_recycler_view);
        textView = view.findViewById(R.id.orders_empty_text_view);
        circleLoader = view.findViewById(R.id.circular_loader_layout);
        databaseHelper = new DatabaseHelper(getContext());

        textView.setVisibility(View.GONE);
        sampleData = new ArrayList<>();

        orderSummaryAdapter = new OrderSummaryAdapter(sampleData, getContext());
        recyclerView.setAdapter(orderSummaryAdapter);
        loadCustomerOrderData();
    }

    private void loadCustomerOrderData() {
        RetrofitService retrofitService = new RetrofitService();
        OrderApi orderApi = retrofitService.getRetrofit().create(OrderApi.class);

        orderApi.getAllOrders().enqueue(new Callback<List<OrderSummary>>() {
            @Override
            public void onResponse(@NonNull Call<List<OrderSummary>> call, @NonNull Response<List<OrderSummary>> response) {
                if(response.body()!=null) {
                    List<OrderSummary> orderSummaries = response.body();
                    Collections.sort(orderSummaries, (p1, p2) -> p2.getOrderDate().compareTo(p1.getOrderDate()));

                    cleanOrders(orderSummaries);
                    orderSummaryAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<OrderSummary>> call, Throwable t) {
                System.out.println(t);

            }
        });
    }

    private void cleanOrders(List<OrderSummary> orderSummaryList){
        RetrofitService retrofitService = new RetrofitService();
        ProductApi productApi = retrofitService.getRetrofit().create(ProductApi.class);

        String customerId = databaseHelper.getCustomerIdFromSession();
        List<OrderSummary> orderSummaryListOutput = new ArrayList<>();

        for(OrderSummary orderSummary: orderSummaryList){
            List<Product> productList = new ArrayList<>();
            List<CartItem> cartItemList = new ArrayList<>();

            if (orderSummary.getCustomerId().equals(customerId)) {
                for (String productId : orderSummary.getProductIds()) {
                    productApi.getProductById(productId).enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
                            Product product = response.body();
                            CartItem cartItem = new CartItem();

                            if (product != null) {

                                cartItem.setCartItemId(product.getId());
                                cartItem.setTitle(product.getProductName());
                                cartItem.setTotalPrice(product.getQuantity());
                                cartItem.setImageResource(R.drawable.ad_3);

                                product.setImageResource(R.drawable.ad_3);
                            }

                            productList.add(product);
                            cartItemList.add(cartItem);
                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {
                            System.out.println(t);
                        }
                    });
                }
            }
            orderSummary.setProductList(productList);
            orderSummary.setCartItems(cartItemList);
            orderSummaryListOutput.add(orderSummary);
        }

        sampleData.addAll(orderSummaryListOutput);
        if (sampleData.isEmpty()) {
            textView.setVisibility(View.VISIBLE);
        }
        circleLoader.setVisibility(View.GONE);

    }
    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

}