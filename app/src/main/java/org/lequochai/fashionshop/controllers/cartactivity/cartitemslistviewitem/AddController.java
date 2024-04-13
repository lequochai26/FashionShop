package org.lequochai.fashionshop.controllers.cartactivity.cartitemslistviewitem;

import android.content.Context;

import org.lequochai.fashionshop.CartActivity;
import org.lequochai.fashionshop.controllers.ContextController;
import org.lequochai.fashionshop.entities.CartItem;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;
import org.lequochai.fashionshop.services.bodies.CartPostBody;
import org.lequochai.fashionshop.utils.GlobalChannel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddController extends ContextController<CartItem> {
//    Constructors:
    public AddController(Context context) {
        super(context);
    }

//    Methods:

    @Override
    public void execute(CartItem param) {
//        Create body
        CartPostBody body = new CartPostBody();
        body.setId(param.getItem().getId());
        body.setMetadata(param.getMetadata());

//        Request sending
        GlobalService.getInstance(context)
                .getCartService()
                .add(body)
                .enqueue(
                        new Callback<RestfulResponse<Void>>() {
                            @Override
                            public void onResponse(Call<RestfulResponse<Void>> call, Response<RestfulResponse<Void>> response) {
                                if (response.isSuccessful()) {
                                    RestfulResponse<Void> body = response.body();

                                    if (body.isSuccess()) {
                                        GlobalChannel.getInstance()
                                                .send(AddController.this, CartActivity.class,
                                                        "onAdd");
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
