package com.selflearning.localizationdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.selflearning.R;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocalizationDemoActivity extends AppCompatActivity {

    private static final double HUNDRED_RUPEES = 100;
    @BindView(R.id.textViewCurrency)
    TextView textViewCurrency;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localization_demo);
        ButterKnife.bind(this);
        setLocalCurrency();
    }

    private void setLocalCurrency() {
        //Change "INR" with the values you need.
        String localCurrency = getFormattedCurrencyString("INR", HUNDRED_RUPEES);
        textViewCurrency.setText(localCurrency);
    }

    public static String getFormattedCurrencyString(String isoCurrencyCode, double amount) {
        // This formats currency values as the user expects to read them (default locale).
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        // This specifies the actual currency that the value is in, and provides the currency symbol.
        Currency currency = Currency.getInstance(isoCurrencyCode);
        // Our fix is to use the US locale as default for the symbol, unless the currency is USD
        // and the locale is NOT the US, in which case we know it should be US$.
        String symbol;
        if (isoCurrencyCode.equalsIgnoreCase("usd") && !Locale.getDefault().equals(Locale.US)) {
            symbol = "US$";
        } else {
            symbol = currency.getSymbol(Locale.US); // US locale has the best symbol formatting table.
        }
        // We then tell our formatter to use this symbol.
        DecimalFormatSymbols decimalFormatSymbols = ((java.text.DecimalFormat) currencyFormat).getDecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol(symbol);
        ((java.text.DecimalFormat) currencyFormat).setDecimalFormatSymbols(decimalFormatSymbols);
        return currencyFormat.format(amount);
    }
}
