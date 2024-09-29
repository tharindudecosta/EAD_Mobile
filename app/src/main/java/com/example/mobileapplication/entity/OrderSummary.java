package com.example.mobileapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class OrderSummary implements Parcelable {

    private String orderId;
    private String orderDate;
    private String orderStatus;
    private int noOfItems;
    private double totalPrice;

    private List<CartItem> cartItems;

    public OrderSummary(String orderId, String orderDate, String orderStatus, int noOfItems, double totalPrice, List<CartItem> cartItems) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.noOfItems = noOfItems;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }


    // Parcelable implementation
    protected OrderSummary(Parcel in) {
        orderId = in.readString();
        orderDate = in.readString();
        orderStatus = in.readString();
        noOfItems = in.readInt();
        totalPrice = in.readDouble();

        // Read the list of CartItems
        cartItems = new ArrayList<>();
        in.readList(cartItems, CartItem.class.getClassLoader());
    }

    public static final Creator<OrderSummary> CREATOR = new Creator<OrderSummary>() {
        @Override
        public OrderSummary createFromParcel(Parcel in) {
            return new OrderSummary(in);
        }

        @Override
        public OrderSummary[] newArray(int size) {
            return new OrderSummary[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderId);
        dest.writeString(orderDate);
        dest.writeString(orderStatus);
        dest.writeInt(noOfItems);
        dest.writeDouble(totalPrice);

        // Write the list of CartItems
        dest.writeList(cartItems);
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
