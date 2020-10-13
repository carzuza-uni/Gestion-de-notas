package com.example.gestiondenotas;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class Actividad {
    int actividad_id, materia_id, corte, porcentaje;
    String nombre;
    ArrayList<Nota> notas;

    public Actividad() {
    }

    public Actividad(int actividad_id, int materia_id, int corte, int porcentaje, String nombre) {
        this.actividad_id = actividad_id;
        this.materia_id = materia_id;
        this.corte = corte; //1 = primer corte, 2 = segundo corte, 3 = tercer corte
        this.porcentaje = porcentaje;
        this.nombre = nombre;
    }

    public double calcularNotaActividades(){
        double sumaNotas = 0;
        for (Nota nota: this.getNotas()) {
            sumaNotas += nota.getNota();
        }
        double notaFinal = sumaNotas * this.calcularPorcentaje();
        return notaFinal;
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Nota> notas) {
        this.notas = notas;
    }

    public double calcularPorcentaje(){
        return (porcentaje / 100d);
    }

    public int getActividad_id() {
        return actividad_id;
    }

    public void setActividad_id(int actividad_id) {
        this.actividad_id = actividad_id;
    }

    public int getMateria_id() {
        return materia_id;
    }

    public void setMateria_id(int materia_id) {
        this.materia_id = materia_id;
    }

    public int getCorte() {
        return corte;
    }

    public void setCorte(int corte) {
        this.corte = corte;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
