package org.lequochai.fashionshop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.lequochai.fashionshop.R;
import org.lequochai.fashionshop.entities.Order;
import org.lequochai.fashionshop.utils.ViewFactory;

import java.util.List;

public class OrdersListViewItemAdapter extends BaseAdapter {
//    Fields:
    private Context context;
    private List<Order> orders;
    private LayoutInflater inflater;

//    Constructors:
    public OrdersListViewItemAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
        inflater = LayoutInflater.from(context);
    }

//    Methods:
    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Inflate if convertView is null
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.orders_list_view_item, null);
        }

//        Get order
        Order order = orders.get(position);

//        Build view
        ViewFactory.buildOrdersListViewItem(context, convertView, order);

        return convertView;
    }
}
