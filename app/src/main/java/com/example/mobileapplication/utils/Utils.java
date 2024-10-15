package com.example.mobileapplication.utils;

import android.util.Patterns;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
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

        if (matcher.matches())
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


    public static String convertToDateString(String dateStr) {

        ZonedDateTime zonedDateTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            zonedDateTime = ZonedDateTime.parse(dateStr);
            String month = zonedDateTime.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            int dayOfMonth = zonedDateTime.getDayOfMonth();
            int year = zonedDateTime.getYear();
            String suffix = getDayOfMonthSuffix(dayOfMonth);

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
            String formattedTime = zonedDateTime.format(timeFormatter);

            String formattedDate = String.format("%s %d%s, %d %s", month, dayOfMonth, suffix, year, formattedTime);

            System.out.println(formattedDate);

            return formattedDate;
        }

        return dateStr;


    }

    private static String getDayOfMonthSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";
        }
        switch (day % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

}
