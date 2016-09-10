package com.selflearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.selflearning.loaderdemo.LoaderDemoActivity;
import com.selflearning.materialdesigndemo.MaterialDesignDemoActivity;
import com.selflearning.toolbardemo.ToolbarDemoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickMaterialDesignDemoButton(View view) {
        startActivity(new Intent(this, MaterialDesignDemoActivity.class));
    }

    public void onClickLoaderButton(View view) {
        startActivity(new Intent(this, LoaderDemoActivity.class));
    }

    public void onClickToolbarButton(View view) {
        startActivity(new Intent(this, ToolbarDemoActivity.class));
    }
}
