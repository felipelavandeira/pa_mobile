package com.pa.schoolnetmobile.data;

import com.pa.schoolnetmobile.data.Disciplina;

public class Nota {
    private Double nota;
    private String tipoNota;

    public Nota() {
    }

    public Nota(Double nota, String tipoNota) {
        this.nota = nota;
        this.tipoNota = tipoNota;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(String tipoNota) {
        this.tipoNota = tipoNota;
    }
}
