package org.lequochai.fashionshop.controllers.registeractivity;

import org.lequochai.fashionshop.RegisterActivity;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;
import org.lequochai.fashionshop.services.bodies.UserBody;
import org.lequochai.fashionshop.utils.DialogHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterController extends RegisterActivityController<Void> {
//    Constructors:
    public RegisterController(RegisterActivity view) {
        super(view);
    }

//    Methods:
    @Override
    public void execute(Void param) {
//        Validation
//        email
        String email = view.getTxtEmail().getText().toString();
        if (email.equals("")) {
            DialogHelper.showAlertDialog(view, "Email không hợp lệ", "Email không thể để trống!");
            return;
        }

//        password
        String password = view.getTxtPassword().getText().toString();
        if (password.equals("")) {
            DialogHelper.showAlertDialog(view, "Mật khẩu không hợp lệ", "Mật khẩu không thể để " +
                    "trống!");
            return;
        }

//        fullName
        String fullName = view.getTxtFullName().getText().toString();
        if (fullName.equals("")) {
            DialogHelper.showAlertDialog(view, "Họ và tên không hợp lệ", "Họ và tên không thể để " +
                    "trống!");
            return;
        }

//        gender
        boolean gender = view.getSwitchGender().isChecked();

//        phoneNumber
        String phoneNumber = view.getTxtPhoneNumber().getText().toString();
        if (phoneNumber.equals("")) {
            DialogHelper.showAlertDialog(view, "Số điện thoại không hợp lệ", "Số điện thoại không" +
                    " thể để trống!");
            return;
        }

//        Address
        String address = view.getTxtAddress().getText().toString();

//        Make request body
        UserBody body = new UserBody();
        body.setEmail(email);
        body.setPassword(password);
        body.setFullName(fullName);
        body.setGender("" + gender);
        body.setPhoneNumber(phoneNumber);
        body.setAddress(address);

//        Make request
        GlobalService.getInstance(view)
                .getUserService()
                .register(body)
                .enqueue(
                        new Callback<RestfulResponse<Void>>() {
                            @Override
                            public void onResponse(Call<RestfulResponse<Void>> call, Response<RestfulResponse<Void>> response) {
                                if (response.isSuccessful()) {
                                    RestfulResponse<Void> responseBody = response.body();

                                    if (responseBody.isSuccess()) {
                                        DialogHelper.showAlertDialog(
                                                view,
                                                "Đăng ký thành công",
                                                "Chúc mừng, bạn đã đăng ký thành công!",
                                                (dialog, which) -> view.finish()
                                        );
                                    }
                                    else {
                                        DialogHelper.showAlertDialog(view, responseBody.getCode()
                                                , responseBody.getMessage());
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<RestfulResponse<Void>> call, Throwable throwable) {
                                DialogHelper.showErrorDialog(view, throwable.toString());
                            }
                        }
                );
    }
}
