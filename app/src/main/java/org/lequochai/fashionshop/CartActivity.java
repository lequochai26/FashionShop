package org.lequochai.fashionshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import org.lequochai.fashionshop.adapters.CartItemsListViewItemAdapter;
import org.lequochai.fashionshop.controllers.Controller;
import org.lequochai.fashionshop.controllers.cartactivity.LoadCartController;
import org.lequochai.fashionshop.entities.CartItem;

import java.util.List;

public class CartActivity extends AppCompatActivity {
//    Fields:
    private ImageView btnBack;
    private ListView cartItemsListView;

    private Controller<Void> loadCartController;

//    Constructors:
    public CartActivity() {

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

//    Methods:
    public void showCart(List<CartItem> cartItems) {
//        Create adapter
        CartItemsListViewItemAdapter adapter = new CartItemsListViewItemAdapter(this, cartItems);

//        Set adapter for cartItemsListView
        cartItemsListView.setAdapter(adapter);
    }
}