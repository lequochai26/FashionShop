package org.lequochai.fashionshop.controllers;

import android.content.Context;

public abstract class ContextController<P> implements Controller<P> {
//    Fiedls:
    protected Context context;

//    Constructors:
    public ContextController(Context context) {
        this.context = context;
    }
}
