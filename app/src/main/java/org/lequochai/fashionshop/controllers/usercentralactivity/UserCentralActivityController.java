package org.lequochai.fashionshop.controllers.usercentralactivity;

import org.lequochai.fashionshop.UserCentralActivity;
import org.lequochai.fashionshop.controllers.ViewController;

public abstract class UserCentralActivityController<P> extends ViewController<UserCentralActivity, P> {
//    Constructors:
    public UserCentralActivityController(UserCentralActivity view) {
        super(view);
    }
}
