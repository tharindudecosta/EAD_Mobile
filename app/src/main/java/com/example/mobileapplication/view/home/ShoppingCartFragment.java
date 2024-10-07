package com.example.mobileapplication.view.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.adapter.CartAdapter;
import com.example.mobileapplication.constants.Constants;
import com.example.mobileapplication.entity.CartItem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartFragment extends Fragment {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<CartItem> sampleData;
    private TextView textView;
    private View circleLoader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        recyclerView = view.findViewById(R.id.cart_products_recycler_view);
        textView = view.findViewById(R.id.cart_empty_text_view);
        circleLoader = view.findViewById(R.id.circular_loader_layout);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        circleLoader.setVisibility(View.VISIBLE);


        // Initially set an empty list or loading indicator
        sampleData = new ArrayList<>();
        cartAdapter = new CartAdapter(sampleData, Constants.CART_VIEW);
        recyclerView.setAdapter(cartAdapter);

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

        return view;
    }

    private void loadSampleData() {
        sampleData.add(new CartItem("CI001","Product 1", 99.99, 1, R.drawable.app_icon));
        sampleData.add(new CartItem("CI002","Product 2", 149.99, 2, R.drawable.app_icon_x));
        sampleData.add(new CartItem("CI003","Product 3", 299.99, 3, R.drawable.app_icon));

        cartAdapter.notifyDataSetChanged();
    }
}
