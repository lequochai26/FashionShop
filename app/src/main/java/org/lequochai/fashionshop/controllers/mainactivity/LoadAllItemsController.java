package org.lequochai.fashionshop.controllers.mainactivity;

import org.lequochai.fashionshop.MainActivity;
import org.lequochai.fashionshop.entities.Item;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadAllItemsController extends MainActivityController<Void> {
//    Constructors:
    public LoadAllItemsController(MainActivity view) {
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
                                        display(body.getResult());
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

    private void display(List<Item> items) {
//        Filter the out of amount items
        for (int i = 0;i<items.size();i++) {
            Item item = items.get(i);

            if (item.getAmount() < 1) {
                if (item.getMetadata() == null) {
                    items.remove(i);
                    i--;
                    continue;
                }

                Item.Metadata metadata = item.getMetadata();
                List<Map<String, Object>> mappings = metadata.getMappings();

                boolean valid = false;

                for (Map<String, Object> mapping : mappings) {
                    if ((int)mapping.get("amount") > 0) {
                        valid = true;
                        break;
                    }
                }

                if (!valid) {
                    items.remove(i);
                    i--;
                }
            }
        }

//        Load items for view
        view.loadItems(items);
    }
}
