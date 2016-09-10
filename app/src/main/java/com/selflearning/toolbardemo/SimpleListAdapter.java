package com.selflearning.toolbardemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.selflearning.R;

import java.util.List;

public class SimpleListAdapter extends RecyclerView.Adapter<SimpleViewHolder> {

    private List<String> mList;

    public SimpleListAdapter(List<String> dummyStringList) {
        this.mList = dummyStringList;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_simple, null));
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.getTvString().setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }
}
