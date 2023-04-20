package com.study.books.services.httpservice;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.study.books.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.function.Consumer;
import java.util.function.Function;

public class VolleyService {
    private Context context;
    private RequestQueue volleyQueue;
    public VolleyService(Context context){
        this.context=context;
        volleyQueue = Volley.newRequestQueue(context);
    }
    public  void load(String url, Consumer<JSONObject> onSuccess, Consumer<Exception> onFailure, int RequestMethod) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                RequestMethod,
                url,
                null,
                response -> {
                    onSuccess.accept(response);
                },
                error -> {
                    onFailure.accept(new Exception(error.getMessage()));
                }
        );
        volleyQueue.add(jsonObjectRequest);
    }
    public  void loadGet(String url, Consumer<JSONObject> onSuccess) {
        load(url,onSuccess, this::ExceptionVolley,Request.Method.GET);
    }
    public  void loadPost(String url, Consumer<JSONObject> onSuccess) {
        load(url,onSuccess,this::ExceptionVolley,Request.Method.POST);
    }
    public void ExceptionVolley(Exception ex){
        Toast.makeText(context, "Some error occurred! Cannot fetch dog image", Toast.LENGTH_LONG).show();
        Log.e("MainActivity", "loadDogImage error: " + ex.getLocalizedMessage());
    }
}
