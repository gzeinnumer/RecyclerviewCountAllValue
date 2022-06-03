package com.gzeinnumer.recyclerviewcountallvalue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gzeinnumer.recyclerviewcountallvalue.databinding.ItemRvBinding;

import java.util.ArrayList;
import java.util.List;

public class CountDataAdapter extends RecyclerView.Adapter<CountDataAdapter.MyHolder> {
    private final List<ItemRvBinding> holders;
    private final Context context;
    private List<String> list;
    private List<String> listFilter;
    private OnFocusListener onFocusListener;

    public CountDataAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = new ArrayList<>(list);
        this.listFilter = new ArrayList<>(list);
        this.holders = new ArrayList<>(list.size());
        initHolders();
    }

    public void setOnFocusListener(OnFocusListener onFocusListener) {
        this.onFocusListener = onFocusListener;
    }

    public void setList(List<String> list) {
        this.list = new ArrayList<>(list);
        this.listFilter = new ArrayList<>(list);
        initHolders();
        notifyDataSetChanged();
    }

    private void initHolders() {
        for (int i = 0; i < list.size(); i++) {
            holders.add(null);
        }
    }

    public List<ItemRvBinding> getHolders() {
        return holders;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(ItemRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holders.set(position, ItemRvBinding.bind(holder.itemBinding.getRoot()));
        holder.bind(list.get(position), onFocusListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnFocusListener {
        void isFocus(boolean isFocus);
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public ItemRvBinding itemBinding;

        public MyHolder(@NonNull ItemRvBinding itemView) {
            super(itemView.getRoot());
            itemBinding = itemView;
        }

        public void bind(String data, OnFocusListener onFocusListener) {
            if (onFocusListener != null) {
                itemBinding.edData.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean hasFocus) {
                        onFocusListener.isFocus(hasFocus);
                    }
                });
            }
        }
    }
}