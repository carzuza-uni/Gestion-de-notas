package com.example.gestiondenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CorteActivity extends AppCompatActivity {
    Actividad actividad;
    DaoActividad daoActividad;
    int materia_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corte);
        Bundle i = getIntent().getExtras();
        materia_id = i.getInt("materia_id");
        final String nombre = i.getString("nombre");
        this.setTitle(nombre);

        daoActividad = new DaoActividad(this);

        Button btnAgregarCorte1 = (Button)findViewById(R.id.btnAgregarActividadCorte1);
        Button btnAgregarCorte2 = (Button)findViewById(R.id.btnAgregarActividadCorte2);
        Button btnAgregarCorte3 = (Button)findViewById(R.id.btnAgregarActividadCorte3);

        Button btnVerCorte1 = (Button)findViewById(R.id.btnVerActividadCorte1);
        Button btnVerCorte2 = (Button)findViewById(R.id.btnVerActividadCorte2);
        Button btnVerCorte3 = (Button)findViewById(R.id.btnVerActividadCorte3);

        btnVerCorte1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CorteActivity.this, ActividadesActivity.class);
                i.putExtra("materia_id", materia_id);
                i.putExtra("corte", 1);
                i.putExtra("nombre", nombre);
                startActivity(i);
            }
        });
        btnVerCorte2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CorteActivity.this, ActividadesActivity.class);
                i.putExtra("materia_id", materia_id);
                i.putExtra("corte", 2);
                i.putExtra("nombre", nombre);
                startActivity(i);
            }
        });
        btnVerCorte3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CorteActivity.this, ActividadesActivity.class);
                i.putExtra("materia_id", materia_id);
                i.putExtra("corte", 3);
                i.putExtra("nombre", nombre);
                startActivity(i);
            }
        });

        btnAgregarCorte1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(CorteActivity.this);
                dialog.setTitle("Agregar actividad");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.crear_actividad);
                dialog.show();

                final EditText nombre = (EditText)dialog.findViewById(R.id.etNombreActividad);
                final EditText porcentaje = (EditText)dialog.findViewById(R.id.etPorcentajeActividad);
                Button guardar = (Button)dialog.findViewById(R.id.btnGuardarActividad);
                Button cancelar = (Button)dialog.findViewById(R.id.btnCancelarActividad);

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            actividad = new Actividad();
                            actividad.setMateria_id(materia_id);
                            actividad.setCorte(1);
                            actividad.setPorcentaje(Integer.parseInt(porcentaje.getText().toString()));
                            actividad.setNombre(nombre.getText().toString());
                            DaoActividad daoActividad = new DaoActividad(getApplicationContext());
                            daoActividad.insertar(actividad);
                            Toast.makeText(getApplication(), "Actividad guardada con éxito!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(getApplication(), "Complete los campos", Toast.LENGTH_SHORT).show();
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
        btnAgregarCorte2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(CorteActivity.this);
                dialog.setTitle("Agregar actividad");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.crear_actividad);
                dialog.show();

                final EditText nombre = (EditText)dialog.findViewById(R.id.etNombreActividad);
                final EditText porcentaje = (EditText)dialog.findViewById(R.id.etPorcentajeActividad);
                Button guardar = (Button)dialog.findViewById(R.id.btnGuardarActividad);
                Button cancelar = (Button)dialog.findViewById(R.id.btnCancelarActividad);

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            actividad = new Actividad();
                            actividad.setMateria_id(materia_id);
                            actividad.setCorte(2);
                            actividad.setPorcentaje(Integer.parseInt(porcentaje.getText().toString()));
                            actividad.setNombre(nombre.getText().toString());
                            DaoActividad daoActividad = new DaoActividad(getApplicationContext());
                            daoActividad.insertar(actividad);
                            Toast.makeText(getApplication(), "Actividad guardada con éxito!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(getApplication(), "Complete los campos", Toast.LENGTH_SHORT).show();
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
        btnAgregarCorte3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(CorteActivity.this);
                dialog.setTitle("Agregar actividad");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.crear_actividad);
                dialog.show();

                final EditText nombre = (EditText)dialog.findViewById(R.id.etNombreActividad);
                final EditText porcentaje = (EditText)dialog.findViewById(R.id.etPorcentajeActividad);
                Button guardar = (Button)dialog.findViewById(R.id.btnGuardarActividad);
                Button cancelar = (Button)dialog.findViewById(R.id.btnCancelarActividad);

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            actividad = new Actividad();
                            actividad.setMateria_id(materia_id);
                            actividad.setCorte(3);
                            actividad.setPorcentaje(Integer.parseInt(porcentaje.getText().toString()));
                            actividad.setNombre(nombre.getText().toString());
                            DaoActividad daoActividad = new DaoActividad(getApplicationContext());
                            daoActividad.insertar(actividad);
                            Toast.makeText(getApplication(), "Actividad guardada con éxito!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(getApplication(), "Complete los campos", Toast.LENGTH_SHORT).show();
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