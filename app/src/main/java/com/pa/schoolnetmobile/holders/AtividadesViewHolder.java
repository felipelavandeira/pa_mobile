package com.pa.schoolnetmobile.holders;

import android.widget.TextView;

public class AtividadesViewHolder {

    private TextView titulo, descricao;

    //CONSTRUTOR
    public AtividadesViewHolder() {
    }

    public AtividadesViewHolder(TextView titulo, TextView descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    //GETTERS AND SETTERS
    public TextView getTitulo() {
        return titulo;
    }

    public void setTitulo(TextView titulo) {
        this.titulo = titulo;
    }

    public TextView getDescricao() {
        return descricao;
    }

    public void setDescricao(TextView descricao) {
        this.descricao = descricao;
    }
}
