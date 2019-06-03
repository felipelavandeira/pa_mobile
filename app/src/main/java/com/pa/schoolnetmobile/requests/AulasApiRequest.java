package com.pa.schoolnetmobile.requests;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.pa.schoolnetmobile.adapter.AtividadesAdapter;
import com.pa.schoolnetmobile.data.Atividade;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AulasApiRequest extends ApiRequest {

    private List<Atividade> list;
    private AtividadesAdapter adapter;
    @SuppressLint("StaticFieldLeak")
    private ListView listView;

    public AulasApiRequest(Context context,
                           String url,
                           int method,
                           Map<String, String> params,
                           Map<String, String> headers,
                           ListView listView,
                           ProgressBar progressBar) {
        super(context, url, method, params, headers);
        list = new ArrayList<>();
        this.listView = listView;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void executeRequest() {
        this.request = new CustomJsonArrayRequest(this.method, this.url, this.params, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() == 0){
                    Atividade atividade = new Atividade(0, "Não há atividades hoje", "");
                    list.add(atividade);
                }else{
                    for (int i = 0; i < response.length(); i++){
                        try {
                            if (response.length() > 0){
                                JSONObject json = response.getJSONObject(i);
                                Atividade atividade = new Atividade(
                                        i,
                                        json.getJSONObject("disciplina").getString("name"),
                                        json.getString("description")
                                );
                                list.add(atividade);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if (adapter == null){
                    adapter = new AtividadesAdapter(context, list);
                    listView.setAdapter(adapter);
                }else adapter.notifyDataSetChanged();

                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.e("AULASAPIREQUEST", error.networkResponse.headers.toString());
                error.printStackTrace();
            }
        }, this.headers);
        requestQueue.add(this.request);
    }
}
