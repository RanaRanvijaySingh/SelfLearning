package com.selflearning.materialdesigndemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.selflearning.R;

public class MaterialDesignDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design_demo);
        initializeComponents();
    }

    private void initializeComponents() {
        initializeToolbar();
        initializeTabs();
    }

    private void initializeToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initializeTabs() {
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new ListContentFragment(), "List");
        pagerAdapter.addFragment(new TileContentFragment(), "Tile");
        pagerAdapter.addFragment(new CardContentFragment(), "Card");
        viewPager.setAdapter(pagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }
}
