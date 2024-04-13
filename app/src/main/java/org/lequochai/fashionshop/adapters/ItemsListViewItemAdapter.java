package org.lequochai.fashionshop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.lequochai.fashionshop.R;
import org.lequochai.fashionshop.entities.Item;
import org.lequochai.fashionshop.utils.ViewFactory;

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

//        Get item
        Item item = items.get(position);

//        Build view
        ViewFactory.buildItemsListItemView(context, convertView, item);

//        Return convertView
        return convertView;
    }
}