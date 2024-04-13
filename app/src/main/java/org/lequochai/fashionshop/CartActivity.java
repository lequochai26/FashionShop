package org.lequochai.fashionshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import org.lequochai.fashionshop.adapters.CartItemsListViewItemAdapter;
import org.lequochai.fashionshop.controllers.Controller;
import org.lequochai.fashionshop.controllers.cartactivity.LoadCartController;
import org.lequochai.fashionshop.entities.CartItem;
import org.lequochai.fashionshop.utils.GlobalChannel;
import org.lequochai.fashionshop.utils.Receiver;

import java.util.List;

public class CartActivity extends AppCompatActivity implements Receiver {
//    Static fields:
    public static final String RECEIVER_NAME = "cartActivity";

//    Fields:
    private ImageView btnBack;
    private ListView cartItemsListView;

    private Controller<Void> loadCartController;

//    Constructors:
    public CartActivity() {
//        Subscribe to GlobalChannel
        GlobalChannel.getInstance()
                .subscribe(this);
    }

//    Creation method:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

//        getViews
        getViews();

//        initialControllers
        initialControllers();

//        Setup views
        setupViews();

//        initial
        init();
    }

//    Setup methods:
    private void getViews() {
//        btnBack
        btnBack = findViewById(R.id.btnBackCart);

//        cartItemsListView
        cartItemsListView = findViewById(R.id.cartItemsListView);
    }

    private void initialControllers() {
//        loadCartController
        loadCartController = new LoadCartController(this);
    }

    private void setupViews() {
//        btnBack
        btnBack.setOnClickListener(
                t -> finish()
        );
    }

    private void init() {
//        Load cart
        loadCartController.execute(null);
    }

//    Terminate methods:
    @Override
    protected void onDestroy() {
//        unsubscribe to GlobalChannel
        GlobalChannel.getInstance()
                .unsubscribe(this);

//        Call super's same method
        super.onDestroy();
    }

    //    Methods:
    @Override
    public void receive(Object from, Object message) {
        if (message instanceof String) {
            if (message.equals("onAdd")) {
                init();
            }
        }
    }

    @Override
    public String getReceiverName() {
        return RECEIVER_NAME;
    }

    public void showCart(List<CartItem> cartItems) {
//        Create adapter
        CartItemsListViewItemAdapter adapter = new CartItemsListViewItemAdapter(this, cartItems);

//        Set adapter for cartItemsListView
        cartItemsListView.setAdapter(adapter);
    }
}