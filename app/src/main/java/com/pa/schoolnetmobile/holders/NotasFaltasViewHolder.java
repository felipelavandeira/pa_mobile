package com.pa.schoolnetmobile.holders;

import android.widget.TextView;


public final class NotasFaltasViewHolder {
    private TextView nomeDisciplina;
    private TextView numFaltas;
    private TextView txtN1;
    private TextView txtN2;
    private TextView txtMedia;

    public NotasFaltasViewHolder(){}

    public NotasFaltasViewHolder(TextView nomeDisciplina, TextView numFaltas, TextView txtN1, TextView txtN2, TextView txtMedia) {
        this.nomeDisciplina = nomeDisciplina;
        this.numFaltas = numFaltas;
        this.txtN1 = txtN1;
        this.txtN2 = txtN2;
        this.txtMedia = txtMedia;
    }

    //GETTERS AND SETTERS
    public TextView getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(TextView nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public TextView getNumFaltas() {
        return numFaltas;
    }

    public void setNumFaltas(TextView numFaltas) {
        this.numFaltas = numFaltas;
    }

    public TextView getTxtN1() {
        return txtN1;
    }

    public void setTxtN1(TextView txtN1) {
        this.txtN1 = txtN1;
    }

    public TextView getTxtN2() {
        return txtN2;
    }

    public void setTxtN2(TextView txtN2) {
        this.txtN2 = txtN2;
    }

    public TextView getTxtMedia() {
        return txtMedia;
    }

    public void setTxtMedia(TextView txtMedia) {
        this.txtMedia = txtMedia;
    }
}
