package com.pa.schoolnetmobile.requests;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.pa.schoolnetmobile.manager.SessionManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class ApiRequest extends AsyncTask<Object, Object, Object> {
    RequestQueue requestQueue;
    String url;
    Request request;
    int method;
    Map<String, String> params;
    Context context;
    ProgressBar progressBar;
    SessionManager session;
    Map<String, String> headers;

    public ApiRequest(Context context, String url, int method, Map<String, String> params, @Nullable Map<String, String> headers){
        this.context = context;
        this.url = url;
        this.method = method;
        this.params = params;
        this.headers = headers;
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

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public SessionManager getSession() {
        return session;
    }

    public void setSession(SessionManager session) {
        this.session = session;
    }

    //MÉTODOS
    protected void executeRequest(){
        this.request = new CustomJsonObjectRequest(this.method, this.url, this.params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("APIREQUEST", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("APIREQUEST", error.getMessage());
            }
        }, null);
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
