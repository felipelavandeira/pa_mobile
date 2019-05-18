package com.pa.schoolnetmobile.requests;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;

public class ApiRequest {
    private RequestQueue requestQueue;
    private String url;
    private JsonArrayRequest jsonArrayRequest;
    private int method;
    private JSONArray response;
    private Response.Listener<JSONArray> responseListener;
    private Response.ErrorListener errorListener;
    private JSONArray params = null;

    public ApiRequest(Context context, String url, int method, JSONArray params){
        this.url = url;
        this.method = method;
        this.params = params;
        requestQueue = Volley.newRequestQueue(context);
    }

    //GETTERS AND SETTERS
    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JsonArrayRequest getJsonArrayRequest() {
        return jsonArrayRequest;
    }

    public void setJsonArrayRequest(JsonArrayRequest jsonArrayRequest) {
        this.jsonArrayRequest = jsonArrayRequest;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public JSONArray getResponse() {
        return response;
    }

    private void setResponse(JSONArray response) {
        this.response = response;
    }

    public Response.Listener<JSONArray> getResponseListener() {
        return responseListener;
    }

    public void setResponseListener(Response.Listener<JSONArray> responseListener) {
        this.responseListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                setResponse(response);
            }
        };
    }

    public Response.ErrorListener getErrorListener() {
        return errorListener;
    }

    public void setErrorListener(Response.ErrorListener errorListener) {
        this.errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("API-REQUEST", error.getMessage());
            }
        };
    }

    //MÉTODOS
    public void executeRequest(Request<Object> request){
        jsonArrayRequest = new JsonArrayRequest(this.method, this.url, this.params, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                setResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("API-REQUEST", error.getMessage());
            }
        });
        requestQueue.add(this.jsonArrayRequest);
    }

}
