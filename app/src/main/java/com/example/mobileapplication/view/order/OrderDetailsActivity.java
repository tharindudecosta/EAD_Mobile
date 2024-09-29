package com.example.mobileapplication.view.order;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobileapplication.R;

public class OrderDetailsActivity extends AppCompatActivity {
    TextView order_details_pro_label_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        order_details_pro_label_tv = findViewById(R.id.order_details_pro_label_tv);
        Intent intent = getIntent();
        String message = intent.getStringExtra("Title");

        order_details_pro_label_tv.setText(message);




    }
}