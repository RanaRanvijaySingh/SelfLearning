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
        rotateView.setOnRotationChangeListener(rotationChangeListener);
    }

    private RotateView.OnRotationChangeListener rotationChangeListener =
            new RotateView.OnRotationChangeListener() {

                @Override
                public void rotationChange(double angle) {
                    Log.i(TAG, "" + angle);
                }
            };
}
