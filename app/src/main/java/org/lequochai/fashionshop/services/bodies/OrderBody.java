package org.lequochai.fashionshop.services.bodies;


import java.util.List;
import java.util.Map;

public class OrderBody {

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

   private  String type;

   private Number totalPrice;
   private User orderedBy;
   private List<Map<String,Object>> items;

   private String paymentMethod;

    public OrderBody(String type, Number totalPrice, User orderedBy, List<Map<String, Object>> items, String paymentMethod) {
        this.type = type;
        this.totalPrice = totalPrice;
        this.orderedBy = orderedBy;
        this.items = items;
        this.paymentMethod = paymentMethod;
    }

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

    public User getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(User orderedBy) {
        this.orderedBy = orderedBy;
    }

    public List<Map<String, Object>> getItems() {
        return items;
    }

    public void setItems(List<Map<String, Object>> items) {
        this.items = items;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
