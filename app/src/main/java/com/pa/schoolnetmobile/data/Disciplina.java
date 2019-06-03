package com.pa.schoolnetmobile.data;

import android.support.annotation.Nullable;

public class Disciplina {
    private int ID;
    private String nome;
    private Integer maxFaltas;
    private int faltas;
    private Nota n1, n2, media;

    //CONSTRUTOR
    public Disciplina(int ID, String nome, Integer maxFaltas, int faltas, @Nullable Nota n1, @Nullable Nota n2) {
        this.ID = ID;
        this.nome = nome;
        this.maxFaltas = maxFaltas;
        this.faltas = faltas;
        this.n1 = n1;
        this.n2 = n2;
        if (n1 != null && n2 != null){
            this.media = new Nota();
            this.calculaMedia(this.n1, this.n2);
        }
    }

    //GETTERS AND SETTERS
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getMaxFaltas() {
        return maxFaltas;
    }

    public void setMaxFaltas(Integer maxFaltas) {
        this.maxFaltas = maxFaltas;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public Nota getN1() {
        return n1;
    }

    public void setN1(Nota n1) {
        this.n1 = n1;
    }

    public Nota getN2() {
        return n2;
    }

    public void setN2(Nota n2) {
        this.n2 = n2;
    }

    public Nota getMedia() {
        return media;
    }

    private void calculaMedia(Nota n1, Nota n2) {
        if (n1.getNota() >= 0 && n2.getNota() >= 0){
            this.media.setNota((n1.getNota() + (n2.getNota() * 2)) / 3);
        }else{
            this.media.setNota(-1.0);
        }

        this.media.setTipoNota("M");
    }
}
