package com.example.mobileapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable {
    private String reviewId;
    private String customerId;
    private int rating;
    private String comment;
    private String timestamp;
    private String vendorId;
    private String vendorName;

    public Review(String reviewId, String customerId, int rating, String comment, String timestamp, String vendorId, String vendorName) {
        this.reviewId = reviewId;
        this.customerId = customerId;
        this.rating = rating;
        this.comment = comment;
        this.timestamp = timestamp;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
    }

    // Getters and Setters
    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    // Parcelable implementation
    protected Review(Parcel in) {
        reviewId = in.readString();
        customerId = in.readString();
        rating = in.readInt();
        comment = in.readString();
        timestamp = in.readString();
        vendorId = in.readString();
        vendorName = in.readString();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(reviewId);
        dest.writeString(customerId);
        dest.writeInt(rating);
        dest.writeString(comment);
        dest.writeString(timestamp);
        dest.writeString(vendorId);   // Writing vendorId
        dest.writeString(vendorName); // Writing vendorName
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
