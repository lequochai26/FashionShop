package org.lequochai.fashionshop.controllers.loginactivity;

import org.lequochai.fashionshop.LoginActivity;
import org.lequochai.fashionshop.controllers.ViewController;

public abstract class LoginActivityController<P> extends ViewController<LoginActivity, P> {
//    Constructors:
    public LoginActivityController(LoginActivity view) {
        super(view);
    }
}
