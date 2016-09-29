package com.selflearning.tddwithmvpmvvmdemo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CommentApi {
    @GET("/comments")
    Call<Comment[]> getComments();
}
