package com.example.urrency.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CurrencyList.class}, version = 1, exportSchema = false)
public abstract class CurrencyDataBase extends RoomDatabase {
    public static final Object LOCK = new Object();
    public static final String DATABASENAME = "currency_database";
    private static CurrencyDataBase instance;


    public static CurrencyDataBase getInstance(Context context) {
        if(instance == null) {
            synchronized (LOCK) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        CurrencyDataBase.class, CurrencyDataBase.DATABASENAME).build();
            }
        }
        return instance;
    }
    public abstract CurrencyDao currencyDao();


}
