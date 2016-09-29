package com.selflearning.imagerotation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.selflearning.R;

public class RotateImageDemoActivity extends AppCompatActivity {
    private static final String TAG = "RotateImageDemoActivity";
    private ImageView imageView;
    private CircularSeekBar circularSeekBar;
    private RelativeLayout rlRotation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_image_demo);
        imageView = (ImageView) findViewById(R.id.imageView);
        rlRotation = (RelativeLayout) findViewById(R.id.rlRotation);
        circularSeekBar = (CircularSeekBar) findViewById(R.id.circularSeekBar);
        circularSeekBar.setOnSeekBarChangeListener(circularSeekBarChangeListener);
        rlRotation.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                    imageView.getLayoutParams();
            layoutParams.height = rlRotation.getHeight() / 2 - 30;
            imageView.setLayoutParams(layoutParams);
            imageView.setX(rlRotation.getWidth() / 2);
        }
    };

    private CircularSeekBar.OnCircularSeekBarChangeListener circularSeekBarChangeListener =
            new CircularSeekBar.OnCircularSeekBarChangeListener() {
                @Override
                public void onProgressChanged(CircularSeekBar circularSeekBar,
                                              int progress, boolean fromUser) {
                    rotateImage(progress);
                }

                @Override
                public void onStopTrackingTouch(CircularSeekBar seekBar) {

                }

                @Override
                public void onStartTrackingTouch(CircularSeekBar seekBar) {

                }
            };

    private void rotateImage(int progress) {
        imageView.setPivotX(imageView.getWidth() / 2);
        imageView.setPivotY(imageView.getHeight());
        imageView.setRotation(progress);
    }
}
