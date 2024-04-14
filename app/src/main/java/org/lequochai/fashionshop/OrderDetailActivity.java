package org.lequochai.fashionshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.lequochai.fashionshop.adapters.OrderItemsListViewItemAdapter;
import org.lequochai.fashionshop.controllers.Controller;
import org.lequochai.fashionshop.controllers.oderdetailactivity.LoadOrderController;
import org.lequochai.fashionshop.entities.Order;

public class OrderDetailActivity extends AppCompatActivity {
//    Fields:
    private ImageView btnBack;
    private TextView lblOrderId;
    private TextView lblStatus;
    private TextView lblDate;
    private TextView lblTotalPrice;
    private ListView orderItemsListView;
    private Button btnCancel;

    private Controller<String> loadOrderController;

    private String id;

//    Constructors:
    public OrderDetailActivity() {

    }

//    Creation method:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        id = getIntent().getStringExtra("id");

        getViews();

        initialControllers();

        setupViews();

        init();
    }

//    Setup methods:
    private void getViews() {
        btnBack = findViewById(R.id.btnBack5);
        lblOrderId = findViewById(R.id.lblOrderId2);
        lblStatus = findViewById(R.id.lblStatus);
        lblDate = findViewById(R.id.lblDate2);
        lblTotalPrice = findViewById(R.id.lblTotalPrice);
        orderItemsListView = findViewById(R.id.orderItemsListView);
        btnCancel = findViewById(R.id.btnCancel2);
    }

    private void initialControllers() {
        loadOrderController = new LoadOrderController(this);
    }

    private void setupViews() {
        btnBack.setOnClickListener(
                t -> finish()
        );

//        TODO
    }

//    Init method:
    private void init() {
        loadOrderController.execute(id);
    }

//    Methods:
    public void loadOrder(Order order) {
        Order.Status status = Order.Status.valueOf(order.getStatus());

        lblOrderId.setText(order.getId());
        lblStatus.setText("Trạng thái: " + Order.getStatusTitle(status));
        lblDate.setText("Ngày đặt: " + order.getDate().toString());
        lblTotalPrice.setText("Tổng giá trị: " + order.getTotalPrice());
        orderItemsListView.setAdapter(
                new OrderItemsListViewItemAdapter(this, order.getItems())
        );

        if (status == Order.Status.APPROVEMENT_AWAITING) {
            btnCancel.setEnabled(true);
        }
        else {
            btnCancel.setEnabled(false);
        }
    }
}