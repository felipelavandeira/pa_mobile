package com.pa.schoolnetmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.android.volley.Request;
import com.pa.schoolnetmobile.manager.SessionManager;
import com.pa.schoolnetmobile.requests.ApiRequest;
import com.pa.schoolnetmobile.requests.LoginApiRequest;
import java.util.HashMap;

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

        //CASO EXISTA QUALQUER SESSÃO SALVA DE LOGINS ANTERIORES
        //A SESSÃO SERÁ DESTRUÍDA
        SessionManager session = new SessionManager(this);
        session.destroySession();
    }

    public void login(View view) {
        HashMap<String, String> params = new HashMap<>();
        params.put("ra", String.valueOf(etRa.getText()));
        params.put("password", String.valueOf(etSenha.getText()));
        ApiRequest apiRequest = new LoginApiRequest(
                this,
                "https://6238780e.ngrok.io/api/login/",
                Request.Method.POST,
                params,
                progressBar);
        apiRequest.execute();
    }
}
