package com.selflearning.expandablerecyclerviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.selflearning.R;

import java.util.List;

public class ExpandableAdapter extends ExpandableRecyclerAdapter<CrimeParentViewHolder, CrimeChildViewHolder> {
    private final LayoutInflater mInflater;

    public ExpandableAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CrimeParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.list_item_parent, viewGroup, false);
        return new CrimeParentViewHolder(view);
    }

    @Override
    public CrimeChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.list_item_child, viewGroup, false);
        return new CrimeChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(CrimeParentViewHolder crimeParentViewHolder, int i, Object o) {
        Parent parent = (Parent) o;
        crimeParentViewHolder.mCrimeTitleTextView.setText(parent.getTitle());
    }

    @Override
    public void onBindChildViewHolder(CrimeChildViewHolder crimeChildViewHolder, int i, Object o) {
        Child child = (Child) o;
        crimeChildViewHolder.mCrimeDateText.setText(child.getMessage());
    }
}
