package org.lequochai.fashionshop.controllers;

public abstract class ViewController<V, P> implements Controller<P> {
//    Fields:
    protected V view;

//    Constructors:
    public ViewController(V view) {
        this.view = view;
    }
}
