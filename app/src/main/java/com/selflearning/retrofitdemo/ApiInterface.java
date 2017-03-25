package com.selflearning.retrofitdemo;

import com.selflearning.rxandroiddemo.User;
import com.selflearning.swiftperfectdemo.DummyResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

public interface ApiInterface {
    @GET("posts")
    Observable<List<User>> getResponseFromApiCall();

    @GET("/v1/call1")
    Call<DummyResponseModel> makeFirstSwiftApiCall();
}
