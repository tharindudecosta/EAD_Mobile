package com.example.mobileapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.entity.OrderSummary;
import com.example.mobileapplication.interfaces.OnItemClickListener;
import com.example.mobileapplication.view.order.OrderDetailsActivity;

import java.io.Serializable;
import java.util.List;

/*
 * https://www.youtube.com/watch?v=qAHWVIK7_BY&ab_channel=AndroidKnowledge
 * https://androidknowledge.com/recyclerview-in-android-studio-using-java/
 * */
public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.OrderViewHolder> {

    private List<OrderSummary> orderList;
    private OnItemClickListener listener;
    private Context context;

    public OrderSummaryAdapter(List<OrderSummary> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_summary_card, parent, false);

        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderSummary order = orderList.get(position);
        holder.orderId.setText(order.getOrderId());
        holder.orderDate.setText(order.getOrderDate());
        holder.orderStatus.setText(order.getOrderStatus());
        holder.noOfItems.setText(String.valueOf(order.getNoOfItems()));
        holder.totalPrice.setText(String.format("$%.2f", order.getTotalPrice()));

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("Order", orderList.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView orderId, orderDate, orderStatus, noOfItems, totalPrice;
        View recCard;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.order_summary_id_tv);
            orderDate = itemView.findViewById(R.id.order_summary_date_tv);
            orderStatus = itemView.findViewById(R.id.order_summary_status_value_tv);
            noOfItems = itemView.findViewById(R.id.order_summary_items_count_tv);
            totalPrice = itemView.findViewById(R.id.order_summary_total_amount_tv);
            recCard = itemView.findViewById(R.id.recCard);
        }
    }
}
