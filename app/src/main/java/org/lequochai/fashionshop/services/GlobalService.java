package org.lequochai.fashionshop.services;

import android.content.Context;

import org.lequochai.fashionshop.models.FashionShopDBHelper;
import org.lequochai.fashionshop.services.cookiejars.RestServiceCookieJar;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GlobalService {
//    Static fields:
    private static GlobalService instance;

//    Static methods:
    public static GlobalService getInstance(Context context) {
        if (instance == null) {
            instance = new GlobalService(context);
        }

        return instance;
    }

    //    Fields:
    private FashionShopDBHelper dbHelper;
    private ItemService itemService;
    private UserService userService;
    private ItemTypeService itemTypeService;
    private BrandService brandService;
    private OrderService orderService;

//    Constructors:
    private GlobalService(Context context) {
//        Create dbHelp
        dbHelper = new FashionShopDBHelper(context);

//        Build retrofit
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(
                        new RestServiceCookieJar(dbHelper)
                )
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

//        Build service
        itemService = retrofit.create(ItemService.class);
        userService = retrofit.create(UserService.class);
        itemTypeService = retrofit.create(ItemTypeService.class);
        brandService = retrofit.create(BrandService.class);
        orderService = retrofit.create(OrderService.class);
    }

//    Methods:
    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public FashionShopDBHelper getDbHelper() {
        return dbHelper;
    }

    public void setDbHelper(FashionShopDBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ItemTypeService getItemTypeService() {
        return itemTypeService;
    }

    public void setItemTypeService(ItemTypeService itemTypeService) {
        this.itemTypeService = itemTypeService;
    }

    public BrandService getBrandService() {
        return brandService;
    }

    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
