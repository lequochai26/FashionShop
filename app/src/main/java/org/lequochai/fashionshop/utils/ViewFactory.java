package org.lequochai.fashionshop.utils;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.lequochai.fashionshop.R;
import org.lequochai.fashionshop.controllers.itemslistviewitem.AddCartItemController;
import org.lequochai.fashionshop.entities.CartItem;
import org.lequochai.fashionshop.entities.Item;
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

//        lblPrice
        TextView lblPrice = view.findViewById(R.id.lblPrice);
        lblPrice.setText("Giá: " + cartItem.getItem().getPrice());

//        lblAmount
        TextView lblAmount = view.findViewById(R.id.lblAmount);
        lblAmount.setText(cartItem.getAmount());

//        btnRemove
        ImageView btnRemove = view.findViewById(R.id.btnRemove);
//        TODO

//        btnAdd
        ImageView btnAdd = view.findViewById(R.id.btnAdd);
//        TODO

//        btnDelete
        ImageView btnDelete = view.findViewById(R.id.btnDelete);
//        TODO
    }

//    Constructors:
    private ViewFactory() {

    }
}
