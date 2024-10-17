package com.example.mobileapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.constants.Constants;
import com.example.mobileapplication.controller.ReviewManagementController;
import com.example.mobileapplication.entity.Review;
import com.example.mobileapplication.utils.AlertBoxUtil;
import com.example.mobileapplication.utils.Utils;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private List<Review> reviewList;
    private Context context;
    private ReviewManagementController reviewManagementController;

    public ReviewAdapter(List<Review> reviewList, Context context) {
        this.reviewList = reviewList;
        this.context = context;
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
        holder.reviewTimestampTv.setText(Utils.convertToDateString(review.getTimestamp()));

        holder.itemView.setOnClickListener(v -> {
            ratingAlertBox(review,holder);
        });
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView reviewVendorTv,reviewTimestampTv,reviewCommentTv;
        RatingBar reviewCommentratingBar;

        ReviewViewHolder(View itemView) {
            super(itemView);
            reviewVendorTv = itemView.findViewById(R.id.reviewVendorTv);
            reviewCommentTv = itemView.findViewById(R.id.reviewCommentTv2);
            reviewCommentratingBar = itemView.findViewById(R.id.reviewCommentratingBar);
            reviewTimestampTv = itemView.findViewById(R.id.reviewTimestampTv);
            reviewManagementController = new ReviewManagementController(context);

        }
    }

    private void ratingAlertBox(Review review, ReviewViewHolder holder) {

        AlertBoxUtil.showRatingAlertBox(context, null, new AlertBoxUtil.RatingDialogCallback() {
            @Override
            public void onOkClick(Review editedReview,String reviewAction) {

                holder.reviewCommentTv.setText(editedReview.getComment());
                holder.reviewCommentratingBar.setRating(editedReview.getRating());

                if(!review.getRating().equals(0f)){

                    if(reviewAction.equals(Constants.REVIEW_CREATE)){
                        reviewManagementController.createReview(review);

                    }
                    if(reviewAction.equals(Constants.REVIEW_UPDATE)){
                        reviewManagementController.updateReview(review);

                    }
                }
            }

            @Override
            public void onCancelClick() {
            }
        }, review);
    }
}

