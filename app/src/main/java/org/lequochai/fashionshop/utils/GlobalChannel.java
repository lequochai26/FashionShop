package org.lequochai.fashionshop.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalChannel {
//    Static fields:
    private static GlobalChannel instance;

//    Static methods
    public static GlobalChannel getInstance() {
        if (instance == null) {
            instance = new GlobalChannel();
        }

        return instance;
    }

    //    Fields:
    private List<Receiver> receivers;
    private Map<String, Receiver> receiversMap;

//    Constructors:
    private GlobalChannel() {
        receivers = new ArrayList<>();
        receiversMap = new HashMap<>();
    }

//    Methods:
    public void subscribe(Receiver receiver) {
        receiversMap.put(receiver.getReceiverName(), receiver);
    }

    public void send(Object sender, String receiverName, Object message) {
        for (Receiver receiver : receiversMap.values()) {
            if (receiver.getReceiverName().equals(receiverName)) {
                receiver.receive(sender, message);
            }
        }
    }

    public void send(Object sender, Class receiverClass, Object message) {
        for (Receiver receiver : receiversMap.values()) {
            if (receiver.getClass() == receiverClass) {
                receiver.receive(sender, message);
            }
        }
    }

    public void unsubscribe(Receiver receiver) {
        receiversMap.remove(receiver.getReceiverName());
    }
}
