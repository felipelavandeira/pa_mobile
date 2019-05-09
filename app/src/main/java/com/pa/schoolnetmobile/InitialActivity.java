package com.pa.schoolnetmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.lang.annotation.Annotation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        TextView txtView = findViewById(R.id.txt_day);
        txtView.setText(String.format("Atividades do dia %s", formatDate()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://servicodados.ibge.gov.br/api/v2/censos/nomes/ranking/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call call = apiInterface.getNomes();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("TESTE", response.body().toString());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("TESTE", t.toString());
            }
        });

        Log.d("INITIAL", retrofit.toString());
    }

    private String formatDate(){
        Date today = new Date();
        today.getTime();
        DateFormat dayFormat = new SimpleDateFormat("dd");
        DateFormat monthFormat = new SimpleDateFormat("MMMM");
        String dayText = dayFormat.format(today);
        String monthText = monthFormat.format(today).substring(0,1).toUpperCase() + monthFormat.format(today).substring(1);
        return dayText + " de " + monthText;
    }
}
