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
import com.example.mobileapplication.entity.CartItem;
import com.example.mobileapplication.entity.OrderSummary;

import java.util.ArrayList;
import java.util.List;

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
                loadSampleData();

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

    private void loadSampleData() {

        List<CartItem> cartItems = new ArrayList<>();

        cartItems.add(new CartItem("CI001", "Product 1", 99.99, 1, R.drawable.app_icon));
        cartItems.add(new CartItem("CI001", "Product 2", 149.99, 2, R.drawable.app_icon_x));
        cartItems.add(new CartItem("CI001", "Product 3", 299.99, 3, R.drawable.app_icon));

        sampleData.add(new OrderSummary("ORD001", "2023-09-21", "Shipped", 5, 150.00, cartItems));
        sampleData.add(new OrderSummary("ORD002", "2023-09-20", "Delivered", 3, 90.50, cartItems));
        sampleData.add(new OrderSummary("ORD003", "2023-09-19", "Pending", 2, 45.99, cartItems));
        sampleData.add(new OrderSummary("ORD004", "2023-09-18", "Cancelled", 4, 120.00, cartItems));
        sampleData.add(new OrderSummary("ORD005", "2023-09-17", "Processing", 6, 200.75, cartItems));

        orderSummaryAdapter.notifyDataSetChanged();
    }
}