package com.pa.schoolnetmobile.requests;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.pa.schoolnetmobile.InitialActivity;
import com.pa.schoolnetmobile.R;
import com.pa.schoolnetmobile.adapter.NotasFaltasAdapter;
import com.pa.schoolnetmobile.data.Disciplina;
import com.pa.schoolnetmobile.data.Nota;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotasFaltasApiRequest extends ApiRequest {

    private List<Disciplina> list;
    private ListView listView;
    private View menuView;
    private NotasFaltasAdapter notasFaltasAdapter;
    private TextView txtNome, txtEmail, txtCpf, txtCurso, txtConclusao;
    private ProgressBar barConclusao;

    public NotasFaltasApiRequest(Context context,
                                 String url,
                                 int method,
                                 Map<String, String> params,
                                 @Nullable Map<String, String> headers,
                                 ProgressBar progressBar,
                                 ListView listView,
                                 View menuView) {
        super(context, url, method, params, headers);
        this.progressBar = progressBar;
        this.listView = listView;
        this.menuView = menuView;

        //CONSTRUINDO A VIEW DO MENU COM AS INFORMAÇÕES DO USUÁRIO
        this.txtNome = this.menuView.findViewById(R.id.perfil_nome);
        this.txtEmail = this.menuView.findViewById(R.id.perfil_email);
        this.txtCpf = this.menuView.findViewById(R.id.perfil_cpf);
        this.txtCurso = this.menuView.findViewById(R.id.perfil_curso);
        this.txtConclusao = this.menuView.findViewById(R.id.perfil_conclusao);
        this.barConclusao = this.menuView.findViewById(R.id.perfil_progresso);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void executeRequest() {
        this.request = new CustomJsonObjectRequest(this.method, this.url, this.params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                list = new ArrayList<>();
                try {
                    JSONObject curso = response.getJSONObject("curso");
                    JSONArray disciplinas = curso.getJSONArray("disciplinas");
                    JSONArray faltas = response.getJSONArray("faltas");
                    JSONArray notas = response.getJSONArray("notas");
                    for (int i = 0; i < disciplinas.length(); i++){
                        int numFaltas = 0;
                        JSONObject JSONdisciplina = disciplinas.getJSONObject(i);

                        for (int j = 0; j < faltas.length(); j++){
                            JSONObject falta = faltas.getJSONObject(j);
                            if (falta.getInt("disciplina_id") == JSONdisciplina.getInt("id"))
                                numFaltas++;
                        }

                        Disciplina disciplina = new Disciplina(
                                JSONdisciplina.getInt("id"),
                                JSONdisciplina.getString("name"),
                                JSONdisciplina.getInt("max_absence"),
                                numFaltas,
                                null, null
                                );

                        for (int k = 0; k < notas.length(); k++){
                            JSONObject JSONnota = notas.getJSONObject(k);
                            if (JSONnota.getInt("disciplina_id") == disciplina.getID()){
                                Nota nota = new Nota(JSONnota.getDouble("grade"), JSONnota.getString("grade_sort"));
                                if (nota.getTipoNota().equals("n1"))
                                    disciplina.setN1(nota);
                                else if (nota.getTipoNota().equals("n2"))
                                    disciplina.setN2(nota);
                            }
                        }
                        Log.d("NOTASAPIREQUEST", JSONdisciplina.toString());
                        list.add(disciplina);
                    }
                    txtNome.setText(response.getString("name"));
                    txtEmail.setText(response.getString("email"));
                    txtCpf.setText(response.getString("cpf"));
                    txtConclusao.setText(response.getInt("conclusion") + "% concluído");
                    barConclusao.setProgress(response.getInt("conclusion"));
                    txtCurso.setText(curso.getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (notasFaltasAdapter == null){
                    notasFaltasAdapter = new NotasFaltasAdapter(context, list);
                    listView.setAdapter(notasFaltasAdapter);
                }else{
                    notasFaltasAdapter.notifyDataSetChanged();
                }
                getProgressBar().setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOGINAPIREQUEST", error.toString());
                getProgressBar().setVisibility(View.GONE);
            }
        }, this.headers);
        requestQueue.add(this.request);
    }
}
