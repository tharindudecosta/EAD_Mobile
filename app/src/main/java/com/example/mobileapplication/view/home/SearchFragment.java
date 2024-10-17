package com.example.mobileapplication.view.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.adapter.SearchQueryAdapter;
import com.example.mobileapplication.adapter.SearchQueryProductAdapter;
import com.example.mobileapplication.api.ProductApi;
import com.example.mobileapplication.entity.Product;
import com.example.mobileapplication.helper.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;
//    private SearchQueryAdapter adapter;
    private SearchQueryProductAdapter adapter;
    private SearchView searchView;
    private List<Product> dataList;
    private TextView searchPageTopicTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setVisibility(View.GONE);

        searchView = view.findViewById(R.id.searchView);

        dataList = new ArrayList<>();

        searchPageTopicTv = view.findViewById(R.id.searchPageTopicTv);
        searchPageTopicTv.setVisibility(View.VISIBLE);
//        recyclerView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));

        loadProductData();

        adapter = new SearchQueryProductAdapter(dataList,getContext());
        recyclerView.setAdapter(adapter);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.VISIBLE);
                searchPageTopicTv.setVisibility(View.GONE);
            }
        });

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    recyclerView.setVisibility(View.VISIBLE);
                    searchPageTopicTv.setVisibility(View.GONE);
                }
                else {
                    // Hide RecyclerView when SearchView loses focus
                    recyclerView.setVisibility(View.GONE);
                    searchPageTopicTv.setVisibility(View.VISIBLE);
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return false;
            }
        });

        return view;

    }


    private void loadProductData() {

        RetrofitService retrofitService = new RetrofitService();
        ProductApi productApi = retrofitService.getRetrofit().create(ProductApi.class);

        productApi.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                if(response.body()!=null) {
                    for (Product product:response.body()){
//                        dataList.add(product.getProductName());
//                        dataList.add(product.getProductName()+"2");
//                        dataList.add(product.getProductName()+"3");
                        product.setImageResource(R.drawable.ad_3);
                        dataList.add(product);
                        adapter.notifyDataSetChanged();

                    }
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                System.out.println(t);

            }
        });
    }



}