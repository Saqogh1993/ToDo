package com.sargisghazaryan.todo.activity;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sargisghazaryan.todo.R;
import com.sargisghazaryan.todo.Util.DateUtil;
import com.sargisghazaryan.todo.model.ItemModel;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class ItemActivity extends AppCompatActivity {

    public static final String ARG_TODO_ITEM = "arg.todo.item";

    EditText title, description;
    TextView date;
    TextView priority;
    Button button;
    ImageView increase;
    ImageView decrease;

    ItemModel itemModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        init();

        if (getIntent().hasExtra(ARG_TODO_ITEM)) {
            itemModel = getIntent().getParcelableExtra(ARG_TODO_ITEM);
            fillData(itemModel);
        }
    }

    private void init() {
        title = (EditText) findViewById(R.id.item_title);
        description = (EditText) findViewById(R.id.item_description);
        date = (TextView) findViewById(R.id.item_date);
        date.setText(DateUtil.formatDateToLongStyle(new Date()));
        button = (Button) findViewById(R.id.item_submit);
        priority = (TextView) findViewById(R.id.item_priority_value);
        increase = (ImageView) findViewById(R.id.item_priority_increase);
        decrease = (ImageView) findViewById(R.id.item_priority_decrease);

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priority.setText(String.valueOf(Integer.parseInt(priority.getText().toString()) + 1));
            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priority.setText(String.valueOf(Integer.parseInt(priority.getText().toString()) - 1));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
                finish();
            }
        });
    }

    private void fillData(ItemModel itemModel) {
        title.setText(itemModel.getTitle());
        description.setText(itemModel.getDescription());
        date.setText(itemModel.getDate());
        priority.setText(String.valueOf(itemModel.getPriority()));
    }

    private ItemModel createTodoItemFromInput() {
        if (itemModel == null) {
            // If item is newly created initialize with uuid
            itemModel = new ItemModel();
            itemModel.setId(UUID.randomUUID().toString());
        }
        itemModel.setTitle(title.getText().toString());
        itemModel.setDescription(description.getText().toString());
        itemModel.setDate(date.getText().toString());
        itemModel.setPriority(Integer.parseInt(priority.getText().toString()));

        return itemModel;
    }

    private void submit() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(ARG_TODO_ITEM, createTodoItemFromInput());
        setResult(RESULT_OK, intent);
        finish();
    }

}
