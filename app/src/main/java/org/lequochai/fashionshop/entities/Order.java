package org.lequochai.fashionshop.entities;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Order {
//    Inner enums:
    public enum Status {
        APPROVEMENT_AWAITING,
        DELIVERING,
        SUCCESS,
        PAYMENT_AWAITING,
        CANCELLED
    }

//    Inner classes:
    public static  class User{
        private String email;
        private  String fullName;

        public  User(){}

        public User(String email, String fullName) {
            this.email = email;
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }
    }

    public static class Item {
//        Fields:
        private String id;
        private String name;
        private int amount;
        private double price;
        private Map<String, String> metadata;
        private String avatar;

//        Constructors:
        public Item() {
        }

        public Item(String id, String name, int amount, double price, Map<String, String> metadata, String avatar) {
            this.id = id;
            this.name = name;
            this.amount = amount;
            this.price = price;
            this.metadata = metadata;
            this.avatar = avatar;
        }

//        Getters / setters:
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

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Map<String, String> getMetadata() {
            return metadata;
        }

        public void setMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

//    Static methods:
    public static String getStatusTitle(Status status) {
        switch (status) {
            case SUCCESS: return "Đã hoàn thành";
            case CANCELLED: return "Đã hủy";
            case DELIVERING: return "Đang giao";
            case PAYMENT_AWAITING: return "Đang chờ thanh toán";
            case APPROVEMENT_AWAITING: return "Đang chờ xác nhận";
        }
        return null;
    }

//    Fields:
    private String id;
    private String type;
    private Date date;
    private double totalPrice;
    private User createdBy;
    private User orderedBy;
    private List<Item> items;
    private String status;
    private String paymentMethod;

//    Constructors:
    public Order() {
    }

    public Order(String id, String type, Date date, double totalPrice, User createdBy, User orderedBy, List<Item> items, String status, String paymentMethod) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.totalPrice = totalPrice;
        this.createdBy = createdBy;
        this.orderedBy = orderedBy;
        this.items = items;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

//    Getters / setters:
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(User orderedBy) {
        this.orderedBy = orderedBy;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
