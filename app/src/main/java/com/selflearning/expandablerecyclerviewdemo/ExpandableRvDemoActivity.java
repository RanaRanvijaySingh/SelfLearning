package com.selflearning.expandablerecyclerviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.selflearning.R;

import java.util.ArrayList;

public class ExpandableRvDemoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_rv_demo);
        RecyclerView rvExpandable = (RecyclerView) findViewById(R.id.rvExpandable);
        rvExpandable.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvExpandable.setLayoutManager(layoutManager);
        ExpandableAdapter expandableAdapter = new ExpandableAdapter(this,
                generateCrimes());
        expandableAdapter.setCustomParentAnimationViewId(R.id.parent_list_item_expand_arrow);
        expandableAdapter.setParentClickableViewAnimationDefaultDuration();
        expandableAdapter.setParentAndIconExpandOnClick(true);
        rvExpandable.setAdapter(expandableAdapter);
    }

    private ArrayList<ParentObject> generateCrimes() {
        Parent[] parents = getDummyList();
        ArrayList<ParentObject> parentObjects = new ArrayList<>();
        for (Parent parent : parents) {
            ArrayList<Object> childList = new ArrayList<>();
            for (int i = 0; i < parent.getChildObjectList().size(); i++) {
                Object o = parent.getChildObjectList().get(i);
                Gson gson = new Gson();
                JsonObject jsonObject = gson.toJsonTree(o).getAsJsonObject();
                Child child = gson.fromJson(jsonObject.toString(), Child.class);
                childList.add(child);
            }
            parent.setChildObjectList(childList);
            parentObjects.add(parent);
        }
        return parentObjects;
    }

    private Parent[] getDummyList() {
        String jsonData = "[{\"title\":\"topwear\",\n" +
                "\"mChildrenList\":[{\"message\":\"topwear 1\"},{\"message\":\"topwear 2\"},{\"message\":\"topwear 3\"}]},\n" +
                "{\"title\":\"swimwear\",\n" +
                "\"mChildrenList\":[{\"message\":\"swimwear 1\"},{\"message\":\"swimwear 2\"},{\"message\":\"swimwear 3\"}]}]";
        return new Gson().fromJson(jsonData, Parent[].class);
    }
}
