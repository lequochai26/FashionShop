package org.lequochai.fashionshop.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GlobalService {
//    Static fields:
    private static GlobalService instance;

//    Static methods:
    public static GlobalService getInstance() {
        if (instance == null) {
            instance = new GlobalService();
        }

        return instance;
    }

    //    Fields:
    private ItemService itemService;

//    Constructors:
    private GlobalService() {
//        Build retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        itemService = retrofit.create(ItemService.class);
    }

//    Methods:
    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
}
