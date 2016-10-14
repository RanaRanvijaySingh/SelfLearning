package com.selflearning.rotateviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.selflearning.R;

public class RotateView extends RelativeLayout {
    private static final String TAG = "RotateView";
    private static final int QUADRANT_FIRST = 1;
    private static final int QUADRANT_SECOND = QUADRANT_FIRST + 1;
    private static final int QUADRANT_THIRD = QUADRANT_SECOND + 1;
    private static final int QUADRANT_FORTH = QUADRANT_THIRD + 1;
    private ImageView mIvRotationImage;
    private RelativeLayout mRlWrapper;
    private int mPivotX = 0;
    private int mPivotY = 0;
    private View mView;
    private int mRotateViewAngle;
    private int mRotateViewImage;
    private int[] mViewCenterPoint;

    public RotateView(Context context) {
        super(context);
    }

    public RotateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView(context, attrs);
    }

    public RotateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context, attrs);
    }

    private void initializeView(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.view_rotation, RotateView.this);
        initializeRotationView();
        getAttributes(context, attrs);
    }

    /**
     * Function to get the attributes values defined by user.
     *
     * @param context Context
     * @param attrs   AttributeSet
     */
    private void getAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.rotate_view, 0, 0);
        try {
            loadRotationAngle(typedArray);
            loadRotationImage(typedArray);
        } finally {
            typedArray.recycle();
        }
    }

    /**
     * Function to load rotation angle.
     *
     * @param typedArray TypedArray
     */
    private void loadRotationAngle(TypedArray typedArray) {
        mRotateViewAngle = typedArray.getInt(R.styleable.rotate_view_angle, 0);
    }

    /**
     * Function to load rotation image.
     *
     * @param typedArray TypedArray
     */
    private void loadRotationImage(TypedArray typedArray) {
        mRotateViewImage = typedArray.getResourceId(R.styleable.rotate_view_image, R.mipmap.ic_launcher);
        setRotateViewImage(mRotateViewImage);
    }

    /**
     * Function to initialize rotation mView components.
     */
    private void initializeRotationView() {
        mIvRotationImage = (ImageView) mView.findViewById(R.id.ivRotationImage);
        mRlWrapper = (RelativeLayout) mView.findViewById(R.id.rlWrapper);
        mIvRotationImage.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        mRlWrapper.setOnTouchListener(viewTouchListener);
    }

    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener =
            new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    /** Set wrapper layout size.*/
                    setWrapperSize();
                    /** Set image position in wrapper layout.*/
                    mIvRotationImage.setY(0);
                    /** Set initial hook position.*/
                    setHookPositions();
                    rotateImageByAngle(mRotateViewAngle);
                }

                /**
                 * Function to set wrapper height and width depending on image which you give for
                 * rotation.
                 */
                private void setWrapperSize() {
                    RelativeLayout.LayoutParams layoutParams =
                            (RelativeLayout.LayoutParams) mRlWrapper.getLayoutParams();
                    int viewHeight = mIvRotationImage.getHeight();
                    layoutParams.height = viewHeight * 2;
                    layoutParams.width = viewHeight * 2;
                    mRlWrapper.setLayoutParams(layoutParams);
                }
            };

    /**
     * Function to set hook positions based on height and width of the wrapper.
     */
    private void setHookPositions() {
        /** Center point from where you can calculate the rotation.*/
        mViewCenterPoint = new int[]{mRlWrapper.getWidth() / 2, mRlWrapper.getHeight() / 2};
        /** Pivot point for image from where you can rotate the image as a hinge.*/
        mPivotX = mIvRotationImage.getWidth() / 2;
        mPivotY = mIvRotationImage.getHeight() - mIvRotationImage.getWidth() / 2;
    }

    private View.OnTouchListener viewTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int position[];
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    stopViewTreeObserver();
                    position = new int[]{(int) event.getX(), (int) event.getY()};
                    rotateViewForPosition(position, mViewCenterPoint);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    position = new int[]{(int) event.getX(), (int) event.getY()};
                    rotateViewForPosition(position, mViewCenterPoint);
                    break;
            }
            return true;
        }
    };

    /**
     * Function to stop view tree observer.
     */
    private void stopViewTreeObserver() {
        if (mIvRotationImage.getViewTreeObserver().isAlive()) {
            mIvRotationImage.getViewTreeObserver()
                    .removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    /**
     * Function to rotate the mView.
     *
     * @param position    int[]
     * @param centerPoint int[]
     */
    private void rotateViewForPosition(int[] position, int[] centerPoint) {
        /** Get the angle on which the mView should rotate.*/
        double angle = getAngle(position, centerPoint);
        rotateImageByAngle(angle);
    }

    /**
     * Function to  rotate the actual image at from the pivoted point at a given angle.
     *
     * @param angle double
     */
    private void rotateImageByAngle(double angle) {
        mIvRotationImage.setPivotX(mPivotX);
        mIvRotationImage.setPivotY(mPivotY);
        mIvRotationImage.setRotation((int) angle);
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

    public int getRotateViewAngle() {
        return mRotateViewAngle;
    }

    public void setRotateViewAngle(int rotateViewAngle) {
        rotateImageByAngle(rotateViewAngle);
        this.mRotateViewAngle = rotateViewAngle;
    }

    public int getRotateViewImage() {
        return mRotateViewImage;
    }

    public void setRotateViewImage(int mRotateViewImage) {
        this.mRotateViewImage = mRotateViewImage;
        try {
            mIvRotationImage.setImageResource(mRotateViewImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
