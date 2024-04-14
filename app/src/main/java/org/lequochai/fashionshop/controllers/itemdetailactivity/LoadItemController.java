package org.lequochai.fashionshop.controllers.itemdetailactivity;

import org.lequochai.fashionshop.ItemDetailActivity;
import org.lequochai.fashionshop.entities.Item;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;
import org.lequochai.fashionshop.utils.DialogHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadItemController extends ItemDetailActivityController<String> {
//    Constructors:
    public LoadItemController(ItemDetailActivity view) {
        super(view);
    }

//    Methods:
    @Override
    public void execute(String id) {
        GlobalService.getInstance(view)
                .getItemService()
                .get(id)
                .enqueue(
                        new Callback<RestfulResponse<Item>>() {
                            @Override
                            public void onResponse(Call<RestfulResponse<Item>> call, Response<RestfulResponse<Item>> response) {
                                if (response.isSuccessful()) {
                                    RestfulResponse<Item> body = response.body();

                                    if (body.isSuccess()) {
                                        view.loadItem(body.getResult());
                                    }
                                    else {
                                        DialogHelper.showAlertDialog(view, body.getCode(),
                                                body.getMessage());
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<RestfulResponse<Item>> call, Throwable throwable) {
                                DialogHelper.showErrorDialog(view, throwable.toString());
                            }
                        }
                );
    }
}
