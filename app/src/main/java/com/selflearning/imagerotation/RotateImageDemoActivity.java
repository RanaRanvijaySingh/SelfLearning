package com.selflearning.imagerotation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.selflearning.R;

public class RotateImageDemoActivity extends AppCompatActivity {
    private ImageView imageView;
    private AppCompatSeekBar seekbar;

    private double x;
    private double y;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_image_demo);
        imageView = (ImageView) findViewById(R.id.imageView);
        seekbar = (AppCompatSeekBar) findViewById(R.id.seekbar);
        seekbar.setOnSeekBarChangeListener(barChangeListener);
        imageView.setOnTouchListener(onTouchListener);
    }

    private double dx;
    private double dy;
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int eid = event.getAction();
            switch (eid) {
                case MotionEvent.ACTION_MOVE:
                    rotateImage(getAngle(event.getX()-dx, event.getY()-dy));
                    break;
                case MotionEvent.ACTION_DOWN:
                    x = event.getX();
                    y = event.getY();
                    dx = x-imageView.getX();
                    dy = y-imageView.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    public int getAngle(double x, double y) {
        double imageX = imageView.getX() + imageView.getWidth() / 2;
        double imageY = imageView.getY() + imageView.getHeight() / 2;
        double perpendicular = y - imageY;
        double base = x - imageX;
        double theta = Math.atan2(perpendicular, base);
        double degree = Math.toDegrees(theta);
        //Check in which quad point is lying
        if (perpendicular < 0 && base >= 0) {
            //Lies in Fourth quad
            degree += 270;
        } else if (perpendicular >= 0 && base < 0) {
            //Lies in Second quad
            degree += 90;
        } else if (perpendicular < 0 && base < 0) {
            //Lies in Third quad
            degree += 180;
        } else {
            //Lies in first quad
        }
        Log.i("", "Angle : " + (int) degree);
        return (int) degree;
    }

    private SeekBar.OnSeekBarChangeListener barChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            rotateImage(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void rotateImage(int progress) {
//        imageView.setPivotX(imageView.getHeight());
//        imageView.setPivotY(imageView.getWidth());
        imageView.setRotation(progress);
    }


/*
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            double r = Math.atan2(event.getX() - imageView.getWidth(), imageView.getHeight() - event.getY());
            int rotation = (int) Math.toDegrees(r);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    x = event.getX();
                    y = event.getY();
                    updateRotation(rotation);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }//switch
            return true;
        }//onTouch
    };

    private void updateRotation(double rot) {
        float newRot = new Float(rot);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Matrix matrix = new Matrix();
        matrix.postRotate(newRot, bitmap.getWidth(), bitmap.getHeight());
        if (y > 250) {
            Bitmap reDrawnBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            imageView.setImageBitmap(reDrawnBitmap);
        } else {
            Bitmap reDrawnBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            imageView.setImageBitmap(reDrawnBitmap);
        }
    }
*/

}
