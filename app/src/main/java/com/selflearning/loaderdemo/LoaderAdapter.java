package com.selflearning.loaderdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.selflearning.R;

import java.util.List;

public class LoaderAdapter extends BaseAdapter {
    private final LoaderDemoActivity mActivity;
    private List<String> mList;

    public LoaderAdapter(LoaderDemoActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mActivity).inflate(R.layout.list_item, null);
        TextView tvString = (TextView) convertView.findViewById(R.id.tvString);
        tvString.setText(mList.get(position));
        return convertView;
    }

    public void setList(List<String> stringList) {
        this.mList = stringList;
    }
}
