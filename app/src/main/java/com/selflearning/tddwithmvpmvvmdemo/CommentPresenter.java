package com.selflearning.tddwithmvpmvvmdemo;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class CommentPresenter {
    private final CommentView mCommentView;
    private final CommentRepoImpl mCommentRepo;

    CommentPresenter(CommentView commentView, CommentRepoImpl commentRepo) {
        this.mCommentView = commentView;
        this.mCommentRepo = commentRepo;
        fetchCommentsFromApi();
    }

    void presentComments() {
        mCommentView.setList(getListData());
    }

    /**
     * Function to get the list of comments from Asserts.
     *
     * @return List<Comments>
     */
    private List<Comment> getListData() {
        /** First way to get android dependent components */
//        BufferedReader bufferedReader = mCommentView.getBufferReader();
        /** Second way to get android dependent components */
        BufferedReader bufferedReader = mCommentRepo.getBufferReader();
        if (bufferedReader == null) {
            return null;
        }
        String line;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getCommentList(stringBuffer.toString());
    }

    /**
     * Function to get comment list from given comment json.
     *
     * @param commentJson String
     * @return List<String>
     */
    private List<Comment> getCommentList(String commentJson) {
        if (commentJson == null) {
            return new ArrayList<>();
        }
        Comment[] comments = new Gson().fromJson(commentJson, Comment[].class);
        return Arrays.asList(comments);
    }

    public void fetchCommentsFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CommentApi commentApi = retrofit.create(CommentApi.class);
        Call<Comment[]> commentCall = commentApi.getComments();
        commentCall.enqueue(callBackComment);
    }

    private Callback callBackComment = new Callback() {
        @Override
        public void onResponse(Call call, Response response) {
            Log.i("", response.toString());
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            Log.i("", t.toString());
        }
    };
}
