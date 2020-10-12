package com.example.gestiondenotas;

public class Materia {
    int materia_id;
    String nombre;
    String descripcion;

    public Materia() {
    }

    public Materia(int materia_id, String nombre, String descripcion) {
        this.materia_id = materia_id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getMateria_id() {
        return materia_id;
    }

    public void setMateria_id(int materia_id) {
        this.materia_id = materia_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
