package org.lequochai.fashionshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.lequochai.fashionshop.adapters.OrdersListViewItemAdapter;
import org.lequochai.fashionshop.controllers.Controller;
import org.lequochai.fashionshop.controllers.orderedordersactivity.LoadOrderedOrdersController;
import org.lequochai.fashionshop.entities.Order;

import java.util.List;

public class OrderedOrderActivity extends AppCompatActivity {
//    Fields:
    private ImageView btnBack;
    private TextView lblTitle;
    private ListView ordersListView;

    private Controller<Void> loadOrderedOrdersController;

//    Constructors:
    public OrderedOrderActivity() {

    }

//    Creation method:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_order);

        getViews();

        initialControllers();

        setupViews();

        init();
    }

//    Setup methods:
    private void getViews() {
        btnBack = findViewById(R.id.btnBack4);
        lblTitle = findViewById(R.id.lblTitle);
        ordersListView = findViewById(R.id.ordersListView);
    }

    private void initialControllers() {
        loadOrderedOrdersController = new LoadOrderedOrdersController(this);
//        TODO
    }

    private void setupViews() {
        btnBack.setOnClickListener(
                t -> finish()
        );
    }

//    Init:
    private void init() {
        loadOrderedOrdersController.execute(null);
    }

//    Methods:
    public void loadOrders(List<Order> orders) {
        OrdersListViewItemAdapter adapter = new OrdersListViewItemAdapter(this, orders);
        ordersListView.setAdapter(adapter);
    }
}