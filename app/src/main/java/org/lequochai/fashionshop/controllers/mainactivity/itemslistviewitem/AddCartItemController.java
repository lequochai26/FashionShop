package org.lequochai.fashionshop.controllers.mainactivity.itemslistviewitem;

import android.content.Context;
import android.widget.Toast;

import org.lequochai.fashionshop.controllers.ContextController;
import org.lequochai.fashionshop.entities.Item;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;
import org.lequochai.fashionshop.services.bodies.CartPostBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCartItemController extends ContextController<Item> {
//    Constructors:
    public AddCartItemController(Context context) {
        super(context);
    }

//    Methods:
    @Override
    public void execute(Item param) {
//        No metadata case
        if (param.getMetadata() == null) {
//            Create body
            CartPostBody body = new CartPostBody();
            body.setId(param.getId());

//            Call API
            GlobalService
                    .getInstance(context)
                    .getCartService()
                    .add(body)
                    .enqueue(
                            new Callback<RestfulResponse<Void>>() {
                                @Override
                                public void onResponse(Call<RestfulResponse<Void>> call, Response<RestfulResponse<Void>> response) {
                                    if (response.isSuccessful()) {
                                        RestfulResponse<Void> body = response.body();

                                        if (body.isSuccess()) {
                                            Toast.makeText(context, "Thêm sản phẩm vào giỏ hàng " +
                                                            "thành công!",
                                                    Toast.LENGTH_SHORT).show();
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
//        Has metadata case
        else {
//            TODO
        }
    }
}
