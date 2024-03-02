package com.example.foodpos_counter.db.Entity;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DataConverter {
    @TypeConverter
    public static ArrayList<Add_ons_cart> fromString(String value) {
        Type listType = new TypeToken<ArrayList<Add_ons_cart>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Add_ons_cart> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
