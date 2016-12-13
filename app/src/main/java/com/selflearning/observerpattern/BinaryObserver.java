package com.selflearning.observerpattern;

public class BinaryObserver implements MyObservers {
    private ConverterSubject mSubject;

    public BinaryObserver(ConverterSubject subject) {
        this.mSubject = subject;
        this.mSubject.attach(this);
    }

    @Override
    public void convert(int decimalValues) {
        String convertedValue = Integer.toBinaryString(decimalValues);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Binary : ").append(convertedValue);
        mSubject.onConverted(stringBuilder.toString());
    }
}
