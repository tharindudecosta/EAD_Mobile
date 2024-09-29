package com.example.mobileapplication.view.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.adapter.CartAdapter;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        recyclerView = view.findViewById(R.id.order_all_orders_recycler_view);
        textView = view.findViewById(R.id.orders_empty_text_view);
        circleLoader = view.findViewById(R.id.circular_loader_layout);

        sampleData = new ArrayList<>();

        // Simulate loading delay (e.g., 2 seconds)
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Once the delay is over, load the actual data
                loadSampleData();

                if (sampleData.isEmpty()){
                    textView.setVisibility(View.VISIBLE);
                }
                circleLoader.setVisibility(View.GONE);

            }
        }, 2000); // Delay for 2 seconds

        orderSummaryAdapter = new OrderSummaryAdapter(sampleData,getContext());
        recyclerView.setAdapter(orderSummaryAdapter);

        return view;
    }

    private void loadSampleData() {
        sampleData.add(new OrderSummary("ORD001", "2023-09-21", "Shipped", 5, 150.00));
        sampleData.add(new OrderSummary("ORD002", "2023-09-20", "Delivered", 3, 90.50));
        sampleData.add(new OrderSummary("ORD003", "2023-09-19", "Pending", 2, 45.99));
        sampleData.add(new OrderSummary("ORD004", "2023-09-18", "Cancelled", 4, 120.00));
        sampleData.add(new OrderSummary("ORD005", "2023-09-17", "Processing", 6, 200.75));

        orderSummaryAdapter.notifyDataSetChanged();
    }
}