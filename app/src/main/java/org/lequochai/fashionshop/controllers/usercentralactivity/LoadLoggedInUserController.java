package org.lequochai.fashionshop.controllers.usercentralactivity;

import org.lequochai.fashionshop.MainActivity;
import org.lequochai.fashionshop.UserCentralActivity;
import org.lequochai.fashionshop.entities.User;
import org.lequochai.fashionshop.utils.GlobalChannel;
import org.lequochai.fashionshop.utils.Receiver;

public class LoadLoggedInUserController extends UserCentralActivityController<Void> implements Receiver {
//    Static fields:
    public static final String RECEIVER_NAME = "userCentralAcitivityLoadLoggedInUserController";
    public static final String SEND_MAINACTIVITY_GETUSER =
            "userCentralAcitivityLoadLoggedInUserControllerGetUser";

//    Constructors:
    public LoadLoggedInUserController(UserCentralActivity view) {
        super(view);

        GlobalChannel.getInstance()
                .subscribe(this);
    }

//    Methods:
    @Override
    public void execute(Void param) {
        GlobalChannel.getInstance()
                .send(this, MainActivity.class, SEND_MAINACTIVITY_GETUSER);
    }

    @Override
    public void receive(Object from, Object message) {
        if (message instanceof User) {
            view.loadUser((User)message);
        }
    }

    @Override
    public String getReceiverName() {
        return RECEIVER_NAME;
    }
}
