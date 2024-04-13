package org.lequochai.fashionshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import org.lequochai.fashionshop.R;
import org.lequochai.fashionshop.entities.User;
import org.lequochai.fashionshop.utils.DialogHelper;
import org.lequochai.fashionshop.utils.GlobalChannel;
import org.lequochai.fashionshop.utils.Receiver;

public class UpdatePersonalInfoActivity extends AppCompatActivity implements Receiver {
//    Static fields:
    public static final String RECEIVER_NAME = "updatePersonalInfoActivity";

//    Fields:
    private ImageView btnBack;
    private EditText txtFullName;
    private Switch switchGender;
    private EditText txtPhoneNumber;
    private EditText txtAddress;

    private User user;

//    Constructors:
    public UpdatePersonalInfoActivity() {
        GlobalChannel.getInstance()
                .subscribe(this);
    }

//    Creation method:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_personal_info);

        getViews();

        initialControllers();

        setupViews();

        init();
    }

//    Setup methods:
    private void getViews() {
        btnBack = findViewById(R.id.btnBack3);
        txtFullName = findViewById(R.id.txtFullName);
        switchGender = findViewById(R.id.switchGender);
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
        txtAddress = findViewById(R.id.txtAddress);
    }

    private void initialControllers() {

    }

    private void setupViews() {
        btnBack.setOnClickListener(
                t -> finish()
        );

        switchGender.setOnCheckedChangeListener(
                (view, isChecked) -> switchGender.setText(isChecked ? "Nam" : "Nữ")
        );
    }

//    Initial method:
    private void init() {
//        Request user from MainActivity
        GlobalChannel.getInstance()
                .send(
                        this, MainActivity.class, MainActivity.REQUEST_GETUSER
                );
    }

//    Methods:
    @Override
    public void receive(Object from, Object message) {
        if (message == null) {
            DialogHelper.showAlertDialog(this, "Chưa đăng nhập", "Giao diện này chỉ dành cho " +
                    "người dùng đã đăng nhập!");
            finish();
            return;
        }

        user = (User)message;
        loadUser();
    }

    @Override
    public String getReceiverName() {
        return RECEIVER_NAME;
    }

    public void loadUser() {
//        txtFullName
        txtFullName.setHint("Họ và tên: " + user.getFullName());

//        switchGender
        switchGender.setChecked(user.isGender());

//        txtPhoneNumber
        txtPhoneNumber.setHint("Số điện thoại: " + user.getPhoneNumber());

//        txtAddress
        txtAddress.setHint("Địa chỉ: " + user.getAddress());
    }
}