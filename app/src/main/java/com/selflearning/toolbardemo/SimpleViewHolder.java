package com.selflearning.toolbardemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.selflearning.R;

public class SimpleViewHolder extends RecyclerView.ViewHolder {
    private TextView tvString;

    public SimpleViewHolder(View itemView) {
        super(itemView);
        tvString = (TextView) itemView.findViewById(R.id.tvString);
    }

    public TextView getTvString() {
        return tvString;
    }

    public void setTvString(TextView tvString) {
        this.tvString = tvString;
    }
}
