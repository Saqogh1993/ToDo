package com.sargisghazaryan.todo.adapter;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sargisghazaryan.todo.R;
import com.sargisghazaryan.todo.model.ItemModel;

import java.util.List;
import java.util.Objects;

import static android.widget.AdapterView.*;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private List<ItemModel> itemList;
    private OnItemSelected onItemSelected;

    public ItemAdapter(List<ItemModel> itemList, OnItemSelected onItemSelected) {
        this.itemList = itemList;
        this.onItemSelected = onItemSelected;
    }

    public void addItem(ItemModel item) {
        itemList.add(item);
        notifyItemInserted(itemList.size() - 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void updateItem(ItemModel item) {
        for (int i = 0; i < itemList.size(); i++) {
            if (Objects.equals(item, itemList.get(i))) {
                itemList.set(i, item);
                notifyItemChanged(i);
            }
        }
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
        TextView date = itemViewHolder.date;
        title.setText(itemList.get(position).getTitle());
        description.setText(itemList.get(position).getDescription());
        date.setText(itemList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder implements OnClickListener{

        TextView title;
        TextView description;
        TextView date;
        OnItemSelected onItemSelected;

        ItemViewHolder(@NonNull View itemView, OnItemSelected onItemSelected) {
            super(itemView);
            title = itemView.findViewById(R.id.each_item_title);
            description = itemView.findViewById(R.id.each_item_description);
            date = itemView.findViewById(R.id.each_item_date);
            this.onItemSelected = onItemSelected;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemSelected.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemSelected {
        void onItemClick(int position);
    }
}
