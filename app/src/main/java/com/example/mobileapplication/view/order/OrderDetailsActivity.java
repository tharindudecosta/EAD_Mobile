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
import com.example.mobileapplication.entity.OrderSummary;

public class OrderDetailsActivity extends AppCompatActivity {
    TextView order_details_pro_label_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        order_details_pro_label_tv = findViewById(R.id.order_details_pro_label_tv);
        Intent intent = getIntent();

        OrderSummary order = getIntent().getParcelableExtra("Order");

        if (order != null) {
            System.out.println(order.getOrderId());
            System.out.println(order.getOrderDate());
            System.out.println(order.getCartItems().get(0).getTitle());
        }

        order_details_pro_label_tv.setText(order.getOrderId());

//// In your activity or fragment after inflating the main layout
//        View shippingLayout = findViewById(R.id.order_details_shipping_add_layout);
//
//// Accessing views within the included layout
//        TextView addressTextView = shippingLayout.findViewById(R.id.shipping_address_text);
//        addressTextView.setText("123 Main St, New York, NY");
//




    }
}