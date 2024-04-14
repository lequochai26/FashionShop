package org.lequochai.fashionshop.controllers.oderdetailactivity;

import org.lequochai.fashionshop.OrderDetailActivity;
import org.lequochai.fashionshop.controllers.ViewController;

public abstract class OrderDetailActivityController<P> extends ViewController<OrderDetailActivity
        , P> {
//    Constructors:
    public OrderDetailActivityController(OrderDetailActivity view) {
        super(view);
    }
}
