package com.example.mobileapplication.view.products;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.adapter.ProductImagesAdapter;
import com.example.mobileapplication.entity.Product;
import com.example.mobileapplication.utils.AlertBoxUtil;
import com.example.mobileapplication.view.image.ImageViewActivity;

import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity {

    private TextView productTitle, productPrice;
    private RecyclerView imageRecycler;
    private ProductImagesAdapter productImagesAdapter;
    private ArrayList<Integer> imageList;
    private Product product;
    private RatingBar ratingBar;
    private float ratingDef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_details);

        initView();
    }

    private void initView() {

        productTitle = findViewById(R.id.pro_details_title_tv);
        productPrice = findViewById(R.id.pro_details_price_tv);
        ratingBar = findViewById(R.id.pro_details_rating_bar);
        ratingBar.setIsIndicator(false);

        // get rating from DB
        ratingDef = ratingBar.getRating();

        initData();

        imageList = new ArrayList<>();
        imageRecycler = findViewById(R.id.pro_details_images_recyclerview);

        productImagesAdapter = new ProductImagesAdapter(ProductDetailsActivity.this, imageList);
        imageRecycler.setAdapter(productImagesAdapter);
        loadImages();

//        ratingBar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ratingAlertBox();
//            }
//        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    ratingBar.setRating(ratingDef);
                    ratingAlertBox();
                }
            }
        });

    }

    private void initData() {
        product = getIntent().getParcelableExtra("product");

        if (product != null) {
            productTitle.setText(product.getTitle());
            productPrice.setText(String.valueOf(product.getUnitPrice()));

        }
    }

    private void loadImages() {

        imageList.add(product.getImageResource());
        imageList.add(R.drawable.ad_1);
        imageList.add(R.drawable.ad_2);
        imageList.add(R.drawable.ad_3);
        imageList.add(R.drawable.ad_3);
        imageList.add(R.drawable.ad_3);
        imageList.add(R.drawable.ad_3);

        productImagesAdapter.setOnItemClickListener(new ProductImagesAdapter.OnItemClickListener() {
            @Override
            public void onClick(ImageView imageView, Integer path) {
                startActivity(new Intent(ProductDetailsActivity.this, ImageViewActivity.class).putExtra("image", path.toString()), ActivityOptions.makeSceneTransitionAnimation(ProductDetailsActivity.this, imageView, "image").toBundle());
            }
        });

        productImagesAdapter.notifyDataSetChanged();
    }

    private void ratingAlertBox() {

        AlertBoxUtil.showRatingAlertBox(this, null, new AlertBoxUtil.RatingDialogCallback() {
            @Override
            public void onOkClick(float rating) {

                // handle send to db
                ratingBar.setRating(rating);
                ratingDef = rating;
            }

            @Override
            public void onCancelClick() {
            }
        },ratingDef);
    }

}