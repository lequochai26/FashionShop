package org.lequochai.fashionshop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.lequochai.fashionshop.R;
import org.lequochai.fashionshop.entities.CartItem;
import org.lequochai.fashionshop.services.GlobalService;

import java.util.List;

public class CartItemsListViewItemAdapter extends BaseAdapter {
//    Fields:
private Context context;
    private List<CartItem> cartItems;
    private LayoutInflater inflater;

//    Constructors:
    public CartItemsListViewItemAdapter(Context context, List<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
        inflater = LayoutInflater.from(context);
    }

//    Methods:
    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cartitems_list_view_item, null);
        }

//        Get cart item
        CartItem cartItem = cartItems.get(position);

//        imgAvatar
        ImageView imgAvatar = convertView.findViewById(R.id.imgAvatar2);
        Picasso.get()
                .load(GlobalService.HOST_HTTP + cartItem.getItem().getAvatar().substring(1))
                .into(imgAvatar);

//        lblName
        TextView lblName = convertView.findViewById(R.id.lblName);
        lblName.setText(cartItem.getItem().getName());

//        lblPrice
        TextView lblPrice = convertView.findViewById(R.id.lblPrice);
        lblPrice.setText("Giá: " + cartItem.getItem().getPrice());

//        lblAmount
        TextView lblAmount = convertView.findViewById(R.id.lblAmount);
        lblAmount.setText(cartItem.getAmount());

//        btnRemove
        ImageView btnRemove = convertView.findViewById(R.id.btnRemove);
//        TODO

//        btnAdd
        ImageView btnAdd = convertView.findViewById(R.id.btnAdd);
//        TODO

//        btnDelete
        ImageView btnDelete = convertView.findViewById(R.id.btnDelete);
//        TODO

//        Return convertView
        return convertView;
    }
}
