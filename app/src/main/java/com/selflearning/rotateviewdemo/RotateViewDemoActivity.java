package com.selflearning.rotateviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.selflearning.R;

public class RotateViewDemoActivity extends AppCompatActivity {
    private static final String TAG = "RotateViewDemoActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_view_demo);
        initializeComponents();
    }

    /**
     * Function to initialize components of the class.
     */
    private void initializeComponents() {
        RotateView rotateView = (RotateView) findViewById(R.id.rotateView);
        /**
         * Adding rotation change listener on your rotation view.
         */
        rotateView.setOnRotationChangeListener(rotationChangeListener);
        /**
         * Setting rotation by angle.
         */
//        rotateView.setRotateViewAngle(60);
        /**
         * Setting image from drawable.
         */
//        rotateView.setRotateViewImage(R.drawable.ic_arrow_colored);
    }

    private RotateView.OnRotationChangeListener rotationChangeListener =
            new RotateView.OnRotationChangeListener() {

                @Override
                public void rotationChange(double angle) {
                    Log.i(TAG, "" + angle);
                }
            };
}
