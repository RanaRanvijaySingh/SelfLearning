package com.selflearning.swiftperfectdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.selflearning.R;
import com.selflearning.retrofitdemo.ApiClient;
import com.selflearning.retrofitdemo.ApiInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.data;

public class SwiftPerfectDemoActivity extends AppCompatActivity {

    private static final String TAG = SwiftPerfectDemoActivity.class.getName();
    @BindView(R.id.buttonApiCall)
    Button mButtonApiCall;
    @BindView(R.id.textViewApiResponse)
    TextView mTextViewApiResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swift_perfect_demo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonApiCall)
    public void onClickApiCallButton1(View view) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<DummyResponseModel> responseModelCall = apiInterface.makeFirstSwiftApiCall();
        responseModelCall.enqueue(new Callback<DummyResponseModel>() {
            @Override
            public void onResponse(Call<DummyResponseModel> call, Response<DummyResponseModel> response) {
                if (response == null) {
                    Toast.makeText(SwiftPerfectDemoActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i(TAG, "onResponse: " + response.body());
                displayResponse(response.body());
            }

            @Override
            public void onFailure(Call<DummyResponseModel> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
            }
        });
    }

    private void displayResponse(DummyResponseModel dummyResponseModel) {
        String data;
        if (dummyResponseModel == null) {
            data = "In correct response";
        } else {
            data = new Gson().toJson(dummyResponseModel);
        }
        mTextViewApiResponse.setText(data);
    }
}
