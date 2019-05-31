package com.pa.schoolnetmobile;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.toolbox.JsonArrayRequest;
import com.pa.schoolnetmobile.manager.SessionManager;
import com.pa.schoolnetmobile.requests.ApiRequest;
import com.pa.schoolnetmobile.requests.LoginApiRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

    private EditText etRa, etSenha;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SETANDO AS VIEWS QUE SERÃO UTILIZADAS
        this.etRa = findViewById(R.id.etRA);
        this.etSenha = findViewById(R.id.etPassword);
        this.progressBar = findViewById(R.id.progressBar);

        //VERIFICANDO SE JÁ EXISTE UM USUÁRIO LOGADO
        //CASO EXISTA, A SESSÃO SERÁ DESTRUÍDA
        SessionManager session = new SessionManager(this);
    }

    public void login(View view) {
        HashMap<String, String> params = new HashMap<>();
        params.put("ra", String.valueOf(etRa.getText()));
        params.put("password", String.valueOf(etSenha.getText()));
        ApiRequest apiRequest = new LoginApiRequest(
                this,
                "https://9160ea2b.ngrok.io/api/login",
                JsonArrayRequest.Method.POST,
                params,
                progressBar);
        apiRequest.execute();
    }
}
