package com.pa.schoolnetmobile.requests;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class ApiRequest extends AsyncTask<Object, Object, Object> {
    RequestQueue requestQueue;
    String url;
    Request request;
    int method;
    private JSONObject response;
    Map<String, String> params;
    Context context;

    public ApiRequest(Context context, String url, int method, Map<String, String> params){
        this.context = context;
        this.url = url;
        this.method = method;
        this.params = params;
        requestQueue = Volley.newRequestQueue(context);
    }

    public ApiRequest(Context context, String url, int method){
        this.context = context;
        this.url = url;
        this.method = method;
        this.params = null;
        requestQueue = Volley.newRequestQueue(context);
    }


    //GETTERS AND SETTERS

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public JSONObject getResponse() {
        return response;
    }

    public void setResponse(JSONObject response) {
        this.response = response;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    //MÉTODOS
    protected void executeRequest(){
        this.request = new CustomJsonObjectRequest(this.method, this.url, this.params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                setResponse(response);
                Log.d("APIREQUEST", getResponse().toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("APIREQUEST", error.getMessage());
            }
        });
        requestQueue.add(this.request);
    }


    @Override
    protected String doInBackground(Object... objects) {
        executeRequest();
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
}
