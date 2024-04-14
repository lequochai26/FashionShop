package org.lequochai.fashionshop.controllers.oderdetailactivity;

import android.content.DialogInterface;

import org.lequochai.fashionshop.OrderDetailActivity;
import org.lequochai.fashionshop.OrderedOrdersActivity;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;
import org.lequochai.fashionshop.utils.DialogHelper;
import org.lequochai.fashionshop.utils.GlobalChannel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancelController extends OrderDetailActivityController<String> {
//    Constructors:
    public CancelController(OrderDetailActivity view) {
        super(view);
    }

//    Methods:

    @Override
    public void execute(String id) {
        GlobalService.getInstance(view)
                .getOrderService()
                .cancel(id)
                .enqueue(
                        new Callback<RestfulResponse<Void>>() {
                            @Override
                            public void onResponse(Call<RestfulResponse<Void>> call, Response<RestfulResponse<Void>> response) {
                                if (response.isSuccessful()) {
                                    RestfulResponse<Void> body = response.body();

                                    if (body.isSuccess()) {
                                        DialogHelper.showAlertDialog(
                                                view, "Hủy thành công", "Hủy đơn hàng thành công!",
                                                (dialog, which) -> view.init()
                                        );

                                        if (view.isFromOrderedOrdersActivity()) {
                                            GlobalChannel.getInstance()
                                                    .send(CancelController.this,
                                                            OrderedOrdersActivity.class,
                                                            OrderedOrdersActivity.MESSAGE_RELOAD_ORDERS);
                                        }
                                    }
                                    else {
                                        DialogHelper.showAlertDialog(view, body.getCode(),
                                                body.getMessage());
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
