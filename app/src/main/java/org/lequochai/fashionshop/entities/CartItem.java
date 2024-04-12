package org.lequochai.fashionshop.entities;

import java.util.Map;

public class CartItem {
//    Inner class:
    public static class Item {
//        Fields:
        private String id;
        private String name;
        private String avatar;
        private double price;

//        Constructors:
        public Item() {
        }

        public Item(String id, String name, String avatar, double price) {
            this.id = id;
            this.name = name;
            this.avatar = avatar;
            this.price = price;
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

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

//    Fields:
    private Item item;
    private int amount;
    private Map<String, String> metadata;

//    Constructors:
    public CartItem() {
    }

    public CartItem(Item item, int amount, Map<String, String> metadata) {
        this.item = item;
        this.amount = amount;
        this.metadata = metadata;
    }

//    Getters / setters:
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
