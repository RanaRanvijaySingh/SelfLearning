package com.selflearning.scaleanimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.selflearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 17/03/17.
 * Purpose of this class is to
 */

public class ScaleAnimationDemoActivity extends AppCompatActivity {

    @BindView(R.id.relativeBackground)
    FrameLayout mRelativeBackground;

    @BindView(R.id.textViewScaleObject)
    TextView mTextViewScaleObject;


    @BindView(R.id.textViewExpandObject)
    TextView mTextViewExpandObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_animation);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.textViewScaleObject)
    public void onClickScaleObject(View view) {
//        scaleView(mRelativeBackground, 0, 10);
        scaleView();
    }

    public void scaleView() {
        Animation anim = new ScaleAnimation(
                1f, 5f, // Start and end values for the X axis scaling
                1f, 1f, // Start and end values for the Y axis scaling
                mTextViewExpandObject.getWidth() / 2, // Pivot point of X scaling
                mTextViewExpandObject.getHeight() / 2); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(1000);
        mTextViewExpandObject.startAnimation(anim);
    }

    private float getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}
