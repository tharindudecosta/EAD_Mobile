package com.example.mobileapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Vendor implements Parcelable {
    private String id;
    private String name;
    private String contactInfo;
    private double averageReviewScore;
    private List<Review> reviews;

    public Vendor(String id, String name, String contactInfo, double averageReviewScore, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.averageReviewScore = averageReviewScore;
        this.reviews = reviews != null ? reviews : new ArrayList<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public double getAverageReviewScore() {
        return averageReviewScore;
    }

    public void setAverageReviewScore(double averageReviewScore) {
        this.averageReviewScore = averageReviewScore;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews != null ? reviews : new ArrayList<>();
    }

    // Parcelable implementation
    protected Vendor(Parcel in) {
        id = in.readString();
        name = in.readString();
        contactInfo = in.readString();
        averageReviewScore = in.readDouble();
        reviews = in.createTypedArrayList(Review.CREATOR); // Reading List of Reviews
    }

    public static final Creator<Vendor> CREATOR = new Creator<Vendor>() {
        @Override
        public Vendor createFromParcel(Parcel in) {
            return new Vendor(in);
        }

        @Override
        public Vendor[] newArray(int size) {
            return new Vendor[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(contactInfo);
        dest.writeDouble(averageReviewScore);
        dest.writeTypedList(reviews); // Writing List of Reviews
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
