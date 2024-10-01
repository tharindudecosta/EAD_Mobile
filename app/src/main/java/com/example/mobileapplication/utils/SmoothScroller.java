package com.example.mobileapplication.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import androidx.recyclerview.widget.LinearSmoothScroller;

public class SmoothScroller extends LinearSmoothScroller {

    public SmoothScroller(Context context) {
        super(context);
    }

    @Override
    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return 30000000f / displayMetrics.densityDpi;
    }
}


