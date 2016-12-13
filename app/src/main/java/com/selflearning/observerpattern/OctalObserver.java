package com.selflearning.observerpattern;

public class OctalObserver implements MyObservers {
    private ConverterSubject mSubject;

    public OctalObserver(ConverterSubject subject) {
        this.mSubject = subject;
        this.mSubject.attach(this);
    }

    @Override
    public void convert(int decimalValues) {
        String convertedValue = Integer.toOctalString(decimalValues);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Octal : ").append(convertedValue);
        mSubject.onConverted(stringBuilder.toString());
    }
}
