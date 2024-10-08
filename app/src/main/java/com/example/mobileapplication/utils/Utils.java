package com.example.mobileapplication.utils;

import android.util.Patterns;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {


    public static boolean inputValidation(EditText input) {
        String username = input.getText().toString().trim();
        return !username.isEmpty();
    }

    public static boolean emailValidation(EditText input) {
        String regEx = "/^\\S+@\\S+\\.\\S+$/";

        CharSequence inputStr = input.getText().toString();

        Pattern pattern = Pattern.compile(regEx, Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;

    }


    public final static boolean isValidPassword(String target) {
        return Pattern.compile("^(?=.*\\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{4,12}$").matcher(target).matches();
    }

    public final static boolean isValidName(String target) {
        return Pattern.compile("^(?=.*[a-zA-Z가-힣])[a-zA-Z가-힣]{1,}$").matcher(target).matches();

    }

    public final static boolean isValidNickName(String target) {
        return Pattern.compile("^(?=.*[a-zA-Z\\d])[a-zA-Z0-9가-힣]{2,12}$|^[가-힣]$").matcher(target).matches();
    }
}
