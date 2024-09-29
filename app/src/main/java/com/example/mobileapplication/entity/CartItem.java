package com.example.mobileapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class CartItem implements Parcelable {

    private String cartItemId;
    private String title;
    private double totalPrice;
    private int quantity;
    private int imageResource;

    // Constructor
    public CartItem(String cartItemId, String title, double price, int quantity, int imageResource) {
        this.cartItemId = cartItemId;
        this.title = title;
        this.totalPrice = price;
        this.quantity = quantity;
        this.imageResource = imageResource;
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }


    // Parcelable implementation
    protected CartItem(Parcel in) {
        cartItemId = in.readString();
        title = in.readString();
        totalPrice = in.readDouble();
        quantity = in.readInt();
        imageResource = in.readInt();
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cartItemId);
        dest.writeString(title);
        dest.writeDouble(totalPrice);
        dest.writeInt(quantity);
        dest.writeInt(imageResource);
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
