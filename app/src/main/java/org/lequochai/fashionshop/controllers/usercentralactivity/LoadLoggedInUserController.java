package org.lequochai.fashionshop.controllers.usercentralactivity;

import org.lequochai.fashionshop.UserCentralActivity;
import org.lequochai.fashionshop.entities.User;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadLoggedInUserController extends UserCentralActivityController<Void> {
//    Constructors:
    public LoadLoggedInUserController(UserCentralActivity view) {
        super(view);
    }

//    Methods:
    @Override
    public void execute(Void param) {
//        Make request
        GlobalService.getInstance(view)
                .getUserService()
                .getLoggedIn()
                .enqueue(
                        new Callback<RestfulResponse<User>>() {
                            @Override
                            public void onResponse(Call<RestfulResponse<User>> call, Response<RestfulResponse<User>> response) {
                                if (response.isSuccessful()) {
                                    RestfulResponse<User> body = response.body();

                                    if (body.isSuccess()) {
                                        view.loadUser(body.getResult());
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<RestfulResponse<User>> call, Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        }
                );
    }
}
