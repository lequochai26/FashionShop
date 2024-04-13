package org.lequochai.fashionshop.controllers.loginactivity;

import android.widget.EditText;

import org.lequochai.fashionshop.LoginActivity;
import org.lequochai.fashionshop.MainActivity;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;
import org.lequochai.fashionshop.services.bodies.UserBody;
import org.lequochai.fashionshop.utils.DialogHelper;
import org.lequochai.fashionshop.utils.GlobalChannel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginController extends LoginActivityController<Void> {
//    Constructors:
    public LoginController(LoginActivity view) {
        super(view);
    }

//    Methods:
    @Override
    public void execute(Void param) {
//        Get views
        EditText txtEmail = view.getTxtEmail();
        EditText txtPassword = view.getTxtPassword();

//        Get email and password
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

//        Validate email and password
        if (email.equals("")) {
            DialogHelper.showAlertDialog(
                    view, "Chưa nhập email", "Vui lòng nhập email!"
            );
            return;
        }

        if (password.equals("")) {
            DialogHelper.showAlertDialog(
                    view, "Chưa nhập mật khẩu", "Vui lòng nhập mật khẩu!"
            );
            return;
        }

//        Create request body
        UserBody body = new UserBody();
        body.setEmail(email);
        body.setPassword(password);

//        Make request
        GlobalService.getInstance(view)
                .getUserService()
                .login(body)
                .enqueue(
                        new Callback<RestfulResponse<Void>>() {
                            @Override
                            public void onResponse(Call<RestfulResponse<Void>> call, Response<RestfulResponse<Void>> response) {
                                if (response.isSuccessful()) {
                                    RestfulResponse<Void> body = response.body();

                                    if (body.isSuccess()) {
                                        view.finish();
                                        GlobalChannel.getInstance()
                                                .send(LoginController.this, MainActivity.class,
                                                        MainActivity.MESSAGE_ONLOGIN);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<RestfulResponse<Void>> call, Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        }
                );
    }
}
