package org.lequochai.fashionshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
//    Fields:
    private ImageView btnBack;
    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnLogin;
    private TextView lblRegister;

//    Constructors:
    public LoginActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        Get views
        getViews();

//        Setup views
        setupViews();
    }

//    Setup methods:
    private void getViews() {
        btnBack = findViewById(R.id.btnBack);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        lblRegister = findViewById(R.id.lblRegister);
    }

    private void setupViews() {
        btnBack.setOnClickListener(
                t -> finish()
        );

//        TODO
    }
}