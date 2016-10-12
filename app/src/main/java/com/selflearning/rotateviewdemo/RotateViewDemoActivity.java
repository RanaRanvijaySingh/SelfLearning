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
    private static final int QUADRANT_FIRST = 1;
    private static final int QUADRANT_SECOND = QUADRANT_FIRST + 1;
    private static final int QUADRANT_THIRD = QUADRANT_SECOND + 1;
    private static final int QUADRANT_FORTH = QUADRANT_THIRD + 1;
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
    private int pivotPositionX;
    private int pivotPositionY;
    private View.OnTouchListener viewTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    pivotPositionX = (int) (rlGroupRotation.getX() + rlGroupRotation.getWidth() / 2);
                    pivotPositionY = (int) (rlGroupRotation.getY() + rlGroupRotation.getHeight());
                    pivotX = rlGroupRotation.getWidth() / 2;
                    pivotY = rlGroupRotation.getHeight();
                    Log.i(TAG, "ACTION_DOWN " + event.getX() + " " + event.getY());
                    break;
                case MotionEvent.ACTION_UP:
                    Log.i(TAG, "ACTION_UP " + event.getX() + " " + event.getY());
                    break;
                case MotionEvent.ACTION_MOVE:
                    rotateView();
                    break;
            }
            return true;
        }
    };

    private void rotateView() {
        int position[] = new int[2];
        viewTouchHolder.getLocationOnScreen(position);
        Log.i(TAG, "ACTION_MOVE position" + position[0] + " " + position[1]);
        position[0] = position[0] + rlGroupRotation.getWidth() / 2;
        position[1] = position[1] + viewTouchHolder.getHeight() / 2;
        int pivotPoints[] = new int[]{pivotPositionX, pivotPositionY};
        double angle = getAngle(position, pivotPoints);
        Log.i(TAG, "ACTION_MOVE " + angle);
        rotateImage(angle);
    }

    private void rotateImage(double progress) {
        rlGroupRotation.setPivotX(pivotX);
        rlGroupRotation.setPivotY(pivotY);
        rlGroupRotation.setRotation((int) progress);
    }

    public double getAngle(int[] position, int[] pivot) {
        double angle = Math.toDegrees(Math.atan2(position[1] - pivot[1], position[0] - pivot[0]));
        if (angle == -90) {
            angle = 0;
        }
        int quadrant = getQuadrantForPoint(position, pivot);
        switch (quadrant) {
            case QUADRANT_FIRST:
                angle = 90 + angle;
                break;
            case QUADRANT_SECOND:
                angle = 270 + 180 + angle;
                break;
            case QUADRANT_THIRD:
                angle = 90 + angle;
                break;
            default:
                angle = 90 + angle;
                break;
        }
        Log.i(TAG, "quad " + angle + " " + quadrant);
        return angle;
    }

    public int getQuadrantForPoint(int[] position, int[] pivot) {
        int xDifference = position[0] - pivot[0];
        int yDifference = position[1] - pivot[1];
        if (xDifference >= 0 && yDifference <= 0) {
            return QUADRANT_FIRST;
        } else if (xDifference < 0 && yDifference < 0) {
            return QUADRANT_SECOND;
        } else if (xDifference < 0 && yDifference >= 0) {
            return QUADRANT_THIRD;
        } else if (xDifference >= 0 && yDifference >= 0) {
            return QUADRANT_FORTH;
        }
        return QUADRANT_FIRST;
    }
}
