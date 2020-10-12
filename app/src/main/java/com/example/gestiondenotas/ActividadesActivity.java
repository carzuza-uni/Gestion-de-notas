package com.example.gestiondenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ActividadesActivity extends AppCompatActivity {
    DaoActividad daoActividad;
    AdaptadorActividad adaptadorActividad;
    ArrayList<Actividad> listaActividades;
    Actividad actividad;
    int materia_id = 0, corte = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);

        Bundle i = getIntent().getExtras();
        materia_id = i.getInt("materia_id");
        corte = i.getInt("corte");
        final String nombre = i.getString("nombre");
        String titulo = nombre +" - Tercer corte";
        if(corte == 1){
            titulo = nombre +" - Primer corte";
        }else if(corte == 2){
            titulo = nombre +" - Segundo corte";
        }
        this.setTitle(titulo);

        daoActividad = new DaoActividad(this);
        listaActividades = daoActividad.listado(materia_id, corte);
        adaptadorActividad = new AdaptadorActividad(listaActividades, daoActividad, this);
        ListView listView = (ListView) findViewById(R.id.listaActividades);
        listView.setAdapter(adaptadorActividad);
    }
}