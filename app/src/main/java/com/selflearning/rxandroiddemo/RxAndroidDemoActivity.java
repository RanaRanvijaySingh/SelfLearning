package com.selflearning.rxandroiddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.selflearning.R;
import com.selflearning.retrofitdemo.ApiClient;
import com.selflearning.retrofitdemo.ApiInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RxAndroidDemoActivity extends AppCompatActivity {

    @BindView(R.id.tvData)
    TextView tvData;
    private Observable<String> mObservable;
    private Subscription mSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_android_demo);
        ButterKnife.bind(this);
    }

    private void log(String message) {
        StringBuilder stringBuilder = new StringBuilder();
        tvData.setText(stringBuilder
                .append(tvData.getText().toString())
                .append("\n")
                .append(message));
    }

    @OnClick(R.id.btnAttachObserver)
    void onClickAttachObserverButton() {
        mObservable = Observable.just("Observer attached");
        mSubscription = mObservable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                log("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                log("onError" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                log(s);
            }
        });
    }

    @OnClick(R.id.btnDetachObserver)
    void onClickDetachObserverButton() {
        if (mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
            log("mSubscription is unsubscribe");
        }
    }

    @OnClick(R.id.btnUsingOperatorsExample)
    void onClickUsingOperators() {
        Observable<Integer> observable = Observable.from(new Integer[]{1, 2, 3, 4, 5, 6});
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                log("call invoked " + integer);
            }
        });
    }

    @OnClick(R.id.btnAsynchronousExample)
    void onClickAsynchronousJob() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Observable<List<User>> observable = apiInterface.getResponseFromApiCall();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<User>>() {
                    @Override
                    public void call(List<User> users) {
                        String jsonData = new Gson().toJson(users);
                        log(jsonData);
                    }
                });
    }
}
