package org.lequochai.fashionshop.entities;

import java.util.List;
import java.util.Map;

public class ItemType {
    //Fields
    private String id;

    private String name;

    private List<String> item;
    //Constructor
    public ItemType(){

    }

    public ItemType(String id, String name){
        this.id=id;
        this.name=name;
        this.item=item;
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

    public List<String> getItem() {
        return item;
    }

    public void setItem(List<String> item) {
        this.item = item;
    }
}
