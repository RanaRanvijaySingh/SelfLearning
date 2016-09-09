package com.selflearning;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.selflearning.materialdesigndemo.MaterialDesignDemo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Function is called on click of material design.
     *
     * @param view View
     */
    public void onClickMaterialDesignDemoButton(View view) {
        startActivity(new Intent(this, MaterialDesignDemo.class));
    }
}
