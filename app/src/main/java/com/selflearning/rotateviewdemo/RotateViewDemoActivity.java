package com.selflearning.rotateviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
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
    private RelativeLayout rlRotation;
    private RelativeLayout rlGroupRotation;
    private int c = 0;
    private int pivotPositionX;
    private int pivotPositionY;
    private int pivotX = 0;
    private int pivotY = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_view_demo);
        imageView = (ImageView) findViewById(R.id.imageView);
        rlRotation = (RelativeLayout) findViewById(R.id.rlRotation);
        rlGroupRotation = (RelativeLayout) findViewById(R.id.rlGroupRotation);
        imageView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        rlRotation.setOnTouchListener(viewTouchListener);
    }

    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener =
            new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    /** Set wrapper layout size.*/
                    RelativeLayout.LayoutParams layoutParams =
                            (RelativeLayout.LayoutParams) rlGroupRotation.getLayoutParams();
                    int viewHeight = imageView.getHeight();
                    layoutParams.height = viewHeight * 2;
                    layoutParams.width = viewHeight * 2;
                    rlGroupRotation.setLayoutParams(layoutParams);
                    /** Set image position in wrapper layout.*/
                    imageView.setY(0);
                }
            };

    private View.OnTouchListener viewTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    pivotPositionX = (int) (rlGroupRotation.getX() + rlGroupRotation.getWidth() / 2);
                    pivotPositionY = (int) (rlGroupRotation.getY() + rlGroupRotation.getHeight() / 2);
                    pivotX = imageView.getWidth() / 2;
                    pivotY = imageView.getHeight();
                    Log.i(TAG, "ACTION_DOWN " + event.getX() + " " + event.getY());
                    break;
                case MotionEvent.ACTION_UP:
                    Log.i(TAG, "ACTION_UP " + event.getX() + " " + event.getY());
                    break;
                case MotionEvent.ACTION_MOVE:
                    rotateView(event);
                    break;
            }
            return true;
        }
    };

    private void rotateView(MotionEvent event) {
        int position[] = new int[2];
        position[0] = (int) event.getX();// + (imageView.getWidth() / 2);
        position[1] = (int) event.getY();// + (imageView.getHeight() / 2);
        Log.i(TAG, "ACTION_MOVE position" + position[0] + " " + position[1]);
        int pivotPoints[] = new int[]{pivotPositionX, pivotPositionY};
        double angle = getAngle(position, pivotPoints);
        Log.i(TAG, "ACTION_MOVE angle" + angle);
        rotateImage(angle);
    }

    private void rotateImage(double progress) {
        imageView.setPivotX(pivotX);
        imageView.setPivotY(pivotY);
        imageView.setRotation((int) progress);
    }

    public double getAngle(int[] position, int[] pivot) {
        double angle = Math.toDegrees(Math.atan2(position[1] - pivot[1], position[0] - pivot[0]));
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
