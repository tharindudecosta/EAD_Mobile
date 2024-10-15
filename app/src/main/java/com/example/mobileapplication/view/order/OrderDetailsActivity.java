package com.example.mobileapplication.view.order;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.adapter.CartAdapter;
import com.example.mobileapplication.constants.Constants;
import com.example.mobileapplication.entity.CartItem;
import com.example.mobileapplication.entity.OrderSummary;
import com.example.mobileapplication.entity.Product;
import com.example.mobileapplication.utils.Utils;

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

//        private String id;
//        private String orderDate;
//        private String orderStatus;
//        private int numberOfItems;
//        private double totalPrice;
//
//        private String customerId;
//        private List<String> productIds;
//        private String deliveryDate;
//        private boolean isActive;
//        private List<Product> productList;
//
//        private List<CartItem> cartItems;


        OrderSummary order = getIntent().getParcelableExtra("Order");

        String orderIdDisplay = order.getId().substring(0, 4);
        order_details_pro_label_tv.setText("ORD_" + orderIdDisplay);

        order_details_pro_recycler_view.setLayoutManager(new LinearLayoutManager(OrderDetailsActivity.this));

        cartAdapter = new CartAdapter(order.getCartItems(), Constants.ORDER_VIEW);
        order_details_pro_recycler_view.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        View shippingLayout = findViewById(R.id.order_details_shipping_add_layout);

        TextView addressTextView = shippingLayout.findViewById(R.id.ship_add_value_tv);
        addressTextView.setText("123 Main St, New York, NY");

        TextView orderDateTextView = shippingLayout.findViewById(R.id.ship_date_value_tv);
        orderDateTextView.setText(Utils.convertToDateString(order.getOrderDate()));

        TextView deliveryDateTextView = shippingLayout.findViewById(R.id.ship_curr_status_value_tv);
        deliveryDateTextView.setText(order.getOrderStatus());

        View paymentLayout = findViewById(R.id.order_details_payment_layout);

        TextView itemPriceTextView = paymentLayout.findViewById(R.id.price_items_amount_tv);
        itemPriceTextView.setText("$" + String.format("%.2f", order.getTotalPrice()));

        TextView shippingPriceTextView = paymentLayout.findViewById(R.id.price_shipping_amount_tv);
        shippingPriceTextView.setText("$ 47");

        TextView chargesPriceTextView = paymentLayout.findViewById(R.id.price_charges_amount_tv);
        chargesPriceTextView.setText("$. 553");

        TextView totalPriceTextView = paymentLayout.findViewById(R.id.price_total_amount_tv);
        totalPriceTextView.setText("Rs. 553");


    }
}