package com.pa.schoolnetmobile;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.pa.schoolnetmobile.adapter.NotasFaltasAdapter;
import com.pa.schoolnetmobile.data.Disciplina;
import com.pa.schoolnetmobile.data.Nota;
import com.pa.schoolnetmobile.manager.SessionManager;
import com.pa.schoolnetmobile.requests.ApiRequest;
import com.pa.schoolnetmobile.requests.AulasApiRequest;
import com.pa.schoolnetmobile.requests.NotasFaltasApiRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class InitialActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listaNotas, listaAtividades;
    private SessionManager session;
    private final Locale locale = new Locale("pt");
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

        session = new SessionManager(this);
        session.checkLogin();

        TextView txtView = findViewById(R.id.txt_day);
        listaNotas = findViewById(R.id.lista_faltas);
        listaAtividades = findViewById(R.id.atividades);
        ProgressBar progressBarAtividades = findViewById(R.id.progressoAtividade);
        ProgressBar progressBarNotas = findViewById(R.id.progressoNotas);

        Date today = new Date();
        today.getTime();

        txtView.setText(String.format("Atividades do dia %s", extenseFormatDate(today)));

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + session.getUserDetails().get("token"));

        HashMap<String, String> params = new HashMap<>();
        params.put("data", requestFormatDate(today));
        params.put("aluno", session.getUserDetails().get("ID"));

        ApiRequest apiRequest = new AulasApiRequest(
                this,
                "https://6238780e.ngrok.io/api/aula/",
                Request.Method.POST,
                params,
                headers,
                listaAtividades,
                progressBarAtividades
        );
        apiRequest.execute();

        ApiRequest notasRequest = new NotasFaltasApiRequest(this,
                "https://6238780e.ngrok.io/api/faltas/" + session.getUserDetails().get("ID"),
                Request.Method.POST,
                null,
                headers,
                progressBarNotas,
                listaNotas,
                navView.getHeaderView(0));
        notasRequest.execute();

    }

    private String extenseFormatDate(Date today){
        DateFormat dayFormat = new SimpleDateFormat("dd", this.locale);
        DateFormat monthFormat = new SimpleDateFormat("MMMM", this.locale);
        String dayText = dayFormat.format(today);
        String monthText = monthFormat.format(today).substring(0,1).toUpperCase() + monthFormat.format(today).substring(1);
        return dayText + " de " + monthText;
    }

    private String requestFormatDate(Date today){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", this.locale);
        return dateFormat.format(today);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("INITIALACTIVITY", "Finalizando a activity e destruindo a sessão salva");
        this.session.destroySession();
    }

    @Override
    public boolean onSupportNavigateUp() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout){
            this.session.logoutUser();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
