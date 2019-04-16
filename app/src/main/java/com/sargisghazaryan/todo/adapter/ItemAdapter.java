package com.sargisghazaryan.todo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

import com.sargisghazaryan.todo.R;
import com.sargisghazaryan.todo.activity.ItemActivity;
import com.sargisghazaryan.todo.activity.MainActivity;
import com.sargisghazaryan.todo.model.ItemModel;

import java.util.List;

import static android.widget.AdapterView.*;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private List<ItemModel> itemList;
    private OnItemSelected onItemSelected;

    public ItemAdapter(List<ItemModel> itemList, OnItemSelected onItemSelected) {
        this.itemList = itemList;
        this.onItemSelected = onItemSelected;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_item,viewGroup,false);
        return new ItemViewHolder(view, onItemSelected);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int position) {
        TextView title = itemViewHolder.title;
        TextView description = itemViewHolder.description;
        title.setText(itemList.get(position).getTitle());
        description.setText(itemList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder implements OnClickListener{

        TextView title;
        TextView description;
        OnItemSelected onItemSelected;

        ItemViewHolder(@NonNull View itemView, OnItemSelected onItemSelected) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.each_item_title);
            description = (TextView) itemView.findViewById(R.id.each_item_description);
            this.onItemSelected = onItemSelected;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemSelected.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemSelected {
        public void onItemClick(int position);
    }
}