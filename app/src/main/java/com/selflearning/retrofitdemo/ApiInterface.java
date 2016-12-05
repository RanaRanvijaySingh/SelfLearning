package com.selflearning.retrofitdemo;

import com.selflearning.rxandroiddemo.User;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface ApiInterface {
    @GET("posts")
    Observable<List<User>> getResponseFromApiCall();
}
