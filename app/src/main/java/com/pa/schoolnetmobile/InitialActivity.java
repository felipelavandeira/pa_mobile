package com.pa.schoolnetmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.pa.schoolnetmobile.adapter.NotasFaltasAdapter;
import com.pa.schoolnetmobile.data.Disciplina;
import com.pa.schoolnetmobile.data.Nota;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InitialActivity extends AppCompatActivity {

    private ListView listaNotas;
    private NotasFaltasAdapter notasFaltasAdapter;
    private List<Disciplina> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        TextView txtView = findViewById(R.id.txt_day);
        listaNotas = findViewById(R.id.lista_faltas);
        list = new ArrayList<>();

        Nota nota = new Nota(8.6, "N1");
        Nota nota2 = new Nota(8.0, "N2");
        Disciplina disciplina = new Disciplina(1,"SIF028 - Banco de Dados", 40, 4, nota, nota2);
        list.add(disciplina);

        Nota nota3 = new Nota(8.0, "N1");
        Nota nota4 = new Nota(6.7, "N2");
        Disciplina disciplina2 = new Disciplina(2,"SIF057 - Computador e Sociedade", 40, 0, nota3, nota4);
        list.add(disciplina2);


        Nota nota5 = new Nota(8.8, "N1");
        Nota nota6 = new Nota(5.8, "N2");
        Disciplina disciplina3 = new Disciplina(3,"SIF031 - Engenharia de Software I", 40, 0, nota5, nota6);
        list.add(disciplina3);

        Nota nota7 = new Nota(8.5, "N1");
        Nota nota8 = new Nota(7.5, "N2");
        Disciplina disciplina4 = new Disciplina(4,"SIF017 - Programação para Web III", 40, 0, nota7, nota8);
        list.add(disciplina4);


        Nota nota9 = new Nota(8.5, "N1");
        Nota nota10 = new Nota(10.0, "N2");
        Disciplina disciplina5 = new Disciplina(5,"SIF011 - Linguagem de Programação III", 40, 0, nota9, nota10);
        list.add(disciplina5);

        Nota nota11 = new Nota(7.5, "N1");
        Nota nota12 = new Nota(10.0, "N2");
        Disciplina disciplina6 = new Disciplina(5,"SIF030 - Sistemas Operacionais", 40, 0, nota11, nota12);
        list.add(disciplina6);

        if (notasFaltasAdapter == null){
            notasFaltasAdapter = new NotasFaltasAdapter(InitialActivity.this, list);
            listaNotas.setAdapter(notasFaltasAdapter);
        }else{
            notasFaltasAdapter.notifyDataSetChanged();
        }

        txtView.setText(String.format("Atividades do dia %s", formatDate()));
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
