package com.selflearning.loaderdemo;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.selflearning.R;

import java.util.Arrays;
import java.util.List;

public class StringsLoader extends AsyncTaskLoader<List<String>> {
    public StringsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<String> loadInBackground() {
        List<String> data = Arrays.asList(getContext().getResources().getStringArray(R.array
                .string_list));
        return data;
    }

    @Override
    public void deliverResult(List<String> data) {
        super.deliverResult(data);
    }
}
