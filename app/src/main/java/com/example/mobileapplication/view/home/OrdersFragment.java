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
import com.example.mobileapplication.entity.OrderSummary;
import com.example.mobileapplication.helper.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersFragment extends Fragment {

    private RecyclerView recyclerView;
    private OrderSummaryAdapter orderSummaryAdapter;
    private List<OrderSummary> sampleData;
    private TextView textView;
    private View circleLoader;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        recyclerView = view.findViewById(R.id.order_all_orders_recycler_view);
        textView = view.findViewById(R.id.orders_empty_text_view);
        circleLoader = view.findViewById(R.id.circular_loader_layout);

        sampleData = new ArrayList<>();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                loadCustomerOrderData();

                if (sampleData.isEmpty()) {
                    textView.setVisibility(View.VISIBLE);
                }
                circleLoader.setVisibility(View.GONE);

            }
        }, 2000);

        orderSummaryAdapter = new OrderSummaryAdapter(sampleData, getContext());
        recyclerView.setAdapter(orderSummaryAdapter);

        return view;
    }

    private void loadCustomerOrderData() {
        RetrofitService retrofitService = new RetrofitService();
        OrderApi orderApi = retrofitService.getRetrofit().create(OrderApi.class);

        orderApi.getCustomerOrders("1").enqueue(new Callback<List<OrderSummary>>() {
            @Override
            public void onResponse(@NonNull Call<List<OrderSummary>> call, @NonNull Response<List<OrderSummary>> response) {
                if(response.body()!=null) {
                    sampleData.addAll(response.body());
                    orderSummaryAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<OrderSummary>> call, Throwable t) {

            }
        });
    }
}