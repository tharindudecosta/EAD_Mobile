package com.example.mobileapplication.entity;


public class InventoryItem {
    private String id;
    private String productId;
    private int quantity;
    private int lowStockThreshold;
    private String lastUpdated;

    // Constructors
    public InventoryItem() {}

    public InventoryItem(String id, String productId, int quantity, int lowStockThreshold, String lastUpdated) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.lowStockThreshold = lowStockThreshold;
        this.lastUpdated = lastUpdated;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLowStockThreshold() {
        return lowStockThreshold;
    }

    public void setLowStockThreshold(int lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "InventoryItem{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", lowStockThreshold=" + lowStockThreshold +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
