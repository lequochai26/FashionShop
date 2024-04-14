package org.lequochai.fashionshop.controllers.itemdetailactivity;

import android.widget.Toast;

import org.lequochai.fashionshop.ItemDetailActivity;
import org.lequochai.fashionshop.entities.Item;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;
import org.lequochai.fashionshop.services.bodies.CartPostBody;
import org.lequochai.fashionshop.utils.DialogHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddToCartController extends ItemDetailActivityController<Void> {
//    Constructors:
    public AddToCartController(ItemDetailActivity view) {
        super(view);
    }

//    Methods:
    @Override
    public void execute(Void param) {
//        Get item from view
        Item item = view.getItem();

//        item null case
        if (item == null) {
            return;
        }

//        Make request body
        CartPostBody body = new CartPostBody();
        body.setId(item.getId());
        body.setAmount(1);
        body.setMetadata(view.getSelection());

//        Make request
        GlobalService.getInstance(view)
                .getCartService()
                .add(body)
                .enqueue(
                        new Callback<RestfulResponse<Void>>() {
                            @Override
                            public void onResponse(Call<RestfulResponse<Void>> call, Response<RestfulResponse<Void>> response) {
                                if (response.isSuccessful()) {
                                    RestfulResponse<Void> responseBody = response.body();

                                    if (responseBody.isSuccess()) {
                                        Toast.makeText(view, "Sản phẩm đã được thêm vào giỏ hàng " +
                                                "thành công!", Toast.LENGTH_LONG).show();
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
