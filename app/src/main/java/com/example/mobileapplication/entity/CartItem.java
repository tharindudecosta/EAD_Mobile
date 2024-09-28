package com.example.mobileapplication.entity;

public class CartItem {
    private String title;
    private double price;
    private int quantity;
    private int imageResource;

    public CartItem(String title, double price, int quantity, int imageResource) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getImageResource() {
        return imageResource;
    }
}
