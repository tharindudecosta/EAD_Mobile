package com.example.mobileapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.example.mobileapplication.entity.LoginResponse;

import java.util.Map;

public class JwtDecoder {

    public static void decodeJWT(LoginResponse loginResponse) {
        try {
            String token = loginResponse.getToken();

            JWT jwt = new JWT(token);

            Map<String, Claim> claims = jwt.getClaims();

            for (Map.Entry<String, Claim> entry : claims.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue().asString());
            }

            loginResponse.setCustomerId(jwt.getClaim("unique_name").asString());
            loginResponse.setRole(jwt.getClaim("role").asString());

//            String subject = jwt.getClaim("sub").asString();
//            System.out.println("Subject: " + subject);
//
//            String header = jwt.getHeader().toString();
//            String signature = jwt.getSignature();
//            System.out.println("Header: " + header);
//            System.out.println("Signature: " + signature);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error decoding JWT");
        }
    }

    public static void saveJwtToken(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("JWT_TOKEN", token);
        editor.apply();
    }

    public static String getJwtToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString("JWT_TOKEN", null);
    }

    public static void clearJwtToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("JWT_TOKEN");
        editor.apply();
    }

}
