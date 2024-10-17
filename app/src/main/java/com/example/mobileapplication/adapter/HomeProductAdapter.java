package com.example.mobileapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.entity.Product;
import com.example.mobileapplication.helper.DatabaseHelper;
import com.example.mobileapplication.view.products.ProductDetailsActivity;

import java.util.List;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.HomeProductViewHolder> {

    private List<Product> productList;
    private Context context;

    public HomeProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
        return new HomeProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProductViewHolder holder, int position) {

        Product product = productList.get(position);


        holder.titleTextView.setText(product.getProductName());
        holder.priceTextView.setText("$" + String.format("%.2f", product.getUnitPrice()));
        holder.catergoryTV.setText(product.getCategory());
        holder.productImageView.setImageResource(product.getImageResource());


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailsActivity.class);
            intent.putExtra("product", product); // Assuming CartItem is Serializable or Parcelable
            context.startActivity(intent);
//            Toast.makeText(context, product.getTitle() + " On click", Toast.LENGTH_SHORT).show();
        });

        holder.addToCartImageButton.setOnClickListener(v -> {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);

            if (!databaseHelper.isProductInCart(product.getId())) {
                product.setQuantity(1);
                databaseHelper.addToCart(product);
                Toast.makeText(context, product.getProductName() + " added to cart", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, product.getProductName() + " already in cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class HomeProductViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView, priceTextView, catergoryTV;
        ImageView productImageView;
        ImageButton addToCartImageButton;

        public HomeProductViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.product_name_tv);
            priceTextView = itemView.findViewById(R.id.product_price_tv);
            catergoryTV = itemView.findViewById(R.id.product_cat_tv);
            productImageView = itemView.findViewById(R.id.product_image_view);
            addToCartImageButton = itemView.findViewById(R.id.add_to_cart_img_btn);


        }
    }
}
