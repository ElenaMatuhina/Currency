package com.example.urrency.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "currencyList")
public class CurrencyList {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String code;
    private String value;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}

