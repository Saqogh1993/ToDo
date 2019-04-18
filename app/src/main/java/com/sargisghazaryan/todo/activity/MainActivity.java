package com.sargisghazaryan.todo.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.sargisghazaryan.todo.R;
import com.sargisghazaryan.todo.adapter.ItemAdapter;
import com.sargisghazaryan.todo.adapter.ItemAdapter.OnItemSelected;
import com.sargisghazaryan.todo.model.ItemModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemSelected {

    public static final int REQUEST_CODE_ADD = 1;
    public static final int REQUEST_CODE_EDIT = 2;

    FloatingActionButton buttonAdd;
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    List<ItemModel> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        final Intent intent = new Intent(this, ItemActivity.class);
        itemList = new ArrayList<>();
        itemAdapter = new ItemAdapter(itemList, this);
        buttonAdd = (FloatingActionButton) findViewById(R.id.button_add);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("requestCode", String.valueOf(REQUEST_CODE_ADD));
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            ItemModel itemModel = new ItemModel();
            if (data == null) throw new AssertionError();
            itemModel.setTitle(data.getStringExtra("title"));
            itemModel.setDescription(data.getStringExtra("description"));
            itemAdapter.addItem(itemModel);
        }else if (requestCode == 2) {
            ItemModel itemModel = new ItemModel();
            itemModel.setTitle(data.getStringExtra("title"));
            itemModel.setDescription(data.getStringExtra("description"));
            itemAdapter.updateItem(itemModel);
        }

    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(this, ItemActivity.class);

        ItemModel itemModel = itemList.get(position);
        intent.putExtra("title", itemModel.getTitle());
        intent.putExtra("description", itemModel.getDescription());
        intent.putExtra("requestCode", String.valueOf(REQUEST_CODE_EDIT));
        intent.putExtra("item", itemModel);

        startActivityForResult(intent, REQUEST_CODE_EDIT);
    }
}
