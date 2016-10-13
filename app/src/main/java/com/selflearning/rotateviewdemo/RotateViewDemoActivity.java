package com.selflearning.rotateviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
    private ImageView ivRotationImage;
    private RelativeLayout rlWrapper;
    private int centerPositionX;
    private int centerPositionY;
    private int pivotX = 0;
    private int pivotY = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_view_demo);
        ivRotationImage = (ImageView) findViewById(R.id.ivRotationImage);
        rlWrapper = (RelativeLayout) findViewById(R.id.rlWrapper);
        ivRotationImage.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        rlWrapper.setOnTouchListener(viewTouchListener);
    }

    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener =
            new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    /** Set wrapper layout size.*/
                    setWrapperSize();
                    /** Set image position in wrapper layout.*/
                    ivRotationImage.setY(0);
                }

                /**
                 * Function to set wrapper height and width depending on image which you give for
                 * rotation.
                 */
                private void setWrapperSize() {
                    RelativeLayout.LayoutParams layoutParams =
                            (RelativeLayout.LayoutParams) rlWrapper.getLayoutParams();
                    int viewHeight = ivRotationImage.getHeight();
                    layoutParams.height = viewHeight * 2;
                    layoutParams.width = viewHeight * 2;
                    rlWrapper.setLayoutParams(layoutParams);
                }
            };

    private View.OnTouchListener viewTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    setHookPositions();
                    rotateView(event);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    rotateView(event);
                    break;
            }
            return true;
        }

        /**
         * Function to set hook positions based on height and width of the wrapper.
         */
        private void setHookPositions() {
            /** Center point from where you can calculate the rotation.*/
            centerPositionX = rlWrapper.getWidth() / 2;
            centerPositionY = rlWrapper.getHeight() / 2;
            /** Pivot point for image from where you can rotate the image as a hinge.*/
            pivotX = ivRotationImage.getWidth() / 2;
            pivotY = ivRotationImage.getHeight();
        }
    };

    /**
     * Function to rotate the view.
     *
     * @param event MotionEvent
     */
    private void rotateView(MotionEvent event) {
        int position[] = new int[2];
        position[0] = (int) event.getX();
        position[1] = (int) event.getY();
        int pivotPoints[] = new int[]{centerPositionX, centerPositionY};
        /** Get the angle on which the view should rotate.*/
        double angle = getAngle(position, pivotPoints);
        rotateImage(angle);
    }

    /**
     * Function to  rotate the actual image at from the pivoted point at a given angle.
     *
     * @param angle double
     */
    private void rotateImage(double angle) {
        ivRotationImage.setPivotX(pivotX);
        ivRotationImage.setPivotY(pivotY);
        ivRotationImage.setRotation((int) angle);
    }

    /**
     * Function to get the angle based on touch position and pivoted position.
     *
     * @param position int[] index 0 is X point and index 1 is Y point.
     * @param pivot    int [] index 0 is X point and index 1 is Y point.
     * @return double
     */
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
        return angle;
    }

    /**
     * Find out in which quadrant user has touched based on give pivot position.
     *
     * @param position int[]
     * @param pivot    int[]
     * @return int
     */
    public int getQuadrantForPoint(int[] position, int[] pivot) {
        /** Know that on mobile (0,0) is top left corner.*/
        int xDifference = position[0] - pivot[0];
        int yDifference = position[1] - pivot[1];
        if (xDifference >= 0 && yDifference <= 0) {
            return QUADRANT_FIRST;
        } else if (xDifference < 0 && yDifference < 0) {
            return QUADRANT_SECOND;
        } else if (xDifference < 0 && yDifference >= 0) {
            return QUADRANT_THIRD;
        } else {
            return QUADRANT_FORTH;
        }
    }
}
