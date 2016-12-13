package com.selflearning.observerpattern;

public class HexObserver implements MyObservers {
    private ConverterSubject mSubject;

    public HexObserver(ConverterSubject subject) {
        this.mSubject = subject;
        this.mSubject.attach(this);
    }

    @Override
    public void convert(int decimalValues) {
        String convertedValue = Integer.toHexString(decimalValues);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hex : ").append(convertedValue);
        mSubject.onConverted(stringBuilder.toString());
    }
}
