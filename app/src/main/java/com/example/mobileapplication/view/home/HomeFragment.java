package com.example.mobileapplication.view.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.adapter.HomeProductAdapter;
import com.example.mobileapplication.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Product> productList;
    private View circleLoader;
    private HomeProductAdapter homeProductAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.products_recycler_view);
        circleLoader = view.findViewById(R.id.circular_loader_layout);

        productList = new ArrayList<>();

        homeProductAdapter = new HomeProductAdapter(productList, getContext());
        recyclerView.setAdapter(homeProductAdapter);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                loadSampleData();

                if (!productList.isEmpty()) {
                    circleLoader.setVisibility(View.GONE);
                }

            }
        }, 500);

        return view;
    }

    private void loadSampleData() {

        productList.add(new Product("P001", "Product number 1", 99.99, 1, R.drawable.app_icon));
        productList.add(new Product("P002", "Product number 2", 149.99, 2, R.drawable.app_icon_x));
        productList.add(new Product("CI001", "Product number 3", 299.99, 3, R.drawable.app_icon));
        productList.add(new Product("CI001", "Product number 4", 299.99, 3, R.drawable.baseline_close_24));
        productList.add(new Product("CI001", "Product number 5", 299.99, 3, R.drawable.app_icon));
        productList.add(new Product("CI001", "Product number 6", 299.99, 3, R.drawable.app_icon));

        homeProductAdapter.notifyDataSetChanged();
    }

}
