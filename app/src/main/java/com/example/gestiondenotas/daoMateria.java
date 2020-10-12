package com.example.gestiondenotas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoMateria {
    SQLiteDatabase database;
    ArrayList<Materia> listaMaterias = new ArrayList<Materia>();
    Materia materia;
    Context context;
    String nombreBD = "notasBD";
    String nombreTabla = "materia";
    String tabla = "CREATE TABLE IF NOT EXISTS "+ nombreTabla +" (materia_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, descripcion TEXT)";

    public daoMateria(Context context) {
        this.context = context;
        this.database = context.openOrCreateDatabase(nombreBD, context.MODE_PRIVATE, null);
        this.database.execSQL(tabla);
    }

    public boolean insertar(Materia materia){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", materia.nombre);
        contentValues.put("descripcion", materia.descripcion);
        return (database.insert(nombreTabla,null, contentValues)) > 0;
    }

    public boolean eliminar(int materia_id){
        return (database.delete(nombreTabla, "materia_id="+ materia_id, null)) > 0;
    }

    public boolean editar(Materia materia){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", materia.nombre);
        contentValues.put("descripcion", materia.descripcion);
        return (database.update(nombreTabla, contentValues, "materia_id="+ materia.getMateria_id(), null)) > 0;
    }

    public ArrayList<Materia> listado(){
        listaMaterias.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ nombreTabla, null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                materia = new Materia();
                materia.setMateria_id(cursor.getInt(0));
                materia.setNombre(cursor.getString(1));
                materia.setDescripcion(cursor.getString(2));
                listaMaterias.add(materia);
            }while (cursor.moveToNext());
        }
        return listaMaterias;
    }

    public Materia buscarMateria(int posicion){
        Cursor cursor = database.rawQuery("SELECT * FROM "+ nombreTabla, null);
        cursor.moveToPosition(posicion);
        materia = new Materia();
        materia.setMateria_id(cursor.getInt(0));
        materia.setNombre(cursor.getString(1));
        materia.setDescripcion(cursor.getString(2));
        return materia;
    }
}
