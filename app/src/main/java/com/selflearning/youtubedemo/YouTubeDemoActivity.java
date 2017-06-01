package com.selflearning.youtubedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.selflearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YouTubeDemoActivity extends AppCompatActivity {

    @BindView(R.id.frameLayout)
    FrameLayout mFrameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,
                new YouTubeFragment()).commit();
    }
}
