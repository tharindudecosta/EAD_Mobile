package com.example.mobileapplication.view.products;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.adapter.ProductImagesAdapter;
import com.example.mobileapplication.constants.Constants;
import com.example.mobileapplication.controller.ProductReviewsController;
import com.example.mobileapplication.controller.ReviewManagementController;
import com.example.mobileapplication.entity.Product;
import com.example.mobileapplication.entity.Review;
import com.example.mobileapplication.entity.Vendor;
import com.example.mobileapplication.helper.DatabaseHelper;
import com.example.mobileapplication.utils.AlertBoxUtil;
import com.example.mobileapplication.view.image.ImageViewActivity;

import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity {

    private TextView productTitle, productPrice,productCategory,productVendor,pro_details_cat_display_tv,pro_details_vendor_display_tv;
    private RecyclerView imageRecycler;
    private ProductImagesAdapter productImagesAdapter;
    private ArrayList<Integer> imageList;
    private Product product;
    private RatingBar ratingBar;
    private float ratingDef;
    private ProductReviewsController productReviewsController;
    private ReviewManagementController reviewManagementController;
    private Review review;
    private Button addToCartBtn;
    private DatabaseHelper databaseHelper;

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
        addToCartBtn = findViewById(R.id.pro_details_add_cart_btn);
        productCategory = findViewById(R.id.pro_details_cat_tv);
        productVendor = findViewById(R.id.pro_details_vendor_tv);

        pro_details_cat_display_tv = findViewById(R.id.pro_details_cat_display_tv);
        pro_details_cat_display_tv.setText("Category");

        pro_details_vendor_display_tv = findViewById(R.id.pro_details_vendor_display_tv);
        pro_details_vendor_display_tv.setText("Vendor");

        ratingBar.setIsIndicator(false);

        databaseHelper = new DatabaseHelper(this);

        productReviewsController = new ProductReviewsController(this);
        reviewManagementController = new ReviewManagementController(this);
        review = new Review();

        initData();

        imageList = new ArrayList<>();
        imageRecycler = findViewById(R.id.pro_details_images_recyclerview);

        productImagesAdapter = new ProductImagesAdapter(ProductDetailsActivity.this, imageList);
        imageRecycler.setAdapter(productImagesAdapter);
        loadImages();

        addToCartBtn.setOnClickListener(view -> {
            DatabaseHelper databaseHelper = new DatabaseHelper(this);

            if (!databaseHelper.isProductInCart(product.getId())) {
                product.setQuantity(1);
                databaseHelper.addToCart(product);
                Toast.makeText(this, product.getProductName() + " added to cart", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, product.getProductName() + " already in cart", Toast.LENGTH_SHORT).show();
            }
        });



        ratingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ratingAlertBox();
            }
        });

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
            productTitle.setText(product.getProductName());
            productPrice.setText("$" + String.format("%.2f", product.getUnitPrice()));
            productCategory.setText(product.getCategory());

            productReviewsController.getVendorReviews(product, new ProductReviewsController.ReviewCallback() {
                @Override
                public void onReviewFetched(Review vendorReview, Vendor body) {
                    String customerId = databaseHelper.getCustomerIdFromSession();
                    review.setVendorId(body.getId());
                    review.setCustomerId(customerId);
                    productVendor.setText(body.getName());

                    if (vendorReview != null) {
                        review = vendorReview;
                        ratingBar.setRating(vendorReview.getRating());
                        ratingDef = vendorReview.getRating();
                    } else {
                        review.setRating(0f);
                        ratingBar.setRating(0f);
                        ratingDef = 0f;
                    }
                }
            });
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
            public void onOkClick(Review review,String reviewAction) {
                // handle send to db
                ratingBar.setRating(review.getRating());
                ratingDef = review.getRating();

                if(!review.getRating().equals(0f)){

                    if(reviewAction.equals(Constants.REVIEW_CREATE)){
                        reviewManagementController.createReview(review);

                    }
                    if(reviewAction.equals(Constants.REVIEW_UPDATE)){
                        reviewManagementController.updateReview(review);

                    }
                }


            }
            @Override
            public void onCancelClick() {
            }
        },review);
    }

}