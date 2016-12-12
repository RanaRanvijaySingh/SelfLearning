package com.selflearning.observerpattern;

import android.util.Log;

public class OctalObserver implements MyObservers {
    private ConverterSubject mSubject;

    public OctalObserver(ConverterSubject subject) {
        this.mSubject = subject;
        this.mSubject.attach(this);
    }

    @Override
    public void convert(int decimalValues) {
        String convertedValue = Integer.toOctalString(decimalValues);
        Log.i("", convertedValue + "");
        mSubject.onConverted(convertedValue);
    }
}
