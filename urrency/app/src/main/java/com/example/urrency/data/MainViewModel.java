package com.example.urrency.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {

    private static CurrencyDataBase currencyDataBase;
    private LiveData<List<CurrencyList>> currencyList;

    public LiveData<List<CurrencyList>> getCurrencyList() {
        return currencyList;
    }

    public MainViewModel(@NonNull @NotNull Application application) {
        super(application);
        currencyDataBase = CurrencyDataBase.getInstance(getApplication());
        currencyList = currencyDataBase.currencyDao().getCurrencyList();
    }

    public CurrencyList getCurrencyItemId(int id) {
        try {
            return new GetCurrencyTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteCurrencyList() {
        new DeleteCurrencyTask().execute();
    }

    public void insertCurrencyList(CurrencyList currencyList) {
        new InsertCurrencyTask().execute(currencyList);
    }

    public void updateCurrensyList(CurrencyList currencyList) {
        new UpdateTask().execute(currencyList);
    }

    private static class GetCurrencyTask extends AsyncTask<Integer, Void, CurrencyList> {
        @Override
        protected CurrencyList doInBackground(Integer... integers) {
            if (integers != null && integers.length > 0) {
                return currencyDataBase.currencyDao().getCurrencyById(integers[0]);
            }
            return null;
        }
    }


    private static class DeleteCurrencyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... integers) {
            currencyDataBase.currencyDao().deleteCurrencyList();
            return null;
        }
    }

    private static class InsertCurrencyTask extends AsyncTask<CurrencyList, Void, Void> {

        @Override
        protected Void doInBackground(CurrencyList... currencyLists) {
            {
                if (currencyLists != null && currencyLists.length > 0) {
                    currencyDataBase.currencyDao().insertCurrencyList(currencyLists[0]);
                }
                return null;
            }
        }
    }

    private static class UpdateTask extends AsyncTask<CurrencyList, Void, Void> {

        @Override
        protected Void doInBackground(CurrencyList... currencyLists) {
            {
                if (currencyLists != null && currencyLists.length > 0) {
                    currencyDataBase.currencyDao().updateCurrencyList(currencyLists[0]);
                }
                return null;
            }
        }


    }
}
