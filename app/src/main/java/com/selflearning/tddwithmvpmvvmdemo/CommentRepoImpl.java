package com.selflearning.tddwithmvpmvvmdemo;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommentRepoImpl implements CommentRepo {
    private final Context mContext;

    public CommentRepoImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public BufferedReader getBufferReader() {
        try {
            return new BufferedReader(new InputStreamReader(mContext.getAssets().open("jsondata"),
                    "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
