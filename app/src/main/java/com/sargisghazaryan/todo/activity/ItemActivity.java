package com.sargisghazaryan.todo.activity;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sargisghazaryan.todo.R;
import com.sargisghazaryan.todo.model.ItemModel;

import java.text.DateFormat;
import java.util.Date;

public class ItemActivity extends AppCompatActivity {

    EditText title, description;
    TextView date;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        title = (EditText) findViewById(R.id.item_title);
        description = (EditText) findViewById(R.id.item_description);
        date = (TextView) findViewById(R.id.item_date);
        date.setText(new Date().toString());
        button = (Button) findViewById(R.id.item_submit);

        final Intent intent = new Intent(this, MainActivity.class);

        String requestCode = getIntent().getStringExtra("requestCode");

        if (requestCode.equals("1")) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String titleText = title.getText().toString();
                    String descriptionText = description.getText().toString();
                    String dateText = date.getText().toString();
                    intent.putExtra("date", dateText);
                    intent.putExtra("title", titleText);
                    intent.putExtra("description", descriptionText);
                    setResult(1, intent);
                    finish();
                }
            });
        } else if (requestCode.equals("2")) {
            ItemModel itemModel = getIntent().getParcelableExtra("item");
            fillData(itemModel);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String titleText = title.getText().toString();
                    String descriptionText = description.getText().toString();
                    String dateText = new Date().toString();
                    intent.putExtra("date", dateText);
                    intent.putExtra("title", titleText);
                    intent.putExtra("description", descriptionText);
                    setResult(2, intent);
                    finish();
                }
            });
        }
    }

    private void fillData(ItemModel itemModel) {
        title.setText(itemModel.getTitle());
        description.setText(itemModel.getDescription());
        date.setText(itemModel.getDate());
    }

}
