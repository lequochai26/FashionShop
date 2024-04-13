package org.lequochai.fashionshop.controllers.cartactivity;

import org.lequochai.fashionshop.CartActivity;
import org.lequochai.fashionshop.controllers.ViewController;

public abstract class CartActivityController<P> extends ViewController<CartActivity, P> {
//    Constructors:
    public CartActivityController(CartActivity view) {
        super(view);
    }
}
