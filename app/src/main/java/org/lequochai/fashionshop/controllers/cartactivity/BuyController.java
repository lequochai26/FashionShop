package org.lequochai.fashionshop.controllers.cartactivity;

import org.lequochai.fashionshop.CartActivity;
import org.lequochai.fashionshop.entities.CartItem;
import org.lequochai.fashionshop.entities.User;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;
import org.lequochai.fashionshop.utils.DialogHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyController extends CartActivityController<List<CartItem>> {
//    Fields:
    private User user;

//    Constructors:
    public BuyController(CartActivity view) {
        super(view);
    }

//    Methods:
    @Override
    public void execute(List<CartItem> param) {
        GlobalService.getInstance(view)
                .getOrderService()
                .create()
                .enqueue(
                        new Callback<RestfulResponse<Void>>() {
                            @Override
                            public void onResponse(Call<RestfulResponse<Void>> call, Response<RestfulResponse<Void>> response) {
                                if (response.isSuccessful()) {
                                    RestfulResponse<Void> body = response.body();

                                    if (body.isSuccess()) {
                                        view.init();
//                                        DialogHelper.showAlertDialog(view, "Đặt hàng thành cong",
//                                                "Đơn hàng của bạn đã được đặt thành công, kiểm " +
//                                                        "tra lịch sử mua hàng đế biết the thông " +
//                                                        "tin nhé!");
                                        view.showOrderedOrdersActivity();
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
