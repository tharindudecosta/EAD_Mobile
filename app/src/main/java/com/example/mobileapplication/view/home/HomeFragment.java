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
import com.example.mobileapplication.api.ProductApi;
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

    private boolean isImageClicked = false;
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
            loadProductData();
            loadAdImages();
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
                if (!isImageClicked) {
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
                    handler.postDelayed(this, 3000);
                }
            }
        };
        handler.postDelayed(runnable, 3000);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isImageClicked) {
            isImageClicked = false;
            startAutoScroll();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopAutoScroll();
    }
    private void startAutoScroll() {
        handler.postDelayed(runnable, 3000);
    }

    private void stopAutoScroll() {
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }


    private void loadAdImages() {
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
