package com.selflearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.selflearning.advancefabdemo.AdvanceFABDemoActivity;
import com.selflearning.boayzswipelibdemo.BoayzSwipeLibDemoActivity;
import com.selflearning.circularseekbardemo.RotateImageDemoActivity;
import com.selflearning.customfabdemo.CustomFABDemoActivity;
import com.selflearning.customnotification.CustomNotificationDemoActivity;
import com.selflearning.databaseupgradedemo.DatabaseUpgradeDemoActivity;
import com.selflearning.expandablerecyclerviewdemo.ExpandableRvDemoActivity;
import com.selflearning.filedemo.FileDemoActivity;
import com.selflearning.loaderdemo.LoaderDemoActivity;
import com.selflearning.localizationdemo.LocalizationDemoActivity;
import com.selflearning.materialdesigndemo.MaterialDesignDemoActivity;
import com.selflearning.observerpattern.ObserverPatternDemoActivity;
import com.selflearning.pulltorefresh.PullToRefreshDemoActivity;
import com.selflearning.rotateviewdemo.RotateViewDemoActivity;
import com.selflearning.rxandroiddemo.RxAndroidDemoActivity;
import com.selflearning.scaleanimation.ScaleAnimationDemoActivity;
import com.selflearning.swiftperfectdemo.DummyResponseModel;
import com.selflearning.swiftperfectdemo.SwiftPerfectDemoActivity;
import com.selflearning.swipelayoutdemo.SwipeDemoActivity;
import com.selflearning.toolbardemo.ToolbarDemoActivity;
import com.selflearning.twodimensionfabdemo.TwoDimensionFABDemoActivity;
import com.selflearning.youtubedemo.YouTubeDemoActivity;

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
        activityList.add(DatabaseUpgradeDemoActivity.class);
        activityList.add(RotateViewDemoActivity.class);
        activityList.add(RxAndroidDemoActivity.class);
        activityList.add(ObserverPatternDemoActivity.class);
        activityList.add(SwipeDemoActivity.class);
        activityList.add(LocalizationDemoActivity.class);
        activityList.add(PullToRefreshDemoActivity.class);
        activityList.add(CustomFABDemoActivity.class);
        activityList.add(AdvanceFABDemoActivity.class);
        activityList.add(TwoDimensionFABDemoActivity.class);
        activityList.add(ScaleAnimationDemoActivity.class);
        activityList.add(BoayzSwipeLibDemoActivity.class);
        activityList.add(SwiftPerfectDemoActivity.class);
        activityList.add(YouTubeDemoActivity.class);
    }

    private String str = "{\"ID\":null,\"name\":\"Doe\",\"first-name\":\"John\",\"age\":25," +
            "\"hobbies\":[\"reading\",\"cinema\",{\"sports\":" +
            "[\"volley-ball\",\"badminton\"]}],\"address\":{}}";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeDemoList();
    }

    /**
     * Function to initialize demo list
     */
    private void initializeDemoList() {
        final RecyclerView rvDemos = (RecyclerView) findViewById(R.id.rvDemos);
        rvDemos.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvDemos.setLayoutManager(layoutManager);
        final DemoAdapter demoAdapter = new DemoAdapter(getDemoList(), mDemoClickListener);
        rvDemos.setAdapter(demoAdapter);
    }

    /**
     * Function to get demo list.
     *
     * @return List
     */
    private List<String> getDemoList() {
        final List<String> demoList = new ArrayList<>();
        demoList.add(getResources().getString(R.string.demo_material_design));
        demoList.add(getResources().getString(R.string.demo_loader));
        demoList.add(getResources().getString(R.string.demo_toolbar));
        demoList.add(getResources().getString(R.string.demo_expandable_list));
        demoList.add(getResources().getString(R.string.demo_custom_notification));
        demoList.add(getResources().getString(R.string.demo_rotate_image));
        demoList.add(getResources().getString(R.string.demo_file));
        demoList.add(getResources().getString(R.string.demo_database_upgrade));
        demoList.add(getResources().getString(R.string.demo_custom_rotate_view));
        demoList.add(getResources().getString(R.string.demo_rx_android));
        demoList.add(getResources().getString(R.string.demo_observer_pattern));
        demoList.add(getResources().getString(R.string.demo_swipe_layout));
        demoList.add(getResources().getString(R.string.demo_localization));
        demoList.add(getResources().getString(R.string.demo_pull_to_refresh));
        demoList.add(getResources().getString(R.string.demo_custom_fab));
        demoList.add(getResources().getString(R.string.demo_advance_fab));
        demoList.add(getResources().getString(R.string.demo_2d_fab));
        demoList.add(getResources().getString(R.string.demo_scale_animation));
        demoList.add(getResources().getString(R.string.demo_boayz_swipe_lib));
        demoList.add(getResources().getString(R.string.demo_swift_perfect));
        demoList.add(getResources().getString(R.string.demo_you_tube));
        return demoList;
    }

    private final DemoClickListener mDemoClickListener = new DemoClickListener() {
        @Override
        public void selectedPosition(int adapterPosition) {
            startActivity(activityList.get(adapterPosition));
        }
    };

    public void startActivity(final Class aClass) {
        startActivity(new Intent(this, aClass));
    }
}
