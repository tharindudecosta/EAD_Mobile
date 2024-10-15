package com.example.mobileapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.entity.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private List<Review> reviewList;

    public ReviewAdapter(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.reviewVendorTv.setText(review.getVendorName());
        holder.reviewCommentTv.setText(review.getComment());
        holder.reviewCommentratingBar.setRating(review.getRating());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView reviewVendorTv;
        TextView reviewCommentTv;
        RatingBar reviewCommentratingBar;

        ReviewViewHolder(View itemView) {
            super(itemView);
            reviewVendorTv = itemView.findViewById(R.id.reviewVendorTv);
            reviewCommentTv = itemView.findViewById(R.id.reviewCommentTv2);
            reviewCommentratingBar = itemView.findViewById(R.id.reviewCommentratingBar);
        }
    }
}

