package com.selflearning.swipelayoutdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.selflearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SwipeDemoActivity extends AppCompatActivity {
    @BindView(R.id.swipeLayout)
    SwipeLayout swipeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_demo);
        ButterKnife.bind(this);
        swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
    }

    @OnClick(R.id.btnSurface)
    void onSurfaceClick() {
        Toast.makeText(this, "Surface clicked", Toast.LENGTH_SHORT).show();
        swipeLayout.close();
    }

    @OnClick(R.id.buttonBottomWrapper)
    void onClickBottomWrapper() {
        Toast.makeText(this, "Bottom Wrapper clicked", Toast.LENGTH_SHORT).show();
    }
}
