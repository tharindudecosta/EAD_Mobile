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
import android.widget.Button;
import android.widget.TextView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.adapter.CartAdapter;
import com.example.mobileapplication.api.OrderApi;
import com.example.mobileapplication.constants.Constants;
import com.example.mobileapplication.entity.CartItem;
import com.example.mobileapplication.entity.OrderSummary;
import com.example.mobileapplication.helper.DatabaseHelper;
import com.example.mobileapplication.helper.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingCartFragment extends Fragment {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<CartItem> sampleData;
    private TextView textView;
    private View circleLoader;
    private DatabaseHelper databaseHelper;
    private Button checkOutBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        databaseHelper = new DatabaseHelper(getContext());

        recyclerView = view.findViewById(R.id.cart_products_recycler_view);
        textView = view.findViewById(R.id.cart_empty_text_view);
        circleLoader = view.findViewById(R.id.circular_loader_layout);
        checkOutBtn = view.findViewById(R.id.cart_check_out_btn);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        circleLoader.setVisibility(View.VISIBLE);


        sampleData = new ArrayList<>();
        cartAdapter = new CartAdapter(sampleData, Constants.CART_VIEW);
        recyclerView.setAdapter(cartAdapter);

        getCart();



        return view;
    }


    private void getCart(){
        List<CartItem> cartItems = databaseHelper.getAllCartItems();
        sampleData.addAll(cartItems);
        if (sampleData.isEmpty()){
            textView.setVisibility(View.VISIBLE);
            checkOutBtn.setVisibility(View.GONE);
        }
        circleLoader.setVisibility(View.GONE);
        cartAdapter.notifyDataSetChanged();

    }

    private void createOrder(){
        RetrofitService retrofitService = new RetrofitService();
        OrderApi orderApi = retrofitService.getRetrofit().create(OrderApi.class);

        orderApi.createCustomerOrder().enqueue(new Callback<OrderSummary>() {
            @Override
            public void onResponse(Call<OrderSummary> call, Response<OrderSummary> response) {

            }

            @Override
            public void onFailure(Call<OrderSummary> call, Throwable t) {

            }
        });

    }

}
