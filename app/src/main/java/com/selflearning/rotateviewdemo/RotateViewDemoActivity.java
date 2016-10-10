package com.selflearning.rotateviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.selflearning.R;

public class RotateViewDemoActivity extends AppCompatActivity {
    private static final String TAG = "RotateViewDemoActivity";
    private ImageView imageView;
    //    private RelativeLayout rlRotation;
    private View viewTouchHolder;
    private RelativeLayout rlGroupRotation;
    private int pivotX = 0;
    private int pivotY = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_view_demo);
        imageView = (ImageView) findViewById(R.id.imageView);
//        rlRotation = (RelativeLayout) findViewById(rlRotation);
        rlGroupRotation = (RelativeLayout) findViewById(R.id.rlGroupRotation);
        viewTouchHolder = findViewById(R.id.viewTouchHolder);
//        rlRotation.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
//        rlRotation.setOnTouchListener(backgroundTouchListener);
        viewTouchHolder.setOnTouchListener(viewTouchListener);
    }

    private int c = 0;
    private View.OnTouchListener viewTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    pivotX = rlGroupRotation.getWidth() / 2;
                    pivotY = rlGroupRotation.getHeight();
                    Log.i(TAG, "ACTION_DOWN " + event.getX() + " " + event.getY());
                    break;
                case MotionEvent.ACTION_UP:
                    Log.i(TAG, "ACTION_UP " + event.getX() + " " + event.getY());
                    break;
                case MotionEvent.ACTION_MOVE:
//                    Log.i(TAG, "ACTION_MOVE " + event.getX() + " " + event.getY());
                    int position[] = new int[2];
                    viewTouchHolder.getLocationOnScreen(position);
//                    Log.i(TAG, "ACTION_MOVE " + position[0] + " " + position[1]);
                    /*imageView.setX(imageView.getX() + event.getX());
                    imageView.setY(imageView.getY() + event.getY());*/
//                    int pivotPoints[] = new int[]{pivotX, pivotY};
                    int pivotPoints[] = new int[]{pivotX, pivotY};
                    double angle = getAngle(position, pivotPoints);
//                    Log.i(TAG, "ACTION_MOVE " + angle);
                    rotateImage(c++);
                    break;
            }
            return true;
        }
    };

    /*private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                    imageView.getLayoutParams();
            layoutParams.height = rlRotation.getHeight() / 2;
            imageView.setLayoutParams(layoutParams);
            imageView.setX(rlRotation.getWidth() / 2);
        }
    };*/

    private void rotateImage(double progress) {
        rlGroupRotation.setPivotX(pivotX);
        rlGroupRotation.setPivotY(pivotY);
        rlGroupRotation.setRotation((int) progress);
    }

    public double getAngle(int[] position, int[] pivot) {
        Log.i(TAG, "" + position[1] + " " + pivot[1] + " " + position[0] + " " + pivot[0]);
        return Math.toDegrees(Math.atan2(position[1] - pivot[1], position[0] - pivot[0]));
    }

/*
    private View.OnTouchListener backgroundTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.i(TAG, "ACTION_DOWN " + event.getX() + " " + event.getY());
                    break;
                case MotionEvent.ACTION_UP:
                    Log.i(TAG, "ACTION_UP " + event.getX() + " " + event.getY());
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.i(TAG, "ACTION_MOVE " + event.getX() + " " + event.getY());
                    break;
            }
            return true;
        }
    };*/
}
