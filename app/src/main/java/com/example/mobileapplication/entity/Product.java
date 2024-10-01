package com.example.mobileapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable {
    private String productId;
    private String title;
    private double unitPrice;
    private int quantity;
    private int imageResource;

    public Product(String productId, String title, double unitPrice, int quantity, int imageResource) {
        this.productId = productId;
        this.title = title;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.imageResource = imageResource;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    /*********************/

    protected Product(Parcel in) {
        productId = in.readString();
        title = in.readString();
        unitPrice = in.readDouble();
        quantity = in.readInt();
        imageResource = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(productId);
        parcel.writeString(title);
        parcel.writeDouble(unitPrice);
        parcel.writeInt(quantity);
        parcel.writeInt(imageResource);
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
