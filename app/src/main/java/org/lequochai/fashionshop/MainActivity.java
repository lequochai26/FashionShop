package org.lequochai.fashionshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//    Fields:
    private ImageView imgAvatar;
    private TextView txtFullName;
    private ImageView btnCart;
    private EditText txtKeyword;
    private ImageView btnSearch;
    private ImageView btnReload;
    private ListView itemsListView;

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
}