package com.pa.schoolnetmobile.data;

public class Atividade {
    private int ID;
    private String titulo, descricao;

    public Atividade(int ID, String titulo, String descricao) {
        this.ID = ID;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    //GETTERS AND SETTERS
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
