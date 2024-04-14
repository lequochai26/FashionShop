package org.lequochai.fashionshop.controllers.orderedordersactivity;

import org.lequochai.fashionshop.OrderedOrdersActivity;
import org.lequochai.fashionshop.entities.Order;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;
import org.lequochai.fashionshop.utils.DialogHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadOrderedOrdersController extends OrderedOrdersActivityController<Void> {
//    Constructors:
    public LoadOrderedOrdersController(OrderedOrdersActivity view) {
        super(view);
    }

//    Methods:
    @Override
    public void execute(Void param) {
        GlobalService.getInstance(view)
                .getOrderService()
                .getOrdered()
                .enqueue(
                        new Callback<RestfulResponse<List<Order>>>() {
                            @Override
                            public void onResponse(Call<RestfulResponse<List<Order>>> call, Response<RestfulResponse<List<Order>>> response) {
                                if (response.isSuccessful()) {
                                    RestfulResponse<List<Order>> body = response.body();

                                    if (body.isSuccess()) {
                                        view.loadOrders(body.getResult());
                                    }
                                    else {
                                        DialogHelper.showAlertDialog(view, body.getCode(),
                                                body.getMessage());
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<RestfulResponse<List<Order>>> call, Throwable throwable) {
                                DialogHelper.showErrorDialog(view, throwable.toString());
                            }
                        }
                );
    }
}
