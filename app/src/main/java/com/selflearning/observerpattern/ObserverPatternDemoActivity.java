package com.selflearning.observerpattern;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import com.selflearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ObserverPatternDemoActivity extends AppCompatActivity {

    @BindView(R.id.etDecimalValue)
    EditText etDecimalValue;
    private ConverterSubject mConververterSubject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer_pattern);
        ButterKnife.bind(this);
        initializeComponents();
    }

    private void initializeComponents() {
        mConververterSubject = new ConverterSubject();
        new BinaryObserver(mConververterSubject);
        new OctalObserver(mConververterSubject);
        new HexaObserver(mConververterSubject);
    }

    @OnClick(R.id.btnConvert)
    void onClickConvertButton() {
        String strDecimalValue = etDecimalValue.getText().toString();
        if (TextUtils.isEmpty(strDecimalValue)) {
            return;
        }
        try {
            int decimalValue = Integer.parseInt(strDecimalValue);
            mConververterSubject.convert(decimalValue);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
