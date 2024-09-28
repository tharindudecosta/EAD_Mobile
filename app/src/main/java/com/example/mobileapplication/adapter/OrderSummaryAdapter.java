package com.example.mobileapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.entity.OrderSummary;
import com.example.mobileapplication.interfaces.OnItemClickListener;

import java.util.List;

public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.OrderViewHolder> {

    private List<OrderSummary> orderList;
    private OnItemClickListener listener;

    public OrderSummaryAdapter(List<OrderSummary> orderList) {
        this.orderList = orderList;
    }
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_order_summary_card, parent, false);
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
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView orderId, orderDate, orderStatus, noOfItems, totalPrice;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.order_summary_id_tv);
            orderDate = itemView.findViewById(R.id.order_summary_date_tv);
            orderStatus = itemView.findViewById(R.id.order_summary_status_value_tv);
            noOfItems = itemView.findViewById(R.id.order_summary_items_count_tv);
            totalPrice = itemView.findViewById(R.id.order_summary_total_amount_tv);
        }
    }
}
