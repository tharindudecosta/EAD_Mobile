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
import com.example.mobileapplication.view.products.ProductDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchQueryProductAdapter extends RecyclerView.Adapter<SearchQueryProductAdapter.MyViewHolder> {

    private List<Product> dataList;
    private List<Product> filteredList;
    private Context context;

    public SearchQueryProductAdapter(List<Product> dataList, Context context) {
        this.dataList = dataList;
        this.filteredList = new ArrayList<>(dataList); // Copy of original data for filtering
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_search_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = dataList.get(position);

//        holder.textView.setText(filteredList.get(position));
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

    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void showRecycleView(@NonNull MyViewHolder holder) {
        holder.itemView.setVisibility(View.VISIBLE);
        holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void hideRecycleView(@NonNull MyViewHolder holder) {
        holder.itemView.setVisibility(View.GONE);
        holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
    }

    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(dataList);
        } else {
            for (Product item : dataList) {
                if (item.getProductName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView titleTextView, priceTextView, catergoryTV;
        ImageView productImageView;
        ImageButton addToCartImageButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            textView = itemView.findViewById(R.id.textView);
            titleTextView = itemView.findViewById(R.id.product_name_tv);
            priceTextView = itemView.findViewById(R.id.product_price_tv);
            catergoryTV = itemView.findViewById(R.id.product_cat_tv);
            productImageView = itemView.findViewById(R.id.product_image_view);
        }
    }
}
