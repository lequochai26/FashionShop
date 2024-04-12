package org.lequochai.fashionshop.controllers.mainactivity;

import org.lequochai.fashionshop.MainActivity;
import org.lequochai.fashionshop.entities.Item;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityLoadAllItemsController extends MainActivityController<Void> {
//    Constructors:
    public MainActivityLoadAllItemsController(MainActivity view) {
        super(view);
    }

//    Methods:

    @Override
    public void execute(Void param) {
        GlobalService
                .getInstance(view)
                .getItemService()
                .getAll()
                .enqueue(
                        new Callback<RestfulResponse<List<Item>>>() {
                            @Override
                            public void onResponse(Call<RestfulResponse<List<Item>>> call, Response<RestfulResponse<List<Item>>> response) {
                                if (response.isSuccessful()) {
                                    RestfulResponse<List<Item>> body = response.body();

                                    if (body.isSuccess()) {
                                        view.loadItems(
                                                body.getResult()
                                        );
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<RestfulResponse<List<Item>>> call, Throwable throwable) {

                            }
                        }
                );
    }
}
