package com.example.mobileapplication.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mobileapplication.R;

import java.util.Objects;

/*
* https://www.youtube.com/watch?v=3RTpdB-RszY
* */
public class AlertBoxUtil {

    public static void showSuccessAlertBox(Context context, String message, DialogCallback callback) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.success_alert);
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
        dialog.setContentView(R.layout.failure_alert);
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

    public interface DialogCallback {
        void onOkClick();
    }
}
