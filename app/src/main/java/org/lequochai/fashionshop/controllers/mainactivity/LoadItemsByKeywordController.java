package org.lequochai.fashionshop.controllers.mainactivity;

import org.lequochai.fashionshop.MainActivity;
import org.lequochai.fashionshop.entities.Item;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadItemsByKeywordController extends MainActivityController<String> {
//    Constructors:
    public LoadItemsByKeywordController(MainActivity view) {
        super(view);
    }

//    Methods:

    @Override
    public void execute(String param) {
        if (param.equals("")) {
            view.getLoadAllItemsController().execute(null);
            return;
        }

        GlobalService
                .getInstance(view)
                .getItemService()
                .getByKeyword(param)
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
                                throwable.printStackTrace();
                            }
                        }
                );
    }
}
