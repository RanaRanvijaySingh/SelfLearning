package com.selflearning.tddwithmvpmvvmdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.selflearning.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TddDemoActivity extends AppCompatActivity implements CommentView {

    private static final String TAG = "TDD with mvp-mvvm";
    private CommentPresenter mCommentPresenter;
    private CommentListAdapter mSimpleListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tdd_mvp_mvvm_demo);
        CommentRepoImpl commentRepo = new CommentRepoImpl(this);
        mCommentPresenter = new CommentPresenter(this, commentRepo);
        initializeComponents();
    }

    private void initializeComponents() {
        initializeRecyclerView();
        mCommentPresenter.presentComments();
    }

    private void initializeRecyclerView() {
        RecyclerView rvStrings = (RecyclerView) findViewById(R.id.rvStrings);
        rvStrings.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvStrings.setLayoutManager(layoutManager);
        mSimpleListAdapter = new CommentListAdapter(null);
        rvStrings.setAdapter(mSimpleListAdapter);
    }

    @Override
    public void setList(List<Comment> commentList) {
        mSimpleListAdapter.setList(commentList);
        mSimpleListAdapter.notifyDataSetChanged();
    }

    /**
     * First way to get objects from your view for all your Android dependent components.
     *
     * @return BufferReader
     */
    @Override
    public BufferedReader getBufferReader() {
        try {
            return new BufferedReader(new InputStreamReader(getAssets().open("jsondata"), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
