package org.lequochai.fashionshop.services.bodies;

import org.lequochai.fashionshop.entities.User;

import java.util.Date;
import java.util.List;

public class OrderBody {
    private String id;
    private String type;
    private Date date;
    private Number totalPrice;
    private String metadata;
    private User createdBy;
    private User orderedBy;
    private List<String> orders;
    private String status;
    private String paymentMethod;

    public  OrderBody(){}

    public OrderBody(String id, String type, Date date, Number totalPrice, String metadata, User createdBy, User orderedBy, List<String> orders, String status, String paymentMethod) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.totalPrice = totalPrice;
        this.metadata = metadata;
        this.createdBy = createdBy;
        this.orderedBy = orderedBy;
        this.orders = orders;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

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

    public Number getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Number totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
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

    public List<String> getOrders() {
        return orders;
    }

    public void setOrders(List<String> orders) {
        this.orders = orders;
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
