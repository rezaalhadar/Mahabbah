package com.randfiq.mahabbah.data.handler;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.randfiq.mahabbah.data.model.DataPengguna;
import com.randfiq.mahabbah.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.List;

public class DataManager implements Constant {

    private static final String TAG = "NetworkManager";

    public interface OnDataLoadedListener {
        void onDataLoaded(List<DataPengguna> items);
        void onError(String errorMessage);
    }

    public static void fetchData(Context context, final OnDataLoadedListener listener) {
        String url_GoogleScript = GAppScript_Prefix + GAppScript_DeploymentID + GAppScript_Suffix;
        String url_GoogleScriptWithAction = url_GoogleScript + "?action=" + gscriptAction_getData_Pengguna;

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url_GoogleScriptWithAction, null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("items");
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<DataPengguna>>() {}.getType();
                        List<DataPengguna> items = gson.fromJson(jsonArray.toString(), listType);
                        listener.onDataLoaded(items);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        listener.onError("Error parsing JSON");
                    }
                },
                error -> {
                    error.printStackTrace();
                    listener.onError("Error fetching data");
                });

        queue.add(request);
    }
}
