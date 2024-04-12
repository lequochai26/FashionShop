package org.lequochai.fashionshop.entities;

import java.util.List;
import java.util.Map;

public class ItemType {
    //Fields
    private String id;

    private String name;

    private List<String> items;
    //Constructor
    public ItemType(){

    }

    public ItemType(String id, String name, List<String> items) {
        this.id = id;
        this.name = name;
        this.items = items;
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

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
