package org.lequochai.fashionshop.utils;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.lequochai.fashionshop.R;
import org.lequochai.fashionshop.controllers.itemslistviewitem.AddCartItemController;
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

    public static View buildCartItemsListItemView() {
        return null;
    }

//    Constructors:
    private ViewFactory() {

    }
}
