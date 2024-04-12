package org.lequochai.fashionshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.lequochai.fashionshop.controllers.Controller;
import org.lequochai.fashionshop.controllers.mainactivity.LoadAllItemsController;
import org.lequochai.fashionshop.entities.Item;

import java.util.List;

public class MainActivity extends AppCompatActivity {
//    Fields:
    private ImageView imgAvatar;
    private TextView txtFullName;
    private ImageView btnCart;
    private EditText txtKeyword;
    private ImageView btnSearch;
    private ImageView btnReload;
    private ListView itemsListView;

    private Controller<Void> loadAllItemsController;

//    Constructors:
    public MainActivity() {

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

//        Load all items
        loadAllItemsController.execute(null);
    }

//    Setup methods:
    private void getViews() {
//        imgAvatar
        imgAvatar = findViewById(R.id.imgAvatar);

//        txtFullName
        txtFullName = findViewById(R.id.txtFullName);

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
//        btnReload
        btnReload.setOnClickListener(
                t -> {
                    loadAllItemsController.execute(null);
                }
        );
    }

    private void initialControllers() {
        loadAllItemsController = new LoadAllItemsController(this);
    }

//    Methods:
    public void loadItems(List<Item> items) {
//        Create adapter
        ItemsListItemAdapter adapter = new ItemsListItemAdapter(this, items);

//        Set adapter for itemsListView
        itemsListView.setAdapter(adapter);
    }
}