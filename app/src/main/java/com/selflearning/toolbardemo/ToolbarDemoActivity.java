package com.selflearning.toolbardemo;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.selflearning.R;
import com.selflearning.materialdesigndemo.CardContentFragment;
import com.selflearning.materialdesigndemo.ListContentFragment;
import com.selflearning.materialdesigndemo.PagerAdapter;
import com.selflearning.materialdesigndemo.TileContentFragment;

import java.util.ArrayList;
import java.util.List;

public class ToolbarDemoActivity extends AppCompatActivity {

    private static final String TAG = "Toolbar Demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_demo);
        initializeComponents();
    }

    private void initializeComponents() {
        initializeToolbar();
        initializeRecyclerView();
    }

    private void initializeToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout colsToolbar = (CollapsingToolbarLayout) findViewById(R.id.colsToolbar);
        colsToolbar.setTitle(TAG);
    }

    private void initializeRecyclerView() {
        RecyclerView rvStrings = (RecyclerView) findViewById(R.id.rvStrings);
        rvStrings.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvStrings.setLayoutManager(layoutManager);
        SimpleListAdapter simpleListAdapter = new SimpleListAdapter(getDummyStringList());
        rvStrings.setAdapter(simpleListAdapter);
    }

    private List<String> getDummyStringList() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stringList.add("Item " + i);
        }
        return stringList;
    }
}
