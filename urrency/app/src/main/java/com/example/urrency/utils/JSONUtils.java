package com.example.urrency.utils;

//класс для работы с JSON



import com.example.urrency.data.CurrencyList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JSONUtils {

    public static ArrayList<CurrencyList> getCurrencyFromJSON(JSONObject jsonObject) {
        ArrayList<CurrencyList> currencyList = new ArrayList<>();
        if (jsonObject == null) {
            return currencyList;
        }
        try {
            JSONObject jsonObj = new JSONObject(String.valueOf(jsonObject));
            JSONObject valute = jsonObj.getJSONObject(Constans.VALUTE);
            JSONArray keys = valute.names();
            ArrayList<String> total = new ArrayList<>();

            for (int i = 0; i < keys.length(); i++) {
                String key = keys.getString(i);
                String valueC = valute.getString(key);
                total.add(valueC);
            }

            Map<String, String> map = new HashMap<String, String>();
            ArrayList<String> filterKey = new ArrayList<>();
            ArrayList<String> filterCode = new ArrayList<>();
            ArrayList<String> filterValue = new ArrayList<>();
            ArrayList<String> filterNominal= new ArrayList<>();
            for (String item : total) {
                String[] pairs = item.split(",");
                for (int i = 0; i < pairs.length; i++) {
                    String pair = pairs[i];
                    String[] keyValue = pair.split(":");
                    map.put(keyValue[0], keyValue[1]);
                }

                Map filteredMap = map.entrySet()
                        .stream().filter(x -> x.getKey().equals("\"Name\""))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                Map filteredCode = map.entrySet()
                        .stream().filter(x -> x.getKey().equals("\"CharCode\""))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                Map filteredValue = map.entrySet()
                        .stream().filter(x -> x.getKey().equals("\"Value\""))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                Map filteredNominal = map.entrySet()
                        .stream().filter(x -> x.getKey().equals("\"Nominal\""))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                filteredMap.forEach((keyFil, valueFil) -> filterKey.add((String) valueFil));
                filteredCode.forEach((keyFil, valueFil) -> filterCode.add((String) valueFil));
                filteredValue.forEach((keyFil, valueFil) -> filterValue.add((String) valueFil));
                filteredNominal.forEach((keyFil, valueFil) -> filterNominal.add((String) valueFil));
            }

            for(int i = 0; i < filterKey.size(); i++) {
                CurrencyList currency = new CurrencyList();
                currency.setName(filterKey.get(i).replace("\"", ""));
                currency.setCode(filterCode.get(i).replace("\"", ""));
                currency.setValue(filterValue.get(i));
                currency.setNominal(filterNominal.get(i));
                currencyList.add(currency);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return currencyList;
    }

    public static String getCurrentDate(JSONObject jsonObject) {
        String date = "";
        if (jsonObject == null) {
            return date;
        }
        try {
            JSONObject jsonObj = new JSONObject(String.valueOf(jsonObject));
            date = jsonObj.getString(Constans.CURRENT_DATE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return date;
    }

}

