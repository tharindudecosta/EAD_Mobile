package com.example.mobileapplication.view.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.adapter.CartAdapter;
import com.example.mobileapplication.api.OrderApi;
import com.example.mobileapplication.constants.Constants;
import com.example.mobileapplication.entity.CartItem;
import com.example.mobileapplication.entity.OrderSummary;
import com.example.mobileapplication.entity.Product;
import com.example.mobileapplication.helper.RetrofitService;
import com.example.mobileapplication.utils.AlertBoxUtil;
import com.example.mobileapplication.utils.Utils;
import com.example.mobileapplication.view.main.MainActivity;
import com.example.mobileapplication.view.signin.SignInActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsActivity extends AppCompatActivity {

    TextView order_details_pro_label_tv;

    private RecyclerView order_details_pro_recycler_view;
    private CartAdapter cartAdapter;
    private List<CartItem> sampleData;
    private TextView textView;
    private View circleLoader, paymentLayout, shippingLayout;
    private Button cancelOrderBtn;

    private OrderSummary order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        order_details_pro_label_tv = findViewById(R.id.order_details_pro_label_tv);
        order_details_pro_recycler_view = findViewById(R.id.order_details_pro_recycler_view);

        order = getIntent().getParcelableExtra("Order");

        String orderIdDisplay = order.getId().substring(0, 4);
        order_details_pro_label_tv.setText("ORD_" + orderIdDisplay);

        order_details_pro_recycler_view.setLayoutManager(new LinearLayoutManager(OrderDetailsActivity.this));

        cartAdapter = new CartAdapter(order.getCartItems(), Constants.ORDER_VIEW, this);
        order_details_pro_recycler_view.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        shippingLayout = findViewById(R.id.order_details_shipping_add_layout);

        TextView addressTextView = shippingLayout.findViewById(R.id.ship_add_value_tv);
        addressTextView.setText("123 Main St, New York, NY");

        TextView orderDateTextView = shippingLayout.findViewById(R.id.ship_date_value_tv);
        orderDateTextView.setText(Utils.convertToDateString(order.getOrderDate()));

        TextView deliveryDateTextView = shippingLayout.findViewById(R.id.ship_curr_status_value_tv);
        deliveryDateTextView.setText(order.getOrderStatus());

        paymentLayout = findViewById(R.id.order_details_payment_layout);

        TextView itemPriceTextView = paymentLayout.findViewById(R.id.price_items_amount_tv);
        itemPriceTextView.setText("$" + String.format("%.2f", order.getTotalPrice()));

        TextView shippingPriceTextView = paymentLayout.findViewById(R.id.price_shipping_amount_tv);
        shippingPriceTextView.setText("$ 47");

        TextView chargesPriceTextView = paymentLayout.findViewById(R.id.price_charges_amount_tv);
        chargesPriceTextView.setText("$. 553");

        TextView totalPriceTextView = paymentLayout.findViewById(R.id.price_total_amount_tv);
        totalPriceTextView.setText("Rs. 553");

        cancelOrderBtn = findViewById(R.id.cancel_order_button);

        if (!order.getOrderStatus().equals(Constants.ORDER_PROCESSING_STATUS)) {
            cancelOrderBtn.setVisibility(View.GONE);
            deliveryDateTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.testProfileColor));

        }
        cancelOrderBtn.setOnClickListener(view -> {
            orderCancellationAlertBox();
        });

    }

    private void orderCancellationAlertBox() {

        AlertBoxUtil.showOrderCancellationAlertBox(this, "Sign up successful", new AlertBoxUtil.DialogCallback() {
            @Override
            public void onOkClick() {
                order.setOrderStatus(Constants.ORDER_CANCEL_REQUESTED_STATUS);

                updateOrder(order);

                TextView deliveryDateTextView = shippingLayout.findViewById(R.id.ship_curr_status_value_tv);
                deliveryDateTextView.setText(order.getOrderStatus());
                cancelOrderBtn.setVisibility(View.GONE);
            }

            @Override
            public void onCancelClick() {
            }
        });
    }

    private void updateOrder(OrderSummary orderSummary) {
        RetrofitService retrofitService = new RetrofitService();
        OrderApi orderApi = retrofitService.getRetrofit().create(OrderApi.class);


        orderApi.updateCustomerOrder(orderSummary.getId(), orderSummary).enqueue(new Callback<OrderSummary>() {
            @Override
            public void onResponse(Call<OrderSummary> call, Response<OrderSummary> response) {
                if (response.code() == 400) {
                    failureAlertBox();
                    return;
                }

            }

            @Override
            public void onFailure(Call<OrderSummary> call, Throwable t) {
                order.setOrderStatus(Constants.ORDER_PROCESSING_STATUS);
                TextView deliveryDateTextView = shippingLayout.findViewById(R.id.ship_curr_status_value_tv);
                deliveryDateTextView.setText(order.getOrderStatus());
                cancelOrderBtn.setVisibility(View.VISIBLE);
                failureAlertBox();
                System.out.println(t);

            }
        });

    }

    private void failureAlertBox() {

        AlertBoxUtil.showFailureAlertBox(this, "Sign up successful", new AlertBoxUtil.DialogCallback() {
            @Override
            public void onOkClick() {
            }

            @Override
            public void onCancelClick() {
            }
        });
    }

}