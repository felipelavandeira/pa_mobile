package com.pa.schoolnetmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pa.schoolnetmobile.R;
import com.pa.schoolnetmobile.data.Disciplina;
import com.pa.schoolnetmobile.data.Nota;
import com.pa.schoolnetmobile.holders.NotasFaltasViewHolder;

import java.util.List;
import java.util.Locale;

public class NotasFaltasAdapter extends BaseAdapter {
    //verificar a necessidade da criação de um objeto Nota em Faltas, ou vice-versa
    private List<Disciplina> list;
    private LayoutInflater inflater;
    private Context context;
    private Locale locale = new Locale("pt");

    //CONSTRUTOR
    public NotasFaltasAdapter(Context context, List<Disciplina> list) {
        this.list = list;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getID();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        NotasFaltasViewHolder viewHolder;
        if (view == null){
            view = inflater.inflate(R.layout.list_faltas, null);
            viewHolder = new NotasFaltasViewHolder(
                    (TextView) view.findViewById(R.id.txtDisciplina),
                    (TextView) view.findViewById(R.id.numberFaltas),
                    (TextView) view.findViewById(R.id.n1_note),
                    (TextView) view.findViewById(R.id.n2_note),
                    (TextView) view.findViewById(R.id.media_note)
            );
            view.setTag(viewHolder);
        }else viewHolder = (NotasFaltasViewHolder) view.getTag();

        viewHolder.getNomeDisciplina().setText(list.get(i).getNome());
        viewHolder.getNumFaltas().setText(String.format("Faltas: %s", list.get(i).getFaltas()));

        if (list.get(i).getN1() != null)
            viewHolder.getTxtN1().setText(String.format(locale, "Nota 1: %.1f", list.get(i).getN1().getNota()));
        else
            viewHolder.getTxtN1().setText("Nota 1: -");

        if (list.get(i).getN2() != null)
            viewHolder.getTxtN2().setText( String.format(locale, "Nota 2: %.1f", list.get(i).getN2().getNota()));
        else
            viewHolder.getTxtN2().setText("Nota 2: -");

        if (list.get(i).getMedia() != null)
            viewHolder.getTxtMedia().setText( String.format(locale, "Média: %.1f", list.get(i).getMedia().getNota()) );
        else
            viewHolder.getTxtMedia().setText("Média: -");

        return view;
    }
}
