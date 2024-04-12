package org.lequochai.fashionshop.services.bodies;


import java.util.List;
import java.util.Map;

public class OrderBody {



   private  String type;

   private Number totalPrice;
   private String orderedBy;
   private List<Map<String,Object>> items;

   private String paymentMethod;

   public OrderBody(){}

    public OrderBody(String type, Number totalPrice, String orderedBy, List<Map<String, Object>> items, String paymentMethod) {
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

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
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
