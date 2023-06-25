package com.example.skincareapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_Config {
    Context mContext;
    ItemsAdapter itemsAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<SkinCareInfo> items, List<String> keys){
        mContext = context;
        itemsAdapter = new ItemsAdapter(items, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(itemsAdapter);
    }

    class ItemView extends RecyclerView.ViewHolder{
        TextView name, description;
        String key;

        public ItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.card_view_item, parent, false));

            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
        }

        public void bind(SkinCareInfo item, String key){
            boolean expanded = item.isExpanded();

            description.setVisibility(expanded ? View.VISIBLE : View.GONE);

            name.setText(item.getName());
            description.setText(item.getDescription());
            this.key = key;
        }
    }
    class ItemsAdapter extends RecyclerView.Adapter<ItemView>{
        List<SkinCareInfo> itemsList;
        List<String> keys;

        public ItemsAdapter(List<SkinCareInfo> itemsList, List<String> keys) {
            this.itemsList = itemsList;
            this.keys = keys;
        }

        @NonNull
        @Override
        public ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemView holder, int position) {
            SkinCareInfo item = itemsList.get(position);
            holder.bind(item, keys.get(position));

            holder.itemView.setOnClickListener( v -> {
                boolean expanded = item.isExpanded();
                item.setExpanded(!expanded);
                notifyItemChanged(position);
            });
        }

        @Override
        public int getItemCount() {
            return itemsList == null ? 0 : itemsList.size();
        }
    }
}
