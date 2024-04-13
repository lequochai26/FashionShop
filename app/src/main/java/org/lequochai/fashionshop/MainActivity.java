package org.lequochai.fashionshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.lequochai.fashionshop.adapters.ItemsListViewItemAdapter;
import org.lequochai.fashionshop.controllers.Controller;
import org.lequochai.fashionshop.controllers.cartactivity.BuyController;
import org.lequochai.fashionshop.controllers.mainactivity.LoadAllItemsController;
import org.lequochai.fashionshop.controllers.mainactivity.LoadItemsByKeywordController;
import org.lequochai.fashionshop.controllers.mainactivity.LoadLoggedInUserController;
import org.lequochai.fashionshop.entities.Item;
import org.lequochai.fashionshop.entities.User;
import org.lequochai.fashionshop.services.GlobalService;
import org.lequochai.fashionshop.utils.GlobalChannel;
import org.lequochai.fashionshop.utils.Receiver;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Receiver {
//    Static fields:
    public static final String RECEIVER_NAME = "mainActivity";

//    Fields:
    private User user;

    private ImageView imgAvatar;
    private TextView lblFullName;
    private ImageView btnCart;
    private EditText txtKeyword;
    private ImageView btnSearch;
    private ImageView btnReload;
    private ListView itemsListView;

    private Controller<Void> loadAllItemsController;
    private Controller<String> loadItemsByKeywordController;
    private Controller<Void> loadLoggedInUserController;

//    Constructors:
    public MainActivity() {
//        Subscribe to global channel
        GlobalChannel.getInstance()
                .subscribe(this);
    }

//    Creation method:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Get views
        getViews();

//        Controllers initialize
        initialControllers();

//        Setup
        setupViews();

//        Initial
        init();
    }

//    Setup methods:
    private void getViews() {
//        imgAvatar
        imgAvatar = findViewById(R.id.imgAvatar);

//        txtFullName
        lblFullName = findViewById(R.id.lblFullName);

//        btnCart
        btnCart = findViewById(R.id.btnCart);

//        txtKeyword
        txtKeyword = findViewById(R.id.txtKeyword);

//        btnSearch
        btnSearch = findViewById(R.id.btnSearch);

//        btnReload
        btnReload = findViewById(R.id.btnReload);

//        itemsListView
        itemsListView = findViewById(R.id.itemsListView);
    }

    private void setupViews() {
//        imgAvatar
        imgAvatar.setOnClickListener(
                t -> {
                    if (user == null) {
                        showLoginActivity();
                    }
                    else {
                        showUserCentralActivity();
                    }
                }
        );

//        btnReload
        btnReload.setOnClickListener(
                t -> {
                    loadAllItemsController.execute(null);
                }
        );

//        btnSearch
        btnSearch.setOnClickListener(
                t -> {
                    loadItemsByKeywordController.execute(txtKeyword.getText().toString());
                }
        );

//        btnCart
        btnCart.setOnClickListener(
                t -> {
                    showCartActivity();
                }
        );
    }

    private void initialControllers() {
        loadAllItemsController = new LoadAllItemsController(this);
        loadItemsByKeywordController = new LoadItemsByKeywordController(this);
        loadLoggedInUserController = new LoadLoggedInUserController(this);
    }

    private void init() {
//        Load all items
        loadAllItemsController.execute(null);

//        Load logged in user
        loadLoggedInUserController.execute(null);
    }

    //    Show activities methods:
    private void showCartActivity() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    private void showLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void showUserCentralActivity() {
        Intent intent = new Intent(this, UserCentralActivity.class);
        startActivity(intent);
    }

//    Terminate method:
    @Override
    protected void onDestroy() {
        GlobalService.getInstance(this)
                .shutdown();
        super.onDestroy();
    }

    //    Methods:
    public void loadItems(List<Item> items) {
//        Create adapter
        ItemsListViewItemAdapter adapter = new ItemsListViewItemAdapter(this, items);

//        Set adapter for itemsListView
        itemsListView.setAdapter(adapter);
    }

    public void loadLoggedInUser(User user) {
//        Assign given user for this activity
        this.user = user;

//        imgAvatar
        Picasso.get()
                .load(GlobalService.HOST_HTTP + user.getAvatar().substring(1))
                .into(imgAvatar);

//        lblFullName
        lblFullName.setText(user.getFullName());
    }

    @Override
    public void receive(Object from, Object message) {
        if (message instanceof String) {
            if (message.equals("onLogin")) {
                loadLoggedInUserController.execute(null);
            }

            if (message.equals(BuyController.GET_MAINACTIVITY_USER_MESSAGE)) {
                GlobalChannel.getInstance()
                        .send(this, BuyController.class, user);
            }
        }
    }

    @Override
    public String getReceiverName() {
        return RECEIVER_NAME;
    }

    //    Getters / setters:
    public ImageView getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(ImageView imgAvatar) {
        this.imgAvatar = imgAvatar;
    }

    public TextView getLblFullName() {
        return lblFullName;
    }

    public void setLblFullName(TextView lblFullName) {
        this.lblFullName = lblFullName;
    }

    public ImageView getBtnCart() {
        return btnCart;
    }

    public void setBtnCart(ImageView btnCart) {
        this.btnCart = btnCart;
    }

    public EditText getTxtKeyword() {
        return txtKeyword;
    }

    public void setTxtKeyword(EditText txtKeyword) {
        this.txtKeyword = txtKeyword;
    }

    public ImageView getBtnSearch() {
        return btnSearch;
    }

    public void setBtnSearch(ImageView btnSearch) {
        this.btnSearch = btnSearch;
    }

    public ImageView getBtnReload() {
        return btnReload;
    }

    public void setBtnReload(ImageView btnReload) {
        this.btnReload = btnReload;
    }

    public ListView getItemsListView() {
        return itemsListView;
    }

    public void setItemsListView(ListView itemsListView) {
        this.itemsListView = itemsListView;
    }

    public Controller<Void> getLoadAllItemsController() {
        return loadAllItemsController;
    }
}