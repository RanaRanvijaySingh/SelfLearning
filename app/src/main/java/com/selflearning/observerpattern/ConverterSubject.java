package com.selflearning.observerpattern;

import java.util.ArrayList;
import java.util.List;

public class ConverterSubject {

    private List<MyObservers> myObserversList = new ArrayList<>();
    private int mDecimalValues;
    private OnConvertedListener mOnConvertListener;

    ConverterSubject(OnConvertedListener onConvertListener){
        this.mOnConvertListener = onConvertListener;
    }

    public void convert(int decimalValue) {
        this.mDecimalValues = decimalValue;
        notifyAllObservers();
    }

    private void notifyAllObservers() {
        for (MyObservers myObservers : myObserversList) {
            myObservers.convert(mDecimalValues);
        }
    }

    public void attach(MyObservers observer) {
        myObserversList.add(observer);
    }

    public void onConverted(String convertedValue) {
        mOnConvertListener.onConvert(convertedValue);
    }
}
