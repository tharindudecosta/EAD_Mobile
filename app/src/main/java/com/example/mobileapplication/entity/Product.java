package com.example.mobileapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable {
    private String id;
    private String productName;
    private double unitPrice;
    private String category;
    private String vendor;
    private boolean isActive;
    private int quantity = 1;
    private int imageResource;

    // Constructor
    public Product(String id, String productName, double unitPrice, String category, String vendor, boolean isActive, int quantity, int imageResource) {
        this.id = id;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.category = category;
        this.vendor = vendor;
        this.isActive = isActive;
        this.quantity = quantity;
        this.imageResource = imageResource;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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

    /*********************/

    // Parcelable implementation
    protected Product(Parcel in) {
        id = in.readString();
        productName = in.readString();
        unitPrice = in.readDouble();
        category = in.readString();
        vendor = in.readString();
        isActive = in.readByte() != 0; // isActive is a boolean, so we use a byte (0 or 1)
        quantity = in.readInt();
        imageResource = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(productName);
        parcel.writeDouble(unitPrice);
        parcel.writeString(category);
        parcel.writeString(vendor);
        parcel.writeByte((byte) (isActive ? 1 : 0)); // Write isActive as a byte
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
