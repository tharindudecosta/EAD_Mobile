package com.example.mobileapplication.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobileapplication.entity.CartItem;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Userdata.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE userSession(sessionId INTEGER PRIMARY KEY AUTOINCREMENT, customerId TEXT,email TEXT, password TEXT, fullName TEXT, phoneNo TEXT,)");
        sqLiteDatabase.execSQL("CREATE TABLE cartItems(cartItemId TEXT PRIMARY KEY, title TEXT, totalPrice REAL, quantity INTEGER, imageResource INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists userSession");
        sqLiteDatabase.execSQL("drop table if exists cartItems");
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

    public boolean createSession(String customerId, String email, String password, String fullName, String phoneNo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("customerId", customerId);
        values.put("email", email);
        values.put("password", password);
        values.put("fullName", fullName);
        values.put("phoneNo", phoneNo);

        long result = db.insert("userSession", null, values);

        return result != -1;
    }

    public String getCustomerIdFromSession() {
        SQLiteDatabase db = this.getWritableDatabase();
        String customerId = null;
        String query = "SELECT customerId FROM userSession ORDER BY sessionId DESC LIMIT 1";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("customerId");
            if (columnIndex != -1) {
                customerId = cursor.getString(columnIndex);
            }
        }
        cursor.close();

        return customerId;
    }

    public boolean addToCart(String cartItemId, String title, double totalPrice, int quantity, int imageResource) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cartItemId", cartItemId);
        values.put("title", title);
        values.put("totalPrice", totalPrice);
        values.put("quantity", quantity);
        values.put("imageResource", imageResource);

        long result = db.insertWithOnConflict("cartItems", null, values, SQLiteDatabase.CONFLICT_REPLACE);
        return result != -1;

    }

    public boolean removeFromCart(String cartItemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("cartItems", "cartItemId = ?", new String[]{cartItemId});
        return result != -1;
    }

    public List<CartItem> getAllCartItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<CartItem> cartItemList = new ArrayList<>();
        String query = "SELECT * FROM cartItems";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String cartItemId = cursor.getString(cursor.getColumnIndex("cartItemId"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                double totalPrice = cursor.getDouble(cursor.getColumnIndex("totalPrice"));
                int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                int imageResource = cursor.getInt(cursor.getColumnIndex("imageResource"));

                CartItem item = new CartItem(cartItemId, title, totalPrice, quantity, imageResource);
                cartItemList.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return cartItemList;
    }


}
