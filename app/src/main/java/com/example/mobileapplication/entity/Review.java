package com.example.mobileapplication.entity;

public class Review {
    private String vendor;
    private String comment;
    private int rating;

    public Review(String vendor, String comment, int rating) {
        this.vendor = vendor;
        this.comment = comment;
        this.rating = rating;
    }

    public String getVendor() {
        return vendor;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }
}
