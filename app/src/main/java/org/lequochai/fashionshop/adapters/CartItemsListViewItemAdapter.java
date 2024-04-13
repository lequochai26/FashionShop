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
import org.lequochai.fashionshop.utils.ViewFactory;

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

//        Build view
        ViewFactory.buildCartItemsListItemView(context, convertView, cartItem);

//        Return convertView
        return convertView;
    }
}
