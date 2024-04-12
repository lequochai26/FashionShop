package org.lequochai.fashionshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.lequochai.fashionshop.controllers.itemslistviewitem.AddCartItemController;
import org.lequochai.fashionshop.entities.Item;
import org.lequochai.fashionshop.services.GlobalService;

import java.util.List;

public class ItemsListViewItemAdapter extends BaseAdapter {
//    Fields:
    private Context context;
    private List<Item> items;
    private LayoutInflater inflater;

//    Constructors:
    public ItemsListViewItemAdapter(Context context, List<Item> items) {
        this.items = items;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_items_list_view_item, null);
        }

        Item item = items.get(position);

//        imgAvatar
        ImageView imgAvatar = convertView.findViewById(R.id.imgItemAvatar);
        Picasso.get()
                .load(
                        GlobalService.HOST_HTTP + item.getAvatar()
                                .substring(1)
                )
                .into(imgAvatar);

//        txtName
        TextView txtName = convertView.findViewById(R.id.txtName);
        txtName.setText(item.getName());

//        txtPrice
        TextView txtPrice = convertView.findViewById(R.id.txtPrice);
        txtPrice.setText("" + item.getPrice());

//        btnAddToCart
        Button btnAddToCart = convertView.findViewById(R.id.btnAddToCart);
        btnAddToCart.setOnClickListener(
                t -> {
                    new AddCartItemController(context)
                            .execute(item);
                }
        );

//        Return convertView
        return convertView;
    }
}