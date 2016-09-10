package com.selflearning.expandablerecyclerviewdemo;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

public class Parent implements ParentObject {

    /* Create an instance variable for your list of children */
    private List<Object> mChildrenList;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildrenList = list;
    }
}