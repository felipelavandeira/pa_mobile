package com.pa.schoolnetmobile.requests;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.pa.schoolnetmobile.manager.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class LoginApiRequest extends ApiRequest {

    private ProgressBar progressBar;
    private SessionManager session;

    public LoginApiRequest(Context context, String url, int method, Map<String, String> params, ProgressBar progressBar) {
        super(context, url, method, params);
        this.progressBar = progressBar;
    }

    public LoginApiRequest(Context context, String url, int method, ProgressBar progressBar) {
        super(context, url, method);
        this.progressBar = progressBar;
    }

    //GETTERS AND SETTERS
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

    protected void executeRequest(){
        this.request = new CustomJsonObjectRequest(this.method, this.url, this.params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                setResponse(response);
                try {
                    SessionManager session = new SessionManager(getContext());
                    session.createLoginSession(
                            Integer.parseInt(getParams().get("ra")),
                            getResponse().getJSONObject("success").getString("token")
                    );
                    Log.d("LOGINAPIREQUEST", session.getUserDetails().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("LOGINAPIREQUEST", e.getMessage());
                }
                getProgressBar().setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOGINAPIREQUEST", error.toString());
                if (error.toString().equals("com.android.volley.AuthFailureError")){
                    Toast.makeText(getContext(), "RA e/ou senha incorreto(s). Tente novamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Houve um erro ao tentar realizar o login, por favor, tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                }
                getProgressBar().setVisibility(View.GONE);
            }
        });
        requestQueue.add(this.request);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
}
