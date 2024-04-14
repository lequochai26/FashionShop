package org.lequochai.fashionshop.controllers.updatepersonalinfoactivity;

import org.lequochai.fashionshop.MainActivity;
import org.lequochai.fashionshop.UpdatePersonalInfoActivity;
import org.lequochai.fashionshop.UserCentralActivity;
import org.lequochai.fashionshop.entities.User;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;
import org.lequochai.fashionshop.services.bodies.UserBody;
import org.lequochai.fashionshop.utils.CallbackMessage;
import org.lequochai.fashionshop.utils.DialogHelper;
import org.lequochai.fashionshop.utils.GlobalChannel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUserPersonalInfoController extends UpdatePersonalInfoActivityController<Void> {
//    Constructors:
    public UpdateUserPersonalInfoController(UpdatePersonalInfoActivity view) {
        super(view);
    }

//    Methods:
    @Override
    public void execute(Void param) {
//        Create request's body
        UserBody body = new UserBody();

//        Get user from view
        User user = view.getUser();
        body.setEmail(user.getEmail());

//        Modified definition
        boolean modified = false;

//        Get neccessary informations
        String fullName = view.getTxtFullName().getText().toString();
        if (!fullName.equals("")) {
            if (!fullName.equals(user.getFullName())) {
                body.setFullName(fullName);
                modified = true;
            }
        }

        boolean gender = view.getSwitchGender().isChecked();
        if (gender != user.isGender()) {
            body.setGender("" + gender);
            modified = true;
        }

        String phoneNumber = view.getTxtPhoneNumber().getText().toString();
        if (!phoneNumber.equals("")) {
            if (!phoneNumber.equals(user.getPhoneNumber())) {
                body.setPhoneNumber(phoneNumber);
                modified = true;
            }
        }

        String address = view.getTxtAddress().getText().toString();
        if (!address.equals("")) {
            if (!address.equals(user.getAddress())) {
                body.setAddress(address);
                modified = true;
            }
        }

//        If not modified
        if (!modified) {
            DialogHelper.showAlertDialog(
                    view, "Không hợp lệ", "Vui lòng cập nhật ít nhất 1 thông tin!"
            );
            return;
        }

//        Make request
        GlobalService.getInstance(view)
                .getUserService()
                .update(body)
                .enqueue(
                        new Callback<RestfulResponse<Void>>() {
                            @Override
                            public void onResponse(Call<RestfulResponse<Void>> call, Response<RestfulResponse<Void>> response) {
                                if (response.isSuccessful()) {
                                    RestfulResponse<Void> responseBody = response.body();

                                    if (responseBody.isSuccess()) {
                                        GlobalChannel.getInstance()
                                                        .send(UpdateUserPersonalInfoController.this,
                                                                MainActivity.class,
                                                                MainActivity.MESSAGE_RELOAD_USER);

//                                        Send callback message to MainActivity
                                        GlobalChannel.getInstance()
                                                        .send(UpdateUserPersonalInfoController.this, MainActivity.class, new CallbackMessage<Void>() {
                                                            @Override
                                                            public void call(Void param) {
                                                                GlobalChannel.getInstance()
                                                                                .send(
                                                                                        UpdateUserPersonalInfoController.this,
                                                                                        UserCentralActivity.class,
                                                                                        UserCentralActivity.MESSAGE_RELOAD_USER
                                                                                );
                                                                view.finish();
                                                            }

                                                            @Override
                                                            public String getMessage() {
                                                                return MainActivity.AWAITING_USER_LOADED;
                                                            }
                                                        });

                                        view.finish();
                                    }
                                    else {
                                        DialogHelper.showAlertDialog(view, responseBody.getCode(),
                                                responseBody.getMessage());
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
