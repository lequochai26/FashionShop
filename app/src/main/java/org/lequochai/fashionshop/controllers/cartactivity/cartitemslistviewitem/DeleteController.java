package org.lequochai.fashionshop.controllers.cartactivity.cartitemslistviewitem;

import android.content.Context;

import org.lequochai.fashionshop.CartActivity;
import org.lequochai.fashionshop.controllers.ContextController;
import org.lequochai.fashionshop.entities.CartItem;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;
import org.lequochai.fashionshop.services.bodies.CartDeleteBody;
import org.lequochai.fashionshop.utils.GlobalChannel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteController extends ContextController<CartItem> {
//    Constructors:
    public DeleteController(Context context) {
        super(context);
    }

//    Methods:
    @Override
    public void execute(CartItem param) {
//        Create body
        CartDeleteBody body = new CartDeleteBody();

        CartDeleteBody.Item item = new CartDeleteBody.Item();
        item.setId(param.getItem().getId());
        item.setMetadata(param.getMetadata());

        List<CartDeleteBody.Item> items = new ArrayList<>();
        items.add(item);

        body.setItems(items);

//        Make request
        GlobalService.getInstance(context)
                .getCartService()
                .remove(body)
                .enqueue(
                        new Callback<RestfulResponse<Void>>() {
                            @Override
                            public void onResponse(Call<RestfulResponse<Void>> call, Response<RestfulResponse<Void>> response) {
                                if (response.isSuccessful()) {
                                    RestfulResponse<Void> body = response.body();

                                    if (body.isSuccess()) {
                                        GlobalChannel.getInstance()
                                                .send(DeleteController.this, CartActivity.class,
                                                        "onDelete");
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<RestfulResponse<Void>> call, Throwable throwable) {

                            }
                        }
                );
    }
}
