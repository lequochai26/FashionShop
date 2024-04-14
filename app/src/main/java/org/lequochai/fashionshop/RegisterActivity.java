package org.lequochai.fashionshop;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
//    Fields:
    private ImageView btnBack;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtFullName;
    private Switch switchGender;
    private EditText txtAddress;
    private EditText txtPhoneNumber;
    private Button btnRegister;

//    Constructors:
    public RegisterActivity() {

    }

//    Creation method:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getViews();

        initialControllers();

        setupViews();
    }

//    Setup methods:
    private void getViews() {
        btnBack = findViewById(R.id.btnBack6);
        txtEmail = findViewById(R.id.txtEmail2);
        txtPassword = findViewById(R.id.txtPassword2);
        switchGender = findViewById(R.id.switchGender2);
        txtAddress = findViewById(R.id.txtAddress2);
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber2);
        btnRegister = findViewById(R.id.btnRegister);
    }

    private void initialControllers() {

    }

    private void setupViews() {
        btnBack.setOnClickListener(
                t -> finish()
        );

        switchGender.setOnCheckedChangeListener(
                (buttonView, isChecked) -> switchGender.setText(isChecked ? "Nam" : "Nữ")
        );

//        TODO
    }

//
}