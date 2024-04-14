package org.lequochai.fashionshop.controllers.itemdetailactivity;

import org.lequochai.fashionshop.ItemDetailActivity;
import org.lequochai.fashionshop.controllers.ViewController;

public abstract class ItemDetailActivityController<P> extends ViewController<ItemDetailActivity,
        P> {
//    Constructors:
    public ItemDetailActivityController(ItemDetailActivity view) {
        super(view);
    }
}
