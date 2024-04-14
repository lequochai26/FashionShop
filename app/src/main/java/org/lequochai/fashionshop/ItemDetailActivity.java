package org.lequochai.fashionshop;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.lequochai.fashionshop.controllers.Controller;
import org.lequochai.fashionshop.controllers.itemdetailactivity.AddToCartController;
import org.lequochai.fashionshop.controllers.itemdetailactivity.LoadItemController;
import org.lequochai.fashionshop.entities.Item;
import org.lequochai.fashionshop.services.GlobalService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDetailActivity extends AppCompatActivity {
//    Fields:
    private ImageView btnBack;
    private ImageView imgAvatar;
    private TextView lblName;
    private TextView lblPrice;
    private TextView lblAmount;
    private TextView lblDescription;
    private LinearLayout metadataLinearLayout;
    private Button btnAddToCart;

    private Controller<String> loadItemController;
    private Controller<Void> addToCartController;

    private String id;
    private Item item;
    private Map<String, String> selection;

//    Constructors:
    public ItemDetailActivity() {

    }

//    Creation method:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        id = getIntent().getStringExtra("id");

        getViews();

        initialControllers();

        setupViews();

        init();
    }

//    Setup methods:
    private void getViews() {
        btnBack = findViewById(R.id.btnBack7);
        imgAvatar = findViewById(R.id.imgAvatar5);
        lblName = findViewById(R.id.lblName3);
        lblPrice = findViewById(R.id.lblPrice3);
        lblAmount = findViewById(R.id.lblAmount3);
        lblDescription = findViewById(R.id.lblDescription);
        metadataLinearLayout = findViewById(R.id.metadataLinearLayout);
        btnAddToCart = findViewById(R.id.btnAddToCart2);
    }

    private void initialControllers() {
        loadItemController = new LoadItemController(this);
        addToCartController = new AddToCartController(this);
    }

    private void setupViews() {
        btnBack.setOnClickListener(
                t -> finish()
        );

        btnAddToCart.setOnClickListener(
                t -> addToCartController.execute(null)
        );
    }

//    Init:
    private void init() {
        loadItemController.execute(id);
    }

//    Private methods:
    private void update() {
//        Search mapping base on
        Map<String, Object> mapping = item.getMetadata().searchMapping(selection);

//        Mapping not exist
        if (mapping == null) {
            lblPrice.setText("Giá: " + item.getMetadata().getMinPrice() + " - " + item.getMetadata().getMaxPrice());
            lblAmount.setText("Số lượng: " + item.getMetadata().getTotalAmount());
            return;
        }

        lblPrice.setText("Giá: " + mapping.get("price"));
        lblAmount.setText("Số lượng: " + mapping.get("amount"));
    }

//    Methods:
    public void loadItem(Item item) {
        this.item = item;

        if (item.getMetadata() != null) {
            selection = new HashMap<>();
        }

//        imgAvatar
        Picasso.get()
                .load(GlobalService.HOST_HTTP + item.getAvatar().substring(1))
                .into(imgAvatar);

//        lblName
        lblName.setText(item.getName());

//        lblPrice
        if (item.getMetadata() == null) {
            lblPrice.setText("Giá: " + item.getPrice());
        }
        else {
            lblPrice.setText("Giá: " + item.getMetadata().getMinPrice() + " - " + item.getMetadata().getMaxPrice());
        }

//        lblAmount
        if (item.getMetadata() == null) {
            lblAmount.setText("Số lượng: " + item.getAmount());
        }
        else {
            lblAmount.setText("Số lượng: " + item.getMetadata().getTotalAmount());
        }

//        lblDescription
        lblDescription.setText("Mô tả:\n" + item.getDescription());

//        metadataLinearLayout
        if (item.getMetadata() == null) {
            TextView lblNoMetadata = new TextView(this);
            lblNoMetadata.setText("Không có thông tin phân loại!");
            lblNoMetadata.setTextColor(getResources().getColor(R.color.black));
            lblNoMetadata.setTextSize(16);
            metadataLinearLayout.addView(lblNoMetadata);
        }
        else {
            Map<String, List<String>> options = item.getMetadata().getOptions();
            for (String title : options.keySet()) {
//                lblTitle
                TextView lblTitle = new TextView(this);
                lblTitle.setText(title + ":");
                lblTitle.setTextColor(Color.BLACK);
                lblTitle.setTextSize(16);
                lblTitle.setTypeface(null, Typeface.BOLD);
                metadataLinearLayout.addView(lblTitle);

//                selectionsLinearLayout
                LinearLayout selectionsLinearLayout = new LinearLayout(this);
                selectionsLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
                selectionsLinearLayout.setLayoutParams(
                        new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                );
                metadataLinearLayout.addView(selectionsLinearLayout);

//                selectionsRadioGroup
                RadioGroup selectionsRadioGroup = new RadioGroup(this);
                selectionsRadioGroup.setOrientation(RadioGroup.HORIZONTAL);
                metadataLinearLayout.addView(selectionsRadioGroup);

//                Selections creating
                for (String selection : options.get(title)) {
//                    selectionRadioButton
                    RadioButton selectionRadioButton = new RadioButton(this);
                    selectionRadioButton.setText(selection);
                    selectionRadioButton.setTextSize(16);
                    selectionRadioButton.setTextColor(Color.BLACK);
                    selectionRadioButton.setOnCheckedChangeListener(
                            (buttonView, isChecked) -> {
                                if (isChecked) {
                                    ItemDetailActivity.this.selection.put(
                                            title, selection
                                    );
                                    update();
                                }
                            }
                    );

                    selectionsRadioGroup.addView(selectionRadioButton);

                    if (selectionsRadioGroup.getChildCount() == 1) {
                        selectionRadioButton.setChecked(true);
                    }
                }
            }
        }
    }

//    Getters / setters:
    public Item getItem() {
        return item;
    }

    public Map<String, String> getSelection() {
        return selection;
    }
}