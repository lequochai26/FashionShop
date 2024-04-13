package org.lequochai.fashionshop.controllers.cartactivity;

import android.widget.Toast;

import org.lequochai.fashionshop.CartActivity;
import org.lequochai.fashionshop.MainActivity;
import org.lequochai.fashionshop.entities.CartItem;
import org.lequochai.fashionshop.entities.User;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.GlobalService;
import org.lequochai.fashionshop.services.bodies.CartPutBody;
import org.lequochai.fashionshop.services.bodies.OrderBody;
import org.lequochai.fashionshop.utils.Callback;
import org.lequochai.fashionshop.utils.DialogHelper;
import org.lequochai.fashionshop.utils.GlobalChannel;
import org.lequochai.fashionshop.utils.Receiver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class BuyController extends CartActivityController<List<CartItem>> implements Receiver {
//    Static fields:
    public static final String RECEIVER_NAME = "cartActivityBuyController";

//    Fields:
    private User user;
    private List<CartItem> cartItems;

//    Constructors:
    public BuyController(CartActivity view) {
        super(view);

        GlobalChannel.getInstance()
                .subscribe(this);
    }

//    Methods:
    @Override
    public void execute(List<CartItem> param) {
//        Validate param
        if (param == null) {
            DialogHelper.showAlertDialog(view, "Không thành công", "Không thể đặt hàng lúc này!");
            return;
        }

        if (param.size() < 1) {
            DialogHelper.showAlertDialog(view, "Không đủ số lượng sản phẩm!", "Phải có ít nhất 1 " +
                    "sản phẩm trong giỏ hàng trước khi đặt hàng!");
            return;
        }

//        Assign param into cartItems
        cartItems = param;

//        Request MainActivity's user through GlobalChannel
        GlobalChannel.getInstance()
                .send(this, MainActivity.class, MainActivity.REQUEST_GETUSER);
    }

    @Override
    public void receive(Object from, Object message) {
        if (message != null) {
            if (message instanceof User) {
                user = (User)message;
                createOrder(
                        v -> deleteCart(
                                p -> {
                                    view.init();
                                    Toast.makeText(view, "Đặt hàng thành công!", Toast.LENGTH_LONG);
                                },
                                t -> t.printStackTrace()
                        ),

                        t -> t.printStackTrace()
                );
            }
        }
        else {
            DialogHelper.showAlertDialog(
                    view, "Chưa đăng nhập", "Vui lòng đăng nhập hoặc đăng ký trước khi đặt hàng!"
            );
        }
    }

    @Override
    public String getReceiverName() {
        return RECEIVER_NAME;
    }

    //    Private methods:
    private void createOrder(Callback<Void> onSuccess,
                             Callback<Throwable> onError) {
//        Create body
        OrderBody body = new OrderBody();
        body.setOrderedBy(user.getEmail());

        List<OrderBody.Item> items = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderBody.Item item = new OrderBody.Item();

            item.setId(cartItem.getItem().getId());
            item.setAmount(cartItem.getAmount());
            item.setMetadata(cartItem.getMetadata());

            items.add(item);
        }

        body.setItems(items);

//        Make request
        GlobalService.getInstance(view)
                .getOrderService()
                .create(body)
                .enqueue(
                        new retrofit2.Callback<RestfulResponse<Void>>() {
                            @Override
                            public void onResponse(Call<RestfulResponse<Void>> call, Response<RestfulResponse<Void>> response) {
                                if (response.isSuccessful()) {
                                    RestfulResponse<Void> body = response.body();

                                    if (body.isSuccess()) {
                                        onSuccess.call(null);
                                    }
                                    else {
                                        System.out.println("Code: " + body.getCode());
                                        System.out.println("Body: " + body.getMessage());
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<RestfulResponse<Void>> call, Throwable throwable) {
                                onError.call(throwable);
                            }
                        }
                );
    }

    private void deleteCart(Callback<Void> onSuccess, Callback<Throwable> onError) {
//        Create body
        CartPutBody body = new CartPutBody();

        List<CartPutBody.Item> items = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            CartPutBody.Item item = new CartPutBody.Item();
            item.setId(cartItem.getItem().getId());
            item.setMetadata(cartItem.getMetadata());
            items.add(item);
        }

        body.setItems(items);

//        Make request
        GlobalService.getInstance(view)
                .getCartService()
                .remove(body)
                .enqueue(
                        new retrofit2.Callback<RestfulResponse<Void>>() {
                            @Override
                            public void onResponse(Call<RestfulResponse<Void>> call, Response<RestfulResponse<Void>> response) {
                                if (response.isSuccessful()) {
                                    RestfulResponse<Void> body = response.body();

                                    if (body.isSuccess()) {
                                        onSuccess.call(null);
                                    }
                                    else {
                                        System.out.println("Message: " + body.getMessage());
                                        System.out.println("Code: " + body.getCode());
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<RestfulResponse<Void>> call, Throwable throwable) {
                                onError.call(throwable);
                            }
                        }
                );
    }
}
