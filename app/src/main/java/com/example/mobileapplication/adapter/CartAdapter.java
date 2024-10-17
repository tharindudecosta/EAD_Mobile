package com.example.mobileapplication.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.mobileapplication.helper.DatabaseHelper;
import com.example.mobileapplication.view.reviews.CustomerReviews;

import java.util.Iterator;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private String currentView;
    private Context context;

    public CartAdapter(List<CartItem> cartItems, String currentView, Context context) {
        this.cartItems = cartItems;
        this.currentView = currentView;
        this.context = context;

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

        holder.productDeleteBtn.setOnClickListener(v -> {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            if(databaseHelper.removeFromCart(cartItem.getCartItemId())){
                removeCartItemById(cartItem.getCartItemId());
            }


        });

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

    private void removeCartItemById(String cartItemId) {
        if (cartItems != null && !cartItems.isEmpty()) {
            Iterator<CartItem> iterator = cartItems.iterator();
            int position = -1;

            while (iterator.hasNext()) {
                CartItem cartItem = iterator.next();
                position++;

                if (cartItem.getCartItemId().equals(cartItemId)) {
                    iterator.remove();
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, cartItems.size());
                    break;
                }
            }
        }
    }


}
