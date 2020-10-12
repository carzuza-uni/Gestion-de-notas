package com.example.gestiondenotas;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DaoActividad {
    SQLiteDatabase database;
    ArrayList<Actividad> listaActividades = new ArrayList<Actividad>();
    Actividad actividad;
    Context context;
    String nombreBD = "notasBD";
    String nombreTabla = "actividad";
    String tabla = "CREATE TABLE IF NOT EXISTS "+ nombreTabla +" (actividad_id INTEGER PRIMARY KEY AUTOINCREMENT, materia_id INTEGER, corte INTEGER, porcentaje INTEGER, nombre TEXT)";

    public DaoActividad(Context context) {
        this.context = context;
        this.database = context.openOrCreateDatabase(nombreBD, context.MODE_PRIVATE, null);
        this.database.execSQL(tabla);
    }

    public boolean insertar(Actividad actividad){
        ContentValues contentValues = new ContentValues();
        contentValues.put("materia_id", actividad.getMateria_id());
        contentValues.put("corte", actividad.getCorte());
        contentValues.put("porcentaje", actividad.getPorcentaje());
        contentValues.put("nombre", actividad.getNombre());
        return (database.insert(nombreTabla,null, contentValues)) > 0;
    }

    public boolean eliminar(int actividad_id){
        return (database.delete(nombreTabla, "actividad_id="+ actividad_id, null)) > 0;
    }

    public boolean editar(Actividad actividad){
        ContentValues contentValues = new ContentValues();
        //contentValues.put("materia_id", actividad.getMateria_id());
        //contentValues.put("corte", actividad.getCorte());
        contentValues.put("porcentaje", actividad.getPorcentaje());
        contentValues.put("nombre", actividad.getNombre());
        return (database.update(nombreTabla, contentValues, "actividad_id="+ actividad.getActividad_id(), null)) > 0;
    }

    public ArrayList<Actividad> listado(int materia_id, int corte){
        listaActividades.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ nombreTabla +" WHERE materia_id = "+ materia_id +" AND corte = "+ corte, null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                actividad = new Actividad();
                actividad.setActividad_id(cursor.getInt(0));
                actividad.setMateria_id(cursor.getInt(1));
                actividad.setCorte(cursor.getInt(2));
                actividad.setPorcentaje(cursor.getInt(3));
                actividad.setNombre(cursor.getString(4));
                listaActividades.add(actividad);
            }while (cursor.moveToNext());
        }
        return listaActividades;
    }

    public Actividad buscarActividad(int posicion){
        Cursor cursor = database.rawQuery("SELECT * FROM "+ nombreTabla, null);
        cursor.moveToPosition(posicion);
        actividad = new Actividad();
        actividad.setActividad_id(cursor.getInt(0));
        actividad.setMateria_id(cursor.getInt(1));
        actividad.setCorte(cursor.getInt(2));
        actividad.setPorcentaje(cursor.getInt(3));
        actividad.setNombre(cursor.getString(4));
        return actividad;
    }
}
