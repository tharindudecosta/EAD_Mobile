package com.example.mobileapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class OrderSummary implements Parcelable {

    private String id;
    private String orderDate;
    private String orderStatus;
    private int numberOfItems;
    private double totalPrice;

    private String customerId;
    private List<String> productIds;
    private String deliveryDate;
    private boolean isActive;
    private List<Product> productList;

    private List<CartItem> cartItems;

    public OrderSummary() {
    }

    // Constructor
    public OrderSummary(String id, String orderDate, String orderStatus, int numberOfItems, double totalPrice,
                        String customerId, List<String> productIds, String deliveryDate, boolean isActive,
                        List<Product> productList, List<CartItem> cartItems) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.numberOfItems = numberOfItems;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.productIds = productIds != null ? productIds : new ArrayList<>();
        this.deliveryDate = deliveryDate;
        this.isActive = isActive;
        this.productList = productList != null ? productList : new ArrayList<>();
        this.cartItems = cartItems != null ? cartItems : new ArrayList<>();
    }

    // Getters and Setters for all fields
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds != null ? productIds : new ArrayList<>();
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList != null ? productList : new ArrayList<>();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems != null ? cartItems : new ArrayList<>();
    }

    // Parcelable implementation
    protected OrderSummary(Parcel in) {
        id = in.readString();
        orderDate = in.readString();
        orderStatus = in.readString();
        numberOfItems = in.readInt();
        totalPrice = in.readDouble();
        customerId = in.readString();
        productIds = in.createStringArrayList(); // Reading List of Strings
        deliveryDate = in.readString();
        isActive = in.readByte() != 0; // Read boolean as byte
        productList = in.createTypedArrayList(Product.CREATOR); // Reading List of Products
        cartItems = in.createTypedArrayList(CartItem.CREATOR); // Reading List of CartItems
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
        dest.writeString(id);
        dest.writeString(orderDate);
        dest.writeString(orderStatus);
        dest.writeInt(numberOfItems);
        dest.writeDouble(totalPrice);
        dest.writeString(customerId);
        dest.writeStringList(productIds); // Writing List of Strings
        dest.writeString(deliveryDate);
        dest.writeByte((byte) (isActive ? 1 : 0)); // Writing boolean as byte
        dest.writeTypedList(productList); // Writing List of Products
        dest.writeTypedList(cartItems); // Writing List of CartItems
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
