package com.example.gestiondenotas;

public class Nota {
    int nota_id, actividad_id;
    double nota;

    public Nota() {
    }

    public Nota(int nota_id, int actividad_id, double nota) {
        this.nota_id = nota_id;
        this.actividad_id = actividad_id;
        this.nota = nota;
    }

    public int getNota_id() {
        return nota_id;
    }

    public void setNota_id(int nota_id) {
        this.nota_id = nota_id;
    }

    public int getActividad_id() {
        return actividad_id;
    }

    public void setActividad_id(int actividad_id) {
        this.actividad_id = actividad_id;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
