package org.lequochai.fashionshop.controllers.usercentralactivity;

import org.lequochai.fashionshop.MainActivity;
import org.lequochai.fashionshop.UserCentralActivity;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;
import org.lequochai.fashionshop.utils.DialogHelper;
import org.lequochai.fashionshop.utils.GlobalChannel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogoutController extends UserCentralActivityController<Void> {
//    Constructors:
    public LogoutController(UserCentralActivity view) {
        super(view);
    }

//    Methods:
    @Override
    public void execute(Void param) {
        GlobalService.getInstance(view)
                .getUserService()
                .logout()
                .enqueue(
                        new Callback<RestfulResponse<Void>>() {
                            @Override
                            public void onResponse(Call<RestfulResponse<Void>> call, Response<RestfulResponse<Void>> response) {
                                if (response.isSuccessful()) {
                                    RestfulResponse<Void> body = response.body();

                                    if (body.isSuccess()) {
                                        view.finish();
                                        GlobalChannel.getInstance()
                                                .send(
                                                        LogoutController.this, MainActivity.class
                                                        , MainActivity.MESSAGE_ONLOGOUT
                                                );
                                    }
                                    else {
                                        DialogHelper.showAlertDialog(
                                                view, body.getCode(), body.getMessage()
                                        );
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
