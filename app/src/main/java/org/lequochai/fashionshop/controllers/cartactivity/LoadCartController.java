package org.lequochai.fashionshop.controllers.cartactivity;

import org.lequochai.fashionshop.CartActivity;
import org.lequochai.fashionshop.entities.CartItem;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadCartController extends CartActivityController<Void> {
//    Constructors:
    public LoadCartController(CartActivity view) {
        super(view);
    }

//    Methods:
    @Override
    public void execute(Void param) {
        GlobalService.getInstance(view)
                .getCartService()
                .get()
                .enqueue(
                        new Callback<RestfulResponse<List<CartItem>>>() {
                            @Override
                            public void onResponse(Call<RestfulResponse<List<CartItem>>> call, Response<RestfulResponse<List<CartItem>>> response) {
                                if (response.isSuccessful()) {
                                    RestfulResponse<List<CartItem>> body = response.body();

                                    if (body.isSuccess()) {
                                        view.showCart(
                                                body.getResult()
                                        );
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<RestfulResponse<List<CartItem>>> call, Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        }
                );
    }
}
