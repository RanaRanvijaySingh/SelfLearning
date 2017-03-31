package com.selflearning.boayzswipelibdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.selflearning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 23/03/17.
 * Purpose of this class is to
 */

public class BoayzListAdapter extends BaseAdapter {
    private final Context mContext;
    private List<String> mList = new ArrayList<>();
    private DummyItemClickListener mDummyItemClickListener;

    public BoayzListAdapter(List<String> stringList, Context context) {
        mList = stringList;
        mContext = context;
    }

    public void setmDummyItemClickListener(DummyItemClickListener clickListener) {
        this.mDummyItemClickListener = clickListener;
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_demo, null);
        TextView textView = (TextView) view.findViewById(R.id.tvDemoName);
        textView.setText(mList.get(position));
       /* textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDummyItemClickListener.onClick(position);
            }
        });*/
        return view;
    }
}
