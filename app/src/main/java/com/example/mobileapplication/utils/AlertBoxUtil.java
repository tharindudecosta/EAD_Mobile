package com.example.mobileapplication.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import com.example.mobileapplication.R;

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

    public static void showRatingAlertBox(Context context, String message, RatingDialogCallback callback,Float ratingDef) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_rating);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        RatingBar ratingBar = dialog.findViewById(R.id.sellerRatingBar);
        ratingBar.setRating(ratingDef);

        Button okButton = dialog.findViewById(R.id.okButton);

        Button cancelButton = dialog.findViewById(R.id.cancel_button);

        okButton.setOnClickListener(v -> {
            dialog.dismiss();
            if (callback != null) {
                callback.onOkClick(ratingBar.getRating());
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


    public interface DialogCallback {
        void onOkClick();
        void onCancelClick();
    }

    public interface RatingDialogCallback {
        void onOkClick(float rating);
        void onCancelClick();
    }
}
