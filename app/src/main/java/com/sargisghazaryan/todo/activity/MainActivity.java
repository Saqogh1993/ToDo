package com.sargisghazaryan.todo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
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
                startActivityForResult(intent, 1);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ItemModel itemModel = new ItemModel();
        if (data == null) throw new AssertionError();
        itemModel.setTitle(data.getStringExtra("title"));
        itemModel.setDescription(data.getStringExtra("description"));
        itemList.add(itemModel);

    }

    @Override
    public void onItemClick(int position) {
        // define code to go to ItemActivity
        Intent intent = new Intent(this, ItemActivity.class);
        startActivity(intent);
    }
}
