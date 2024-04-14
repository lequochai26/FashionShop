package org.lequochai.fashionshop.controllers.oderdetailactivity;

import org.lequochai.fashionshop.OrderDetailActivity;
import org.lequochai.fashionshop.entities.Order;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;
import org.lequochai.fashionshop.utils.DialogHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadOrderController extends OrderDetailActivityController<String> {
//    Constructors:
    public LoadOrderController(OrderDetailActivity view) {
        super(view);
    }

//    Methods:
    @Override
    public void execute(String id) {
        GlobalService.getInstance(view)
                .getOrderService()
                .get(id)
                .enqueue(
                        new Callback<RestfulResponse<Order>>() {
                            @Override
                            public void onResponse(Call<RestfulResponse<Order>> call, Response<RestfulResponse<Order>> response) {
                                if (response.isSuccessful()) {
                                    RestfulResponse<Order> body = response.body();

                                    if (body.isSuccess()) {
                                        view.loadOrder(body.getResult());
                                    }
                                    else {
                                        DialogHelper.showAlertDialog(view, body.getCode(),
                                                body.getMessage());
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<RestfulResponse<Order>> call, Throwable throwable) {
                                DialogHelper.showErrorDialog(view, throwable.toString());
                            }
                        }
                );
    }
}
