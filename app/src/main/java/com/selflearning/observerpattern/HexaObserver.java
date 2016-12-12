package com.selflearning.observerpattern;

import android.util.Log;

public class HexaObserver implements MyObservers {
    private ConverterSubject mSubject;

    public HexaObserver(ConverterSubject subject) {
        this.mSubject = subject;
        this.mSubject.attach(this);
    }

    @Override
    public void convert(int decimalValues) {
        String convertedValue = Integer.toHexString(decimalValues);
        Log.i("", convertedValue + "");
    }
}
