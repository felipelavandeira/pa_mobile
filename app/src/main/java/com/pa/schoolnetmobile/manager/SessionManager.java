package com.pa.schoolnetmobile.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.pa.schoolnetmobile.InitialActivity;
import com.pa.schoolnetmobile.MainActivity;

import java.util.HashMap;

public class SessionManager {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    //Nome do arquivo de preferências
    private static final String PREFERENCE_NAME = "Schoolnet";
    private static final String IS_LOGIN = "LoggedIn";
    private static String KEY_ID = "id", KEY_TOKEN = "token";

    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
        this.context = context;
        int PRIVATE_MODE = 0;
        this.preferences = context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        this.editor = preferences.edit();
    }

    //MÉTODOS

    //CRIA A SESSÃO DE LOGIN
    public void createLoginSession(int id, String token){
        //Setando login como TRUE
        editor.putBoolean(IS_LOGIN, true);
        //Gravando os dados na preferência
        editor.putInt(KEY_ID, id);
        editor.putString(KEY_TOKEN, token);
        editor.commit();
        Intent intent = new Intent(this.context, InitialActivity.class);
        this.context.startActivity(intent);
    }

    //VERIFICA SE O USUÁRIO ESTÁ LOGADO
    public void checkLogin(){
        if (context.getClass() == MainActivity.class){
            this.logoutUser();
            return;
        }

        if (!this.isLoggedIn()){
            //Usuário não está logado, enviando ele para a tela de login
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    //RETORNA AS INFORMAÇÕES DO USUÁRIO
    //RA E TOKEN DE LOGIN
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put("ID", String.valueOf(preferences.getInt(KEY_ID, 0)));
        user.put("token", preferences.getString(KEY_TOKEN, null));
        return user;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();

        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    private boolean isLoggedIn(){
        return preferences.getBoolean(IS_LOGIN, false);
    }
}
