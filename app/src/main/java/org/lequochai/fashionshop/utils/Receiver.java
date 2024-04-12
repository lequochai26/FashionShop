package org.lequochai.fashionshop.utils;

public interface Receiver {
    void receive(Object from, Object message);
    String getReceiverName();
}
