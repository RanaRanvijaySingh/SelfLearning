package com.selflearning.pulltorefresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.selflearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PullToRefreshDemoActivity extends AppCompatActivity {

    private static final String REFRESH_COUNT = "Refresh Count : ";
    private int refreshCount = 1;

    @BindView(R.id.textViewOnRefresh)
    TextView textViewOnRefresh;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
        ButterKnife.bind(this);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    textViewOnRefresh.setText(REFRESH_COUNT + refreshCount++);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 2000);

        }
    };
}
