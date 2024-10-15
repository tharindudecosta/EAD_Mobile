package com.example.mobileapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.constants.Constants;
import com.example.mobileapplication.entity.CartItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private String currentView;

    public CartAdapter(List<CartItem> cartItems,String currentView) {
        this.cartItems = cartItems;
        this.currentView = currentView;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);

        holder.titleTextView.setText(cartItem.getTitle());
        holder.priceTextView.setText("$" + String.format("%.2f", cartItem.getTotalPrice()));
        holder.quantityTextView.setText(String.valueOf(cartItem.getQuantity()));
        holder.productImageView.setImageResource(cartItem.getImageResource());

        if(currentView.equals(Constants.ORDER_VIEW)){
            holder.productDeleteBtn.setVisibility(View.GONE);
            holder.productQuantityBtns.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView, priceTextView, quantityTextView;
        ImageView productImageView;
        ImageView productDeleteBtn;
        LinearLayout productQuantityBtns;
        View circleLoader;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.cart_product_title_tv);
            priceTextView = itemView.findViewById(R.id.cart_product_price_tv);
            quantityTextView = itemView.findViewById(R.id.cart_product_quantity_text_View);
            productImageView = itemView.findViewById(R.id.product_image_view);
            productDeleteBtn = itemView.findViewById(R.id.cart_product_delete_btn);
            productQuantityBtns = itemView.findViewById(R.id.cart_product_quantity_buttons_layout);
//            circleLoader = itemView.findViewById(R.id.loader_layout);
        }
    }

}
