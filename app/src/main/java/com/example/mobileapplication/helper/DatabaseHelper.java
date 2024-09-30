package com.example.mobileapplication.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Userdata.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(email TEXT primary key,password TEXT, fullName TEXT, phoneNo TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
    }

    public boolean insert(String email, String password, String fullName, String phoneNo) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("fullName", fullName);
        contentValues.put("phoneNo", phoneNo);

        long result = sqLiteDatabase.insert("users", null, contentValues);

        return result != -1;
    }

    public boolean login(String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor userCheckCursor = sqLiteDatabase.rawQuery("select * from users where email = ?",new String[]{email});
        if (userCheckCursor.getCount() > 0){
            Cursor loginCursor = sqLiteDatabase.rawQuery("select * from users where email = ? and password = ?",new String[]{email,password});
            if (loginCursor.getCount() > 0){
                return true;
            }
            return false;
        }
        return false;
    }
}
