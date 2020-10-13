package com.example.gestiondenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NotasActivity extends AppCompatActivity {
    DaoNota daoNota;
    AdaptadorNota adaptadorNota;
    ListView listView;
    ArrayList<Nota> listaNotas;
    Nota nota;
    int materia_id = 0, actividad_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        Bundle i = getIntent().getExtras();
        materia_id = i.getInt("materia_id");
        actividad_id = i.getInt("actividad_id");
        final String nombre = i.getString("nombre");
        this.setTitle(nombre);

        final EditText edNota = (EditText)findViewById(R.id.etNota);
        Button btnGuardarNota = (Button)findViewById(R.id.btnGuardarNota);

        daoNota = new DaoNota(this);
        listaNotas = daoNota.listado(actividad_id);
        adaptadorNota = new AdaptadorNota(listaNotas, daoNota, this);
        listView = (ListView) findViewById(R.id.listaNotas);
        listView.setAdapter(adaptadorNota);

        btnGuardarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    nota = new Nota();
                    nota.setActividad_id(actividad_id);
                    nota.setNota(Double.parseDouble(edNota.getText().toString()));
                    daoNota = new DaoNota(getApplicationContext());
                    daoNota.insertar(nota);
                    edNota.setText("");
                    listaNotas = daoNota.listado(actividad_id);
                    adaptadorNota.notifyDataSetChanged();
                    adaptadorNota = new AdaptadorNota(listaNotas, daoNota, NotasActivity.this);
                    listView.setAdapter(adaptadorNota);
                    Toast.makeText(getApplication(), "Nota guardada con éxito!", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplication(), "El campo nota es requerido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}