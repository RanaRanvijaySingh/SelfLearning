package com.selflearning.boayzswipelibdemo;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.selflearning.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 23/03/17.
 * Purpose of this class is to
 */

public class BoayzSwipeLibDemoActivity extends AppCompatActivity {

    private static final String TAG = BoayzSwipeLibDemoActivity.class.getName();
    @BindView(R.id.listView)
    SwipeMenuListView mSwipeMenuListView;
    private List<String> mListString = new ArrayList<>();
    private BoayzListAdapter mBoayzListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boayz_swipe_list);
        ButterKnife.bind(this);
        initSwipeView();
    }

    private void initSwipeView() {
        mListString = getDummyList();
        mBoayzListAdapter = new BoayzListAdapter(mListString, this);
        mBoayzListAdapter.setmDummyItemClickListener(mDummyItemClickListener);
        mSwipeMenuListView.setAdapter(mBoayzListAdapter);
        setSwipeItems("open");
        mSwipeMenuListView.setOnMenuItemClickListener(mItemClickListener);
    }

    private void setSwipeItems(final String buttonText) {
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle(buttonText);
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_android_black_24dp);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mSwipeMenuListView.setMenuCreator(creator);
        mSwipeMenuListView.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);
        SwipeMenuCreator creator1 = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle(buttonText);
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_android_black_24dp);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mSwipeMenuListView.setMenuCreator(creator1);
        mSwipeMenuListView.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);
    }

    private final SwipeMenuListView.OnMenuItemClickListener mItemClickListener =
            new SwipeMenuListView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                    Log.i(TAG, "onMenuItemClick: " + index);
                    switch (index) {
                        case 0:
                            break;
                        case 1:
                            break;
                    }
                    // false : close the menu; true : not close the menu
                    return false;
                }
            };

    private void invalidateList() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBoayzListAdapter.notifyDataSetChanged();
            }
        }, 500);
    }

    private final DummyItemClickListener mDummyItemClickListener = new DummyItemClickListener() {
        @Override
        public void onClick(int position) {
        }
    };

    private int dp2px(int dpValue) {
        Resources r = getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, r.getDisplayMetrics
                ());
    }

    public List<String> getDummyList() {
        List<String> dummyList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dummyList.add("Item " + i);
        }
        return dummyList;
    }
}
