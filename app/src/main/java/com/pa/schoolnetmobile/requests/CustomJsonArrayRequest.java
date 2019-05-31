package com.pa.schoolnetmobile.requests;

import android.support.annotation.Nullable;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class CustomJsonArrayRequest extends Request<JSONArray> {

    private Map<String, String> params;
    private Response.Listener<JSONArray> response;

    //CONSTRUTOR
    CustomJsonArrayRequest(int method, String url, @Nullable Map<String, String> params, Response.Listener<JSONArray> response, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.params = params;
        this.response = response;
    }

    //GETTERS AND SETTERS
    @Override
    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Response.Listener<JSONArray> getResponse() {
        return response;
    }

    public void setResponse(Response.Listener<JSONArray> response) {
        this.response = response;
    }

    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        String js = null;
        try {
            js = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return(Response.success(new JSONArray(js), HttpHeaderParser.parseCacheHeaders(response)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void deliverResponse(JSONArray response) {
        this.response.onResponse(response);
    }

    public static Cache.Entry parseIgnoreCacheHeaders(NetworkResponse response){
        long now = System.currentTimeMillis();

        Map<String, String> headers = response.headers;
        long serverDate = 0;
        String serverEtag = null;
        String headerValue;

        headerValue = headers.get("Date");
        if (headerValue != null){
            serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
        }

        serverEtag = headers.get("ETag");

        final long cacheHitButRefreshed = 3 * 60 * 1000;
        final long cacheExpired = 24 * 60 * 1000;
        final long softExpire = now + cacheHitButRefreshed;
        final long ttl = now + cacheExpired;

        Cache.Entry entry = new Cache.Entry();
        entry.data = response.data;
        entry.etag = serverEtag;
        entry.softTtl = softExpire;
        entry.ttl = ttl;
        entry.serverDate = serverDate;
        entry.responseHeaders = headers;

        return entry;
    }
}
