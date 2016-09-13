package com.selflearning.imagerotation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.selflearning.R;

public class RotateImageDemo extends AppCompatActivity {
    private ImageView imageView;
    private AppCompatSeekBar seekbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_image_demo);
        imageView = (ImageView) findViewById(R.id.imageView);
        seekbar = (AppCompatSeekBar) findViewById(R.id.seekbar);
        seekbar.setOnSeekBarChangeListener(barChangeListener);
    }

    private SeekBar.OnSeekBarChangeListener barChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            imageView.setPivotX(imageView.getHeight());
            imageView.setPivotY(imageView.getWidth());
            imageView.setRotation(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
