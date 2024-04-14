package org.lequochai.fashionshop.entities;

import java.util.List;
import java.util.Map;

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

        @Override
        public String toString() {
            return "ItemType{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
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

        @Override
        public String toString() {
            return "Brand{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
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

    public static class Metadata {
//        Fields:
        private Map<String, List<String>> options;
        private List<Map<String, Object>> mappings;

//        Constructors:
        public Metadata() {
        }

        public Metadata(Map<String, List<String>> options, List<Map<String, Object>> mappings) {
            this.options = options;
            this.mappings = mappings;
        }

//        Methods:
        @Override
        public String toString() {
            return "Metadata{" +
                    "options=" + options +
                    ", mappings=" + mappings +
                    '}';
        }

        public double getMaxPrice() {
            double max = (double)mappings.get(0).get("price");

            for (Map<String, Object> mapping : mappings) {
                double price = (double)mapping.get("price");

                if (price > max) {
                    max = price;
                }
            }

            return max;
        }

        public double getMinPrice() {
            double min = (double)mappings.get(0).get("price");

            for (Map<String, Object> mapping : mappings) {
                double price = (double)mapping.get("price");

                if (price < min) {
                    min = price;
                }
            }

            return min;
        }

        public int getTotalAmount() {
            int amount = 0;

            for (Map<String, Object> mapping : mappings) {
                amount += (double)mapping.get("amount");
            }

            return (int)amount;
        }

        public Map<String, Object> searchMapping(Map<String, String> selection) {
            for (Map<String, Object> mapping : mappings) {
                boolean match = true;

                for (String key : selection.keySet()) {
                    if (!mapping.get(key).equals(selection.get(key))) {
                        match = false;
                        break;
                    }
                }

                if (!match) {
                    continue;
                }

                return mapping;
            }

            return null;
        }

        //        Getters / setters:
        public Map<String, List<String>> getOptions() {
            return options;
        }

        public void setOptions(Map<String, List<String>> options) {
            this.options = options;
        }

        public List<Map<String, Object>> getMappings() {
            return mappings;
        }

        public void setMappings(List<Map<String, Object>> mappings) {
            this.mappings = mappings;
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
    private Metadata metadata;

//    Constructors:
    public Item() {
    }

    public Item(String id, String avatar, String name, String description, double price, double buyPrice, int amount, boolean gender, ItemType type, Brand brand, List<String> images, List<String> orders, Metadata metadata) {
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
        this.metadata = metadata;
    }

//    Methods:


    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", buyPrice=" + buyPrice +
                ", amount=" + amount +
                ", gender=" + gender +
                ", type=" + type +
                ", brand=" + brand +
                ", images=" + images +
                ", orders=" + orders +
                ", metadata=" + metadata +
                '}';
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

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
