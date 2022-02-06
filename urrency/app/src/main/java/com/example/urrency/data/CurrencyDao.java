package com.example.urrency.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CurrencyDao {

    @Query("SELECT * FROM currencyList")
    LiveData<List<CurrencyList>> getCurrencyList();

    @Query("SELECT * FROM currencyList WHERE id == :currencyId")
    CurrencyList getCurrencyById(int currencyId);

    @Query("DELETE FROM currencyList")
    void deleteCurrencyList();

    @Insert
    void insertCurrencyList(CurrencyList currencyList);

    @Update
    void updateCurrencyList(CurrencyList currencyList);

}

