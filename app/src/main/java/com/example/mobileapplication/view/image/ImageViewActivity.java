package com.example.mobileapplication.view.image;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mobileapplication.R;
/*
* https://www.youtube.com/watch?v=Nld8bmj2BQE
* */
public class ImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        ImageView imageView = findViewById(R.id.imageView);

        String imageId = getIntent().getStringExtra("image");
        Glide.with(ImageViewActivity.this).load(Integer.valueOf(imageId)).into(imageView);

    }
}