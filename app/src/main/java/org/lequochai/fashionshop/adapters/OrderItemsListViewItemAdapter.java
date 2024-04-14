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

public class OrderItemsListViewItemAdapter extends BaseAdapter {
//    Fields:
    private Context context;
    private List<Order.Item> orderItems;
    private LayoutInflater inflater;

//    Constructors:
    public OrderItemsListViewItemAdapter(Context context, List<Order.Item> orderItems) {
        this.context = context;
        this.orderItems = orderItems;
    }

//    Methods:
    @Override
    public int getCount() {
        return orderItems.size();
    }

    @Override
    public Object getItem(int position) {
        return orderItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.order_items_list_view_item, null);
        }

//        Get orderItem
        Order.Item orderItem = orderItems.get(position);

//        Build view
        ViewFactory.buildOrderItemsListViewItem(context, convertView, orderItem);

//        Return convertView
        return convertView;
    }
}
