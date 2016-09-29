package com.selflearning.tddwithmvpmvvmdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.selflearning.R;
import com.selflearning.toolbardemo.SimpleViewHolder;

import java.util.List;

public class CommentListAdapter extends RecyclerView.Adapter<SimpleViewHolder> {

    private List<Comment> mList;

    public CommentListAdapter(List<Comment> commentList) {
        this.mList = commentList;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_simple, null));
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.getTvString().setText(mList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }


    public void setList(List<Comment> commentList) {
        this.mList = commentList;
    }
}
