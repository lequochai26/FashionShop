package org.lequochai.fashionshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.lequochai.fashionshop.controllers.Controller;
import org.lequochai.fashionshop.controllers.usercentralactivity.LoadLoggedInUserController;
import org.lequochai.fashionshop.entities.User;
import org.lequochai.fashionshop.services.GlobalService;

public class UserCentralActivity extends AppCompatActivity {
//    Fields:
    private ImageView btnBack;
    private ImageView imgAvatar;
    private TextView lblFullName;
    private TextView lblUpdatePersonalInfo;
    private TextView lblOrderedOrders;
    private TextView lblLogout;

    private Controller<Void> loadLoggedInUserController;

//    Constructors:
    public UserCentralActivity() {

    }

//    Creation method:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_central);

//        Get views
        getViews();

//        Initial controllers
        initialControllers();

//        Setup views
        setupViews();

//        Init
        init();
    }

//    Setup methods:
    private void getViews() {
        btnBack = findViewById(R.id.btnBack2);
        imgAvatar = findViewById(R.id.imgAvatar3);
        lblFullName = findViewById(R.id.lblFullName2);
        lblUpdatePersonalInfo = findViewById(R.id.lblUpdatePersonalInfo);
        lblOrderedOrders = findViewById(R.id.lblOrderedOrders);
        lblLogout = findViewById(R.id.lblLogout);
    }

    private void initialControllers() {
        loadLoggedInUserController = new LoadLoggedInUserController(this);
//        TODO
    }

    private void setupViews() {
        btnBack.setOnClickListener(
                t -> finish()
        );

//        TODO
    }

    private void init() {
//        Call load logged in user controller
        loadLoggedInUserController.execute(null);
    }

//    Methods:
    public void loadUser(User user) {
        Picasso.get()
                .load(GlobalService.HOST_HTTP + user.getAvatar().substring(1))
                .into(imgAvatar);

        lblFullName.setText(user.getFullName());
    }
}