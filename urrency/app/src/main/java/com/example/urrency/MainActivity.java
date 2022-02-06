package com.example.urrency;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.urrency.data.CurrencyList;
import com.example.urrency.data.MainViewModel;
import com.example.urrency.databinding.ActivityMainBinding;
import com.example.urrency.utils.Constans;
import com.example.urrency.utils.JSONUtils;
import com.example.urrency.utils.NetWork;

import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView recyclerViewCurrency;
    private Adapter adapterCurrency;
    private Adapter.AdapterOnClickedItem adapterOnClickedItem;
    private MainViewModel viewModel;
    private TextView currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    public void init() {
        recyclerViewCurrency = binding.rvCurrency;
        currentDate = binding.tvTitleDate;
        recyclerViewCurrency.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        JSONObject jsonObject = NetWork.getJSONFromNetwork();
        List<CurrencyList> currencyList = JSONUtils.getCurrencyFromJSON(jsonObject);
        String date = JSONUtils.getCurrentDate(jsonObject);
        currentDate.setText(date.substring(0, 10));
        //передача данных в Converter
        adapterOnClickedItem = new Adapter.AdapterOnClickedItem() {
            @Override
            public void onAdapterClickedItem(int position) {
                Intent intent = new Intent(com.example.urrency.MainActivity.this, Converter.class);
                intent.putExtra(Constans.VALUTE, currencyList.get(position).getName());
                intent.putExtra(Constans.CODE_CURRENCY, currencyList.get(position).getCode());
                intent.putExtra(Constans.EXCHANGE_RATE, currencyList.get(position).getValue());
                intent.putExtra(Constans.CURRENT_DATE, date.substring(0, 10));
                startActivity(intent);
            }
        };
        adapterCurrency = new Adapter(currencyList, adapterOnClickedItem);
        adapterCurrency.setCurrency(currencyList);
        recyclerViewCurrency.setAdapter(adapterCurrency);


        //Сохранение состояния не реализовано
        LiveData<List<CurrencyList>> currencyFromLiveData = viewModel.getCurrencyList();
        currencyFromLiveData.observe(this, new Observer<List<CurrencyList>>() {
            @Override
            public void onChanged(@Nullable List<CurrencyList> currencyLists) {
            }
        });
    }
}
