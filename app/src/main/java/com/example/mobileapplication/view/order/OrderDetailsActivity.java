package com.example.mobileapplication.view.order;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.adapter.CartAdapter;
import com.example.mobileapplication.constants.Constants;
import com.example.mobileapplication.entity.CartItem;
import com.example.mobileapplication.entity.OrderSummary;

import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {

    TextView order_details_pro_label_tv;

    private RecyclerView order_details_pro_recycler_view;
    private CartAdapter cartAdapter;
    private List<CartItem> sampleData;
    private TextView textView;
    private View circleLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        order_details_pro_label_tv = findViewById(R.id.order_details_pro_label_tv);
        order_details_pro_recycler_view = findViewById(R.id.order_details_pro_recycler_view);

        OrderSummary order = getIntent().getParcelableExtra("Order");

        if (order != null) {
            System.out.println(order.getOrderId());
            System.out.println(order.getOrderDate());
            System.out.println(order.getCartItems().get(0).getTitle());
        }

        order_details_pro_label_tv.setText(order.getOrderId());

        order_details_pro_recycler_view.setLayoutManager(new LinearLayoutManager(OrderDetailsActivity.this));

        cartAdapter = new CartAdapter(order.getCartItems(), Constants.ORDER_VIEW);
        order_details_pro_recycler_view.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

// In your activity or fragment after inflating the main layout
        View shippingLayout = findViewById(R.id.order_details_shipping_add_layout);

        TextView addressTextView = shippingLayout.findViewById(R.id.ship_add_value_tv);
        addressTextView.setText("123 Main St, New York, NY");

        TextView orderDateTextView = shippingLayout.findViewById(R.id.ship_date_value_tv);
        orderDateTextView.setText("December 01, 2022");

        TextView deliveryDateTextView = shippingLayout.findViewById(R.id.ship_curr_status_value_tv);
        deliveryDateTextView.setText("DELIVERED ON January 01, 2023");

        View paymentLayout = findViewById(R.id.order_details_payment_layout);

        TextView itemPriceTextView = paymentLayout.findViewById(R.id.price_items_amount_tv);
        itemPriceTextView.setText("Rs. 321");

        TextView shippingPriceTextView = paymentLayout.findViewById(R.id.price_shipping_amount_tv);
        shippingPriceTextView.setText("Rs. 471");

        TextView chargesPriceTextView = paymentLayout.findViewById(R.id.price_charges_amount_tv);
        chargesPriceTextView.setText("Rs. 553");

        TextView totalPriceTextView = paymentLayout.findViewById(R.id.price_total_amount_tv);
        totalPriceTextView.setText("Rs. 553");


    }
}