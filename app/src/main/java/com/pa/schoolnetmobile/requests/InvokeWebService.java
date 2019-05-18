package com.pa.schoolnetmobile.requests;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;

public class InvokeWebService extends AsyncTask<Object, Object, String> {

    private ApiRequest apiRequest;
    private Request<Object> request;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Object... objects) {
        return null;
    }

    //Chamada ao web service de maneira assíncrona
    public void callWebService(){
        if (this.apiRequest != null){
            this.apiRequest.executeRequest(this.request);
        }
    }
}
