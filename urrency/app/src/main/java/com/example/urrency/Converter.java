package com.example.urrency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.example.urrency.databinding.ActivityConverterBinding;
import com.example.urrency.utils.Constans;

public class Converter extends AppCompatActivity {

    private ActivityConverterBinding binding;
    private TextView currentDate;
    private TextView currentRateEx;
    private EditText sumRub;
    private TextView currencyCode;
    private TextView currencySumResult;
    private String rateEx;
    private String nominal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConverterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        getIntentCurrency();
        onSumChanged();
    }

    public void init() {
        currencyCode = binding.tvCurrency;
        currentDate = binding.currentDate;
        currentRateEx = binding.currentRateEx;
        sumRub = binding.tvSumChange;
        sumRub.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        currencySumResult = binding.tvResultCurrency;
    }

    private void getIntentCurrency() {
        Intent intent = getIntent();
        currentDate.setText(intent.getStringExtra(Constans.CURRENT_DATE));
        rateEx = intent.getStringExtra(Constans.EXCHANGE_RATE);
        nominal = intent.getStringExtra(Constans.NOMINAL);
        String rateExResult = rateEx + " RUB";
        currentRateEx.setText(rateExResult);
        currencyCode.setText(intent.getStringExtra(Constans.CODE_CURRENCY));

    }

    private void onSumChanged() {
        sumRub.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                double rate = Double.parseDouble(rateEx);
                double nominalValue = Double.parseDouble(nominal);
                try {
                    if(sumRub.getText().toString().trim().length() == 0) {
                        currencySumResult.setText(R.string.empty);
                    } else {
                        double sumEdit = Double.parseDouble(sumRub.getText().toString());
                        double result = sumEdit/rate*nominalValue;
                        String sumResult = new DecimalFormat("#0.00").format(result);
                        currencySumResult.setText(sumResult);
                    }
                }
                catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

