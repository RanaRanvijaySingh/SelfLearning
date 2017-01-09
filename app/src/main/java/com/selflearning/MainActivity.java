package com.selflearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.selflearning.circularseekbardemo.RotateImageDemoActivity;
import com.selflearning.customnotification.CustomNotificationDemoActivity;
import com.selflearning.databaseupgradedemo.DatabaseUpgradeDemoActivity;
import com.selflearning.expandablerecyclerviewdemo.ExpandableRvDemoActivity;
import com.selflearning.filedemo.FileDemoActivity;
import com.selflearning.loaderdemo.LoaderDemoActivity;
import com.selflearning.localizationdemo.LocalizationDemoActivity;
import com.selflearning.materialdesigndemo.MaterialDesignDemoActivity;
import com.selflearning.observerpattern.ObserverPatternDemoActivity;
import com.selflearning.rotateviewdemo.RotateViewDemoActivity;
import com.selflearning.rxandroiddemo.RxAndroidDemoActivity;
import com.selflearning.sharedprefdemo.SharedPrefDemoActivity;
import com.selflearning.swipelayoutdemo.SwipeDemoActivity;
import com.selflearning.tddwithmvpmvvmdemo.TddDemoActivity;
import com.selflearning.toolbardemo.ToolbarDemoActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Class> activityList = new ArrayList<>();

    {
        activityList.add(MaterialDesignDemoActivity.class);
        activityList.add(LoaderDemoActivity.class);
        activityList.add(ToolbarDemoActivity.class);
        activityList.add(ExpandableRvDemoActivity.class);
        activityList.add(CustomNotificationDemoActivity.class);
        activityList.add(RotateImageDemoActivity.class);
        activityList.add(FileDemoActivity.class);
        activityList.add(TddDemoActivity.class);
        activityList.add(SharedPrefDemoActivity.class);
        activityList.add(DatabaseUpgradeDemoActivity.class);
        activityList.add(RotateViewDemoActivity.class);
        activityList.add(RxAndroidDemoActivity.class);
        activityList.add(ObserverPatternDemoActivity.class);
        activityList.add(SwipeDemoActivity.class);
        activityList.add(LocalizationDemoActivity.class);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeDemoList();
    }

    private void initializeDemoList() {
        final RecyclerView rvDemos = (RecyclerView) findViewById(R.id.rvDemos);
        rvDemos.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvDemos.setLayoutManager(layoutManager);
        final DemoAdapter demoAdapter = new DemoAdapter(getDemoList(), demoClickListener);
        rvDemos.setAdapter(demoAdapter);
    }

    private List<String> getDemoList() {
        final List<String> demoList = new ArrayList<>();
        demoList.add(getResources().getString(R.string.demo_material_design));
        demoList.add(getResources().getString(R.string.demo_loader));
        demoList.add(getResources().getString(R.string.demo_toolbar));
        demoList.add(getResources().getString(R.string.demo_expandable_list));
        demoList.add(getResources().getString(R.string.demo_custom_notification));
        demoList.add(getResources().getString(R.string.demo_rotate_image));
        demoList.add(getResources().getString(R.string.demo_file));
        demoList.add(getResources().getString(R.string.demo_tdd_mvp_mvvp));
        demoList.add(getResources().getString(R.string.demo_shared_pref_screen));
        demoList.add(getResources().getString(R.string.demo_database_upgrade));
        demoList.add(getResources().getString(R.string.demo_custom_rotate_view));
        demoList.add(getResources().getString(R.string.demo_rx_android));
        demoList.add(getResources().getString(R.string.demo_observer_pattern));
        demoList.add(getResources().getString(R.string.demo_swipe_layout));
        demoList.add(getResources().getString(R.string.demo_localization));
        return demoList;
    }

    private final DemoClickListener demoClickListener = new DemoClickListener() {
        @Override
        public void selectedPosition(int adapterPosition) {
            startActivity(activityList.get(adapterPosition));
        }
    };

    public void startActivity(final Class aClass) {
        startActivity(new Intent(this, aClass));
    }
}
