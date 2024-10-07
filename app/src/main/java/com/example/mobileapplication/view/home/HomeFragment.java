package com.example.mobileapplication.view.home;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.adapter.CarouselImageAdapter;
import com.example.mobileapplication.adapter.HomeProductAdapter;
import com.example.mobileapplication.api.LoginApi;
import com.example.mobileapplication.api.ProductApi;
import com.example.mobileapplication.entity.LoginRequest;
import com.example.mobileapplication.entity.LoginResponse;
import com.example.mobileapplication.entity.Product;
import com.example.mobileapplication.helper.RetrofitService;
import com.example.mobileapplication.utils.SmoothScroller;
import com.example.mobileapplication.view.image.ImageViewActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView productRecyclerView, imageRecyclerView;
    private List<Product> productList;
    private ArrayList<Integer> imageList;
    private View circleLoader;
    private HomeProductAdapter homeProductAdapter;
    private CarouselImageAdapter carouselImageAdapter;

    private Handler handler;
    private Runnable runnable;
    private int scrollPosition = 0;

    private boolean isImageClicked = false;  // Flag to check if an image was clicked
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        initView();

        setupAutoScroll();

        carouselImageAdapter.setOnItemClickListener(new CarouselImageAdapter.OnItemClickListener() {
            @Override
            public void onClick(ImageView imageView, Integer path) {
                stopAutoScroll();
                isImageClicked = true;

                Intent intent = new Intent(getContext(), ImageViewActivity.class);
                intent.putExtra("image", path.toString());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(), imageView, "image").toBundle());
            }
        });

        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            loadSampleData();
            loadImages();
            if (!productList.isEmpty()) {
                circleLoader.setVisibility(View.GONE);
            }
        }, 500);

        return view;
    }

    private void initView() {
        productRecyclerView = view.findViewById(R.id.products_recycler_view);
        circleLoader = view.findViewById(R.id.circular_loader_layout);
        imageRecyclerView = view.findViewById(R.id.imageCarousel);

        productList = new ArrayList<>();
        imageList = new ArrayList<>();

        homeProductAdapter = new HomeProductAdapter(productList, getContext());
        productRecyclerView.setAdapter(homeProductAdapter);

        carouselImageAdapter = new CarouselImageAdapter(getContext(), imageList);
        imageRecyclerView.setAdapter(carouselImageAdapter);

    }

    private void setupAutoScroll() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (!isImageClicked) { // Ensure it doesn't auto-scroll when an image is clicked
                    if (scrollPosition == carouselImageAdapter.getItemCount() - 1) {
                        scrollPosition = 0;
                    } else {
                        scrollPosition++;
                    }

                    // Smooth scrolling
                    SmoothScroller smoothScroller = new SmoothScroller(getContext());
                    smoothScroller.setTargetPosition(scrollPosition);
                    imageRecyclerView.getLayoutManager().startSmoothScroll(smoothScroller);

                    imageRecyclerView.smoothScrollToPosition(scrollPosition);
                    handler.postDelayed(this, 3000);  // Continue auto-scrolling every 3 seconds
                }
            }
        };
        handler.postDelayed(runnable, 3000);  // Start auto-scrolling after initial delay
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isImageClicked) {
            // Resume auto-scrolling when returning from ImageViewActivity
            isImageClicked = false;
            startAutoScroll();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Remove callbacks when the fragment is destroyed to avoid memory leaks
        stopAutoScroll();
    }
    private void startAutoScroll() {
        handler.postDelayed(runnable, 3000);  // Start auto-scrolling
    }

    private void stopAutoScroll() {
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);  // Stop auto-scrolling
        }
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


    private void loadImages() {
        imageList.add(R.drawable.ad_1);
        imageList.add(R.drawable.ad_2);
        imageList.add(R.drawable.ad_3);
        imageList.add(R.drawable.ad_3);
        imageList.add(R.drawable.ad_3);
        imageList.add(R.drawable.ad_3);

        carouselImageAdapter.notifyDataSetChanged();
    }


    private void loadProductData() {

        RetrofitService retrofitService = new RetrofitService();
        ProductApi productApi = retrofitService.getRetrofit().create(ProductApi.class);

        productApi.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                if(response.body()!=null) {
                    productList.addAll(response.body());

                    homeProductAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });


    }
}
