package com.pa.schoolnetmobile.requests;

import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class CustomJsonObjectRequest extends Request<JSONObject> {

    private Response.Listener<JSONObject> response;
    private Map<String, String> params;
    private Map<String, String> headers;

    //CONSTRUTOR
    CustomJsonObjectRequest(int method,
                            String url,
                            @Nullable Map<String, String> params,
                            Response.Listener<JSONObject> response,
                            Response.ErrorListener errorListener,
                            @Nullable Map<String, String> headers) {
        super(method, url, errorListener);
        this.params = params;
        this.response = response;
        this.headers = headers;
    }

    //GETTERS AND SETTERS
    public Response.Listener<JSONObject> getResponse() {
        return response;
    }

    public void setResponse(Response.Listener<JSONObject> response) {
        this.response = response;
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String js = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return (Response.success(new JSONObject(js), HttpHeaderParser.parseCacheHeaders(response)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        this.response.onResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (this.headers != null)
            return this.headers;
        else
            return super.getHeaders();
    }
}
