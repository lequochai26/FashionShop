package org.lequochai.fashionshop.services.bodies;


import java.util.List;
import java.util.Map;

public class OrderBody {
//    Inner classes:
    public static class Item {
//        Fields:
        private String id;
        private int amount;
        private Map<String, String> metadata;

//        Constructors:
        public Item() {
        }

        public Item(String id, int amount, Map<String, String> metadata) {
            this.id = id;
            this.amount = amount;
            this.metadata = metadata;
        }

//        Getters / setters:
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

//    Fields:
    private  String type;
    private Number totalPrice;
    private String orderedBy;
    private List<Item> items;
    private String paymentMethod;

//    Constructors:
    public OrderBody() {
    }

    public OrderBody(String type, Number totalPrice, String orderedBy, List<Item> items, String paymentMethod) {
        this.type = type;
        this.totalPrice = totalPrice;
        this.orderedBy = orderedBy;
        this.items = items;
        this.paymentMethod = paymentMethod;
    }

//    Getters / setters:
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Number getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Number totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
