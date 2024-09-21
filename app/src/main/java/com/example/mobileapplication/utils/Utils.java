package com.example.mobileapplication.utils;

import android.widget.EditText;

public class Utils {


    public static boolean inputValidation(EditText input) {
        String username = input.getText().toString().trim();
        return !username.isEmpty();
    }

}
