package org.lequochai.fashionshop.utils;

import java.util.ArrayList;
import java.util.List;

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

//    Constructors:
    private GlobalChannel() {
        receivers = new ArrayList<>();
    }

//    Methods:
    public void subscribe(Receiver receiver) {
        if (receivers.contains(receiver)) {
            return;
        }

        receivers.add(receiver);
    }

    public void send(Object sender, String receiverName, Object message) {
        for (Receiver receiver : receivers) {
            if (receiver.getReceiverName().equals(receiverName)) {
                receiver.receive(sender, message);
            }
        }
    }

    public void send(Object sender, Class receiverClass, Object message) {
        for (Receiver receiver : receivers) {
            if (receiver.getClass() == receiverClass) {
                receiver.receive(sender, message);
            }
        }
    }
}
