package org.lequochai.fashionshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.lequochai.fashionshop.controllers.Controller;
import org.lequochai.fashionshop.controllers.usercentralactivity.LoadLoggedInUserController;
import org.lequochai.fashionshop.controllers.usercentralactivity.LogoutController;
import org.lequochai.fashionshop.entities.User;
import org.lequochai.fashionshop.services.GlobalService;
import org.lequochai.fashionshop.utils.GlobalChannel;
import org.lequochai.fashionshop.utils.Receiver;

public class UserCentralActivity extends AppCompatActivity implements Receiver {
//    Static fields:
    public static final String RECEIVER_NAME = "userCentralActivity";
    public static final String MESSAGE_RELOAD_USER = "reloadUser";

//    Fields:
    private ImageView btnBack;
    private ImageView imgAvatar;
    private TextView lblFullName;
    private TextView lblUpdatePersonalInfo;
    private TextView lblOrderedOrders;
    private TextView lblLogout;

    private Controller<Void> loadLoggedInUserController;
    private Controller<Void> logoutController;

//    Constructors:
    public UserCentralActivity() {
        GlobalChannel.getInstance()
                .subscribe(this);
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
        logoutController = new LogoutController(this);
//        TODO
    }

    private void setupViews() {
        btnBack.setOnClickListener(
                t -> finish()
        );

        lblLogout.setOnClickListener(
                t -> logoutController.execute(null)
        );

        lblUpdatePersonalInfo.setOnClickListener(
                t -> showUpdatePersonalInfoActivity()
        );

        lblOrderedOrders.setOnClickListener(
                t -> showOrderedOrdersActivity()
        );
    }

    private void init() {
//        Call load logged in user controller
        loadLoggedInUserController.execute(null);
    }

//    Show activity methods:
    private void showUpdatePersonalInfoActivity() {
        Intent intent = new Intent(this, UpdatePersonalInfoActivity.class);
        startActivity(intent);
    }

    private void showOrderedOrdersActivity() {
        Intent intent = new Intent(this, OrderedOrdersActivity.class);
        startActivity(intent);
    }

//    Methods:
    public void loadUser(User user) {
        Picasso.get()
                .load(GlobalService.HOST_HTTP + user.getAvatar().substring(1))
                .into(imgAvatar);

        lblFullName.setText(user.getFullName());
    }

    @Override
    public void receive(Object from, Object message) {
        if (message != null) {
            if (message instanceof String) {
                if (message.equals(MESSAGE_RELOAD_USER)) {
                    loadLoggedInUserController.execute(null);
                }
            }
        }
    }

    @Override
    public String getReceiverName() {
        return RECEIVER_NAME;
    }
}