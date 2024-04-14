package org.lequochai.fashionshop.controllers.orderedordersactivity.orderslistview;

import android.content.Context;

import org.lequochai.fashionshop.OrderedOrdersActivity;
import org.lequochai.fashionshop.controllers.ContextController;
import org.lequochai.fashionshop.entities.Order;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;
import org.lequochai.fashionshop.utils.DialogHelper;
import org.lequochai.fashionshop.utils.GlobalChannel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancelController extends ContextController<Order> {
//    Constructors:
    public CancelController(Context context) {
        super(context);
    }

//    Methods:
    @Override
    public void execute(Order order) {
        GlobalService.getInstance(context)
                .getOrderService()
                .cancel(order.getId())
                .enqueue(
                        new Callback<RestfulResponse<Void>>() {
                            @Override
                            public void onResponse(Call<RestfulResponse<Void>> call, Response<RestfulResponse<Void>> response) {
                                if (response.isSuccessful()) {
                                    RestfulResponse<Void> body = response.body();

                                    if (body.isSuccess()) {
                                        GlobalChannel.getInstance()
                                                .send(
                                                        CancelController.this,
                                                        OrderedOrdersActivity.class,
                                                        OrderedOrdersActivity.MESSAGE_RELOAD_ORDERS
                                                );
                                    }
                                    else {
                                        DialogHelper.showAlertDialog(context, body.getCode(),
                                                body.getMessage());
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<RestfulResponse<Void>> call, Throwable throwable) {
                                DialogHelper.showErrorDialog(context, throwable.toString());
                            }
                        }
                );
    }
}
