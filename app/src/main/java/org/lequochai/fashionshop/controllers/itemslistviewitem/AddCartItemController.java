package org.lequochai.fashionshop.controllers.itemslistviewitem;

import android.content.Context;

import org.lequochai.fashionshop.controllers.ContextController;
import org.lequochai.fashionshop.entities.Item;
import org.lequochai.fashionshop.services.GlobalService;

public class AddCartItemController extends ContextController<Item> {
//    Constructors:
    public AddCartItemController(Context context) {
        super(context);
    }

//    Methods:
    @Override
    public void execute(Item param) {
//        No metadata case
        if (param.getMetadata() == null) {
//            TODO
        }
//        Has metadata case
        else {
//            TODO
        }
    }
}
