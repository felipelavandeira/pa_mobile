package com.pa.schoolnetmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        TextView txtView = findViewById(R.id.txt_day);
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
