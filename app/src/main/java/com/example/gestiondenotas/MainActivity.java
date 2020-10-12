package com.example.gestiondenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    daoMateria daoMat;
    AdaptadorMateria adaptadorMateria;
    ArrayList<Materia> listaMaterias;
    Materia materia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daoMat = new daoMateria(this);
        listaMaterias = daoMat.listado();
        adaptadorMateria = new AdaptadorMateria(listaMaterias, daoMat, this);
        ListView listView = (ListView) findViewById(R.id.listaMaterias);
        Button btnAgregar = (Button)findViewById(R.id.btnAgregarMateria);
        listView.setAdapter(adaptadorMateria);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //vista interna que lleva a los cortes
                /*Toast.makeText(getApplication(), "PRUEBA DE CLICK", Toast.LENGTH_SHORT).show();
                materia = listaMaterias.get(position);
                Intent i = new Intent(MainActivity.this, CorteActivity.class);
                i.putExtra("materia_id", materia.getMateria_id());
                i.putExtra("nombre", materia.getNombre());
                startActivity(i);*/
            }
        });
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vista de agregar materia
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("Agregar materia");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.crear_materia);
                dialog.show();

                final EditText nombre = (EditText)dialog.findViewById(R.id.etNombre);
                final EditText descripcion = (EditText)dialog.findViewById(R.id.etDescripcion);
                Button guardar = (Button)dialog.findViewById(R.id.btnGuardarMateria);
                Button cancelar = (Button)dialog.findViewById(R.id.btnCancelarMateria);
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            materia = new Materia();
                            materia.setNombre(nombre.getText().toString());
                            materia.setDescripcion(descripcion.getText().toString());

                            daoMat.insertar(materia);
                            listaMaterias = daoMat.listado();
                            adaptadorMateria.notifyDataSetChanged();
                            Toast.makeText(getApplication(), "Materia guardada con éxito!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(getApplication(), "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

    }
}