package com.selflearning.twodimensionfabdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.selflearning.R;

import java.util.ArrayList;
import java.util.List;

public class TwoDimensionFABDemoActivity extends AppCompatActivity implements View.OnClickListener {
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private FloatingActionButton fab;
    private boolean isFabOpen = false;
    private List<Integer> floatingActionButtonIds = new ArrayList<>();
    private List<FloatingActionsMenu> floatingActionMenus = new ArrayList<>();

    {
        floatingActionButtonIds.add(R.id.famButtons);
        floatingActionButtonIds.add(R.id.famImages);
        floatingActionButtonIds.add(R.id.famShapes);
        floatingActionButtonIds.add(R.id.famTexts);
//        floatingActionButtonIds.add(R.id.famTitleBar);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_dimension_fab_demo);
        initializeFloatingActionMenu();
        fab = (FloatingActionButton) findViewById(R.id.fabAllOptions);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        fab.setOnClickListener(this);
    }

    private void initializeFloatingActionMenu() {
        for (int menuId : floatingActionButtonIds) {
            FloatingActionsMenu floatingActionsMenu = (FloatingActionsMenu) findViewById(menuId);
            floatingActionMenus.add(floatingActionsMenu);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fabAllOptions:
                animateFAB();
                break;
        }
    }

    public void animateFAB() {
        if (isFabOpen) {
            fab.startAnimation(rotate_backward);
            closeAllMenu();
            isFabOpen = false;
            Log.d("Raj", "close");
        } else {
            fab.startAnimation(rotate_forward);
            openAllMenu();
            isFabOpen = true;
            Log.d("Raj", "open");
        }
    }

    private void closeAllMenu() {
        for (FloatingActionsMenu actionsMenu : floatingActionMenus) {
            actionsMenu.startAnimation(fab_close);
            actionsMenu.setClickable(false);
        }
    }

    private void openAllMenu() {
        for (FloatingActionsMenu actionsMenu : floatingActionMenus) {
            actionsMenu.startAnimation(fab_open);
        }
    }
}
