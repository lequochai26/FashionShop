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
    public static final String HOST = "192.168.1.14";
    public static final String PORT = "3000";
    public static final String HOST_HTTP = "http://" + HOST + ":" + PORT + "/";

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
    private CartService cartService;

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
                .baseUrl(HOST_HTTP)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

//        Build service
        itemService = retrofit.create(ItemService.class);
        userService = retrofit.create(UserService.class);
        itemTypeService = retrofit.create(ItemTypeService.class);
        brandService = retrofit.create(BrandService.class);
        orderService = retrofit.create(OrderService.class);
        cartService = retrofit.create(CartService.class);
    }

//    Methods:
    public void shutdown() {
        dbHelper.shutdown();
    }

//    Getters / setters
    public ItemService getItemService() {
        return itemService;
    }

    public FashionShopDBHelper getDbHelper() {
        return dbHelper;
    }

    public UserService getUserService() {
        return userService;
    }

    public ItemTypeService getItemTypeService() {
        return itemTypeService;
    }

    public BrandService getBrandService() {
        return brandService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public CartService getCartService() {
        return cartService;
    }
}
