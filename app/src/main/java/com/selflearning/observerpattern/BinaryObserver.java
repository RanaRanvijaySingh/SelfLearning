package com.selflearning.observerpattern;

import android.util.Log;

public class BinaryObserver implements MyObservers {
    private ConverterSubject mSubject;

    public BinaryObserver(ConverterSubject subject) {
        this.mSubject = subject;
        this.mSubject.attach(this);
    }

    @Override
    public void convert(int decimalValues) {
        String convertedValue = Integer.toBinaryString(decimalValues);
        Log.i("", convertedValue + "");
    }
}
