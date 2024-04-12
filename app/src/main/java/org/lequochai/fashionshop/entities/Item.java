package org.lequochai.fashionshop.entities;

import java.util.List;

public class Item {
//    Innter class:
    public static class ItemType {
        private String id;
        private String name;

        public ItemType() {
        }

        public ItemType(String id, String name) {
            this.id = id;
            this.name = name;
        }

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
    }

    public static class Brand {
        private String id;
        private String name;

        public Brand() {
        }

        public Brand(String id, String name) {
            this.id = id;
            this.name = name;
        }

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
    }

//    Fields:
    private String id;
    private String avatar;
    private String name;
    private String description;
    private double price;
    private double buyPrice;
    private int amount;
    private boolean gender;
    private ItemType type;
    private Brand brand;
    private List<String> images;
    private List<String> orders;

//    Constructors:
    public Item() {
    }

    public Item(String id, String avatar, String name, String description, double price, double buyPrice, int amount, boolean gender, ItemType type, Brand brand, List<String> images, List<String> orders) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.description = description;
        this.price = price;
        this.buyPrice = buyPrice;
        this.amount = amount;
        this.gender = gender;
        this.type = type;
        this.brand = brand;
        this.images = images;
        this.orders = orders;
    }

//    Getters / setters:
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getOrders() {
        return orders;
    }

    public void setOrders(List<String> orders) {
        this.orders = orders;
    }
}
