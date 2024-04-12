package org.lequochai.fashionshop.entities;

import java.util.List;

public class User {
    //Fields:
    private String email;
    private String password;
    private String fullName;
    private boolean gender;
    private String phoneNumber;
    private String address;
    private String avatar;
    private String permission;
    private List<String> orderedOrders;
    private List<String> createdOrders;
    private String wallet;

    //Constructors:
    public User() {
    }

    public User(String email, String password, String fullName, boolean gender, String phoneNumber, String address, String avatar, String permission, List<String> orderedOrders, List<String> createdOrders, String wallet) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.avatar = avatar;
        this.permission = permission;
        this.orderedOrders = orderedOrders;
        this.createdOrders = createdOrders;
        this.wallet = wallet;
    }

//Getter /setter

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public List<String> getCreatedOrders() {
        return createdOrders;
    }

    public void setCreatedOrders(List<String> createdOrders) {
        this.createdOrders = createdOrders;
    }

    public List<String> getOrderedOrders() {
        return orderedOrders;
    }

    public void setOrderedOrders(List<String> orderedOrders) {
        this.orderedOrders = orderedOrders;
    }
}
