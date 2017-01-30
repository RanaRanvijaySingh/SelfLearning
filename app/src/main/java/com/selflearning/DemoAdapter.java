package com.selflearning;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoViewHolder> {

    private List<String> mList;
    private DemoClickListener mDemoClickListener;

    public DemoAdapter(List<String> mList, DemoClickListener demoClickListener) {
        this.mList = mList;
        this.mDemoClickListener = demoClickListener;
    }

    @Override
    public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DemoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_demo, null));
    }

    @Override
    public void onBindViewHolder(DemoViewHolder holder, int position) {
        holder.tvDemoName.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class DemoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvDemoName;

        public DemoViewHolder(final View itemView) {
            super(itemView);
            tvDemoName = (TextView) itemView.findViewById(R.id.tvDemoName);
            tvDemoName.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            mDemoClickListener.selectedPosition(getAdapterPosition());
        }
    }
}
