package org.lequochai.fashionshop.services.bodies;

import java.util.Map;

public class CartPostBody {
//    Fields:
    private String id;
    private Integer amount;
    private Map<String, String> metadata;

//    Constructors:
    public CartPostBody() {
    }

    public CartPostBody(String id, Integer amount, Map<String, String> metadata) {
        this.id = id;
        this.amount = amount;
        this.metadata = metadata;
    }

//    Getters / setters:
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
