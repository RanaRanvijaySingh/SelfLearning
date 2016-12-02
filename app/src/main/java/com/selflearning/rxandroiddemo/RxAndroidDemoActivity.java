package com.selflearning.rxandroiddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.selflearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

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
        initializeComponents();
    }

    private void initializeComponents() {
        mObservable = Observable.just("Observer attached");
    }

    Observer<String> observer = new Observer<String>() {
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
    };

    private void log(String message) {
        StringBuilder stringBuilder = new StringBuilder();
        tvData.setText(stringBuilder
                .append(tvData.getText().toString())
                .append("\n")
                .append(message));
    }

    @OnClick(R.id.btnAttachObserver)
    void onClickAttachObserverButton() {
        mSubscription = mObservable.subscribe(observer);
    }

    @OnClick(R.id.btnDetachObserver)
    void onClickDetachObserverButton() {
        if (mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
            log("mSubscription is unsubscribe");
        }
    }
}
