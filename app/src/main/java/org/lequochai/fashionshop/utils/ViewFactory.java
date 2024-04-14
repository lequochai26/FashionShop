package org.lequochai.fashionshop.utils;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.lequochai.fashionshop.OrderedOrdersActivity;
import org.lequochai.fashionshop.R;
import org.lequochai.fashionshop.controllers.Controller;
import org.lequochai.fashionshop.controllers.cartactivity.cartitemslistviewitem.AddController;
import org.lequochai.fashionshop.controllers.cartactivity.cartitemslistviewitem.DeleteController;
import org.lequochai.fashionshop.controllers.cartactivity.cartitemslistviewitem.RemoveController;
import org.lequochai.fashionshop.controllers.mainactivity.itemslistviewitem.AddCartItemController;
import org.lequochai.fashionshop.controllers.orderedordersactivity.orderslistview.CancelController;
import org.lequochai.fashionshop.entities.CartItem;
import org.lequochai.fashionshop.entities.Item;
import org.lequochai.fashionshop.entities.Order;
import org.lequochai.fashionshop.services.GlobalService;

public class ViewFactory {
//    Static methods:
    public static void buildItemsListItemView(Context context, View view, Item item) {
//        imgAvatar
        ImageView imgAvatar = view.findViewById(R.id.imgItemAvatar);
        Picasso.get()
                .load(
                        GlobalService.HOST_HTTP + item.getAvatar()
                                .substring(1)
                )
                .into(imgAvatar);

//        txtName
        TextView txtName = view.findViewById(R.id.txtName);
        txtName.setText(item.getName());

//        txtPrice
        TextView txtPrice = view.findViewById(R.id.txtPrice);
        txtPrice.setText("Giá: " + item.getPrice());

//        btnAddToCart
        Button btnAddToCart = view.findViewById(R.id.btnAddToCart);
        btnAddToCart.setOnClickListener(
                t -> {
                    new AddCartItemController(context)
                            .execute(item);
                }
        );
    }

    public static void buildCartItemsListItemView(Context context, View view, CartItem cartItem) {
//        imgAvatar
        ImageView imgAvatar = view.findViewById(R.id.imgAvatar2);
        Picasso.get()
                .load(GlobalService.HOST_HTTP + cartItem.getItem().getAvatar().substring(1))
                .into(imgAvatar);

//        lblName
        TextView lblName = view.findViewById(R.id.lblName);
        lblName.setText(cartItem.getItem().getName());

//        lblMetadata
        TextView lblMetadata = view.findViewById(R.id.lblMetadata);
        if (cartItem.getMetadata() != null) {
            String metadata = "";
            for (String key : cartItem.getMetadata().keySet()) {
                metadata = metadata.concat(metadata.equals("") ? "" : ", ")
                        .concat(key)
                        .concat(": ")
                        .concat(cartItem.getMetadata().get(key));
            }
            metadata = "Phân loại: " + metadata;
            lblMetadata.setText("Phân loại: " + metadata);
        }

//        lblPrice
        TextView lblPrice = view.findViewById(R.id.lblPrice);
        lblPrice.setText("Giá: " + cartItem.getItem().getPrice());

//        lblAmount
        TextView lblAmount = view.findViewById(R.id.lblAmount);
        lblAmount.setText("" + cartItem.getAmount());

//        btnRemove
        ImageView btnRemove = view.findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(
                t -> new RemoveController(context).execute(cartItem)
        );

//        btnAdd
        ImageView btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(
                t -> new AddController(context).execute(cartItem)
        );

//        btnDelete
        ImageView btnDelete = view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(
                t -> new DeleteController(context).execute(cartItem)
        );
    }

    public static void buildOrdersListViewItem(Context context, View view, Order order) {
//        Get order's status
        Order.Status status = Order.Status.valueOf(order.getStatus());

//        Controllers initialize
        Controller<Order> cancelController = new CancelController(context);

//        lblOrderId
        TextView lblOrderId = view.findViewById(R.id.lblOrderId);
        lblOrderId.setText(
                order.getId() + " ( "  + Order.getStatusTitle(status) + " )"
        );

//        lblDate
        TextView lblDate = view.findViewById(R.id.lblDate);
        lblDate.setText(
                order.getDate().toString()
        );

//        btnCancel
        Button btnCancel = view.findViewById(R.id.btnCancel);
        if (status == Order.Status.APPROVEMENT_AWAITING) {
            btnCancel.setEnabled(true);
        }
        else {
            btnCancel.setEnabled(false);
        }
        btnCancel.setOnClickListener(
                t -> cancelController.execute(order)
        );

//        btnDetail
        Button btnDetail = view.findViewById(R.id.btnDetail);
        btnDetail.setOnClickListener(
                t -> GlobalChannel.getInstance()
                        .send(
                                view, OrderedOrdersActivity.class, order
                        )
        );
    }

    public static void buildOrderItemsListViewItem(Context context, View view,
                                                   Order.Item orderItem) {
//        imgAvatar
        ImageView imgAvatar = view.findViewById(R.id.imgAvatar4);
        Picasso.get()
                .load(GlobalService.HOST_HTTP + orderItem.getAvatar().substring(1))
                .into(imgAvatar);

//        lblName
        TextView lblName = view.findViewById(R.id.lblName2);
        lblName.setText(orderItem.getName());

//        lblAmount
        TextView lblAmount = view.findViewById(R.id.lblAmount2);
        lblAmount.setText("Số lượng: " + orderItem.getAmount());

//        lblPrice
        TextView lblPrice = view.findViewById(R.id.lblPrice2);
        lblPrice.setText("Giá: " + orderItem.getPrice());

//        lblMetadata
        TextView lblMetadata = view.findViewById(R.id.lblMetadata2);
        if (orderItem.getMetadata() != null) {
            String metadata = "";
            for (String key : orderItem.getMetadata().keySet()) {
                metadata = metadata.concat(metadata.equals("") ? "" : ", ")
                        .concat(key)
                        .concat(": ")
                        .concat(orderItem.getMetadata().get(key));
            }
            metadata = "Phân loại: " + metadata;
            lblMetadata.setText(metadata);
        }
        else {
            lblMetadata.setText("");
        }
    }

//    Constructors:
    private ViewFactory() {

    }
}
