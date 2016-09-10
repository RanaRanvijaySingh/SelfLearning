package com.selflearning.loaderdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.selflearning.R;

import java.util.Collections;
import java.util.List;

public class LoaderDemoActivity extends AppCompatActivity implements LoaderCallbacks<List<String>> {
    private LoaderAdapter loaderAdpater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader_demo);
        initializeComponents();
    }

    private void initializeComponents() {
        ListView lvStrings = (ListView) findViewById(R.id.lvStrings);
        loaderAdpater = new LoaderAdapter(this);
        lvStrings.setAdapter(loaderAdpater);
        getSupportLoaderManager().initLoader(R.id.loader_ids, null, this);
    }

    @Override
    public Loader<List<String>> onCreateLoader(int id, Bundle args) {
        return new StringsLoader(getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
        loaderAdpater.setList(data);
    }

    @Override
    public void onLoaderReset(Loader<List<String>> loader) {
        loaderAdpater.setList(Collections.<String>emptyList());
    }
}
