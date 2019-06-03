package com.pa.schoolnetmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pa.schoolnetmobile.R;
import com.pa.schoolnetmobile.data.Atividade;
import com.pa.schoolnetmobile.holders.AtividadesViewHolder;

import java.util.List;

public class AtividadesAdapter extends BaseAdapter {

    private List<Atividade> list;
    private LayoutInflater layoutInflater;
    private Context context;

    //CONSTRUTOR
    public AtividadesAdapter(Context context, List<Atividade> list) {
        this.list = list;
        this.context = context;
        this.layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        AtividadesViewHolder viewHolder;
        if (view == null){
            view = layoutInflater.inflate(R.layout.list_atividades, null);
            viewHolder = new AtividadesViewHolder(
                    (TextView) view.findViewById(R.id.nomeAula),
                    (TextView) view.findViewById(R.id.assuntoAula)
            );
            view.setTag(viewHolder);
        }else viewHolder = (AtividadesViewHolder) view.getTag();

        viewHolder.getTitulo().setText(list.get(i).getTitulo());
        viewHolder.getDescricao().setText(list.get(i).getDescricao());
        return view;
    }
}
