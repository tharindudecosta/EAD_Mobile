package com.example.mobileapplication.entity;

public class User {
    private String _id;
    private String name;
    private String nic;
    private String email;
    private String contactNo;
    private String role;
    private String gender;
    private boolean isActive;
    private String password;

    public User(String _id, String name, String nic, String email, String contactNo, String role, String gender, boolean isActive, String password) {
        this._id = _id;
        this.name = name;
        this.nic = nic;
        this.email = email;
        this.contactNo = contactNo;
        this.role = role;
        this.gender = gender;
        this.isActive = isActive;
        this.password = password;
    }

    // Getters and Setters
    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", email='" + email + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", role='" + role + '\'' +
                ", gender='" + gender + '\'' +
                ", isActive=" + isActive +
                ", password='" + password + '\'' +
                '}';
    }
}
