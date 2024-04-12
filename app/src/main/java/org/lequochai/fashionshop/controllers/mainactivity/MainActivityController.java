package org.lequochai.fashionshop.controllers.mainactivity;

import org.lequochai.fashionshop.MainActivity;
import org.lequochai.fashionshop.controllers.ViewController;

public abstract class MainActivityController<P> extends ViewController<MainActivity, P> {
//    Constructors:
    public MainActivityController(MainActivity view) {
        super(view);
    }
}
