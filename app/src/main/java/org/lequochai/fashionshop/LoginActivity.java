package org.lequochai.fashionshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.lequochai.fashionshop.controllers.Controller;
import org.lequochai.fashionshop.controllers.loginactivity.LoginController;

public class LoginActivity extends AppCompatActivity {
//    Fields:
    private ImageView btnBack;
    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnLogin;
    private TextView lblRegister;

    private Controller<Void> loginController;

//    Constructors:
    public LoginActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        Get views
        getViews();

//        Initial controllers
        initialControllers();

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

    private void initialControllers() {
        loginController = new LoginController(this);
    }

    private void setupViews() {
        btnBack.setOnClickListener(
                t -> finish()
        );

        btnLogin.setOnClickListener(
                t -> {
                    loginController.execute(null);
                }
        );

        lblRegister.setOnClickListener(
            t -> showRegisterActivity()
        );
    }

//    Show activity methods:
    private void showRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

//    Getters / setters:
    public EditText getTxtEmail() {
        return txtEmail;
    }

    public EditText getTxtPassword() {
        return txtPassword;
    }
}