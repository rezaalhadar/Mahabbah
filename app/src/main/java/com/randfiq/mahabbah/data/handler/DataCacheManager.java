package com.randfiq.mahabbah.data.handler;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.randfiq.mahabbah.data.model.DataPengguna;

import java.lang.reflect.Type;
import java.util.List;

public class DataCacheManager {
    private static final String PREFS_NAME = "DataCachePrefs";
    private static final String KEY_JSON_DATA = "json_data";

    public static void cacheData(Context context, List<DataPengguna> items) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonData = gson.toJson(items);
        editor.putString(KEY_JSON_DATA, jsonData);
        editor.apply();
    }

    public static List<DataPengguna> getCachedData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String jsonData = sharedPreferences.getString(KEY_JSON_DATA, null);
        Gson gson = new Gson();
        Type listType = new TypeToken<List<DataPengguna>>() {}.getType();
        return gson.fromJson(jsonData, listType);
    }

    public static void clearCachedData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_JSON_DATA);
        editor.apply();
    }
}