package com.example.gestiondenotas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DaoNota {
    SQLiteDatabase database;
    ArrayList<Nota> listaNotas = new ArrayList<Nota>();
    Nota nota;
    Context context;
    String nombreBD = "notasBD";
    String nombreTabla = "nota";
    String tabla = "CREATE TABLE IF NOT EXISTS "+ nombreTabla +" (nota_id INTEGER PRIMARY KEY AUTOINCREMENT, actividad_id INTEGER, nota REAL)";

    public DaoNota(Context context) {
        this.context = context;
        this.database = context.openOrCreateDatabase(nombreBD, context.MODE_PRIVATE, null);
        this.database.execSQL(tabla);
    }

    public boolean insertar(Nota nota){
        ContentValues contentValues = new ContentValues();
        contentValues.put("actividad_id", nota.getActividad_id());
        contentValues.put("nota", nota.getNota());
        return (database.insert(nombreTabla,null, contentValues)) > 0;
    }

    public ArrayList<Nota> listado(int actividad_id){
        listaNotas.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ nombreTabla +" WHERE actividad_id = "+ actividad_id, null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                nota = new Nota();
                nota.setNota_id(cursor.getInt(0));
                nota.setActividad_id(cursor.getInt(1));
                nota.setNota(cursor.getDouble(2));
                listaNotas.add(nota);
            }while (cursor.moveToNext());
        }
        return listaNotas;
    }
}
