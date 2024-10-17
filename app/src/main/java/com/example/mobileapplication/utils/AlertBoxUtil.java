package com.example.mobileapplication.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.mobileapplication.R;
import com.example.mobileapplication.constants.Constants;
import com.example.mobileapplication.entity.Review;

import java.util.Objects;

/*
* https://www.youtube.com/watch?v=3RTpdB-RszY
* */
public class AlertBoxUtil {

    public static void showSuccessAlertBox(Context context, String message, DialogCallback callback) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_success);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        Button okButton = dialog.findViewById(R.id.okButton);

        okButton.setOnClickListener(v -> {
            dialog.dismiss();
            if (callback != null) {
                callback.onOkClick();
            }
        });

        dialog.show();
    }

    public static void showFailureAlertBox(Context context, String message, DialogCallback callback) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_failure);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        Button okButton = dialog.findViewById(R.id.tryAgainButton);

        okButton.setOnClickListener(v -> {
            dialog.dismiss();
            if (callback != null) {
                callback.onOkClick();
            }
        });

        dialog.show();
    }

    public static void showRatingAlertBox(Context context, String message, RatingDialogCallback callback, Review review) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_rating);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        String reviewAction = null;
        if(review.getRating() == 0){
            reviewAction = Constants.REVIEW_CREATE;
        } else {
            reviewAction = Constants.REVIEW_UPDATE;
        }

        RatingBar ratingBar = dialog.findViewById(R.id.sellerRatingBar);
        ratingBar.setRating(review.getRating());

        EditText reviewComment = dialog.findViewById(R.id.reviewComment);
        reviewComment.setText(review.getComment());

        Button okButton = dialog.findViewById(R.id.okButton);

        Button cancelButton = dialog.findViewById(R.id.cancel_button);

        String finalReviewAction = reviewAction;
        okButton.setOnClickListener(v -> {
            dialog.dismiss();
            if (callback != null) {

                review.setRating(ratingBar.getRating());
                review.setComment(reviewComment.getText().toString());

                callback.onOkClick(review, finalReviewAction);
            }
        });

        cancelButton.setOnClickListener(v -> {
            dialog.dismiss();
            if (callback != null) {
                callback.onCancelClick();
            }
        });

        dialog.show();
    }

    public static void showSignOutAlertBox(Context context, String message, DialogCallback callback) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_signout);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        Button okButton = dialog.findViewById(R.id.okButton);

        okButton.setOnClickListener(v -> {
            dialog.dismiss();
            if (callback != null) {
                callback.onOkClick();
            }
        });

        dialog.show();
    }

    public static void showOrderCancellationAlertBox(Context context, String message, DialogCallback callback) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_order_cancel);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        Button okButton = dialog.findViewById(R.id.okButton);

        okButton.setOnClickListener(v -> {
            dialog.dismiss();
            if (callback != null) {
                callback.onOkClick();
            }
        });

        dialog.show();
    }


    public interface DialogCallback {
        void onOkClick();
        void onCancelClick();
    }

    public interface RatingDialogCallback {
        void onOkClick(Review review,String reviewAction);
        void onCancelClick();
    }
}
