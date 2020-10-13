package com.example.gestiondenotas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdaptadorActividad extends BaseAdapter {
    ArrayList<Actividad> listadoActividades;
    DaoActividad daoActividad;
    Actividad actividad;
    Activity activity;
    int activity_id = 0, materia_id, corte;

    public AdaptadorActividad(ArrayList<Actividad> listadoActividades, DaoActividad daoActividad, Activity activity) {
        this.listadoActividades = listadoActividades;
        this.daoActividad = daoActividad;
        this.activity = activity;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
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

    @Override
    public int getCount() {
        return listadoActividades.size();
    }

    @Override
    public Object getItem(int position) {
        actividad = listadoActividades.get(position);
        return actividad;
    }

    @Override
    public long getItemId(int position) {
        actividad = listadoActividades.get(position);
        return actividad.getActividad_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_materia, null);
        }
        actividad = listadoActividades.get(position);
        TextView nombre = (TextView) view.findViewById(R.id.txtItemNombreMateria);
        TextView porcentaje = (TextView) view.findViewById(R.id.txtItemDescripcionMateria);
        Button btnEditar = (Button) view.findViewById(R.id.btnEditarMateria);
        final Button btnEliminar = (Button) view.findViewById(R.id.btnEliminarMateria);
        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linLayItemMateria);

        nombre.setText(actividad.getNombre());
        porcentaje.setText(actividad.getPorcentaje() +"%");
        btnEditar.setTag(position);
        btnEliminar.setTag(position);
        linearLayout.setTag(position);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vista interna que lleva a las notas
                int posicion = Integer.parseInt(v.getTag().toString());
                actividad = listadoActividades.get(posicion);
                Intent i = new Intent(activity.getApplicationContext(), NotasActivity.class);
                i.putExtra("materia_id", actividad.getMateria_id());
                i.putExtra("actividad_id", actividad.getActividad_id());
                i.putExtra("nombre", actividad.getNombre());
                activity.startActivity(i);
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vista de editar materia
                int posicion = Integer.parseInt(v.getTag().toString());
                final Dialog dialog = new Dialog(activity);
                dialog.setTitle("Editar actividad");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.crear_actividad);
                dialog.show();

                final EditText nombre = (EditText)dialog.findViewById(R.id.etNombreActividad);
                final EditText porcentaje = (EditText)dialog.findViewById(R.id.etPorcentajeActividad);
                Button guardar = (Button)dialog.findViewById(R.id.btnGuardarActividad);
                Button cancelar = (Button)dialog.findViewById(R.id.btnCancelarActividad);

                actividad = listadoActividades.get(posicion);
                //guardar datos de actividad
                setActivity_id(actividad.getActividad_id());
                setMateria_id(actividad.getMateria_id());
                setCorte(actividad.getCorte());

                nombre.setText(actividad.getNombre());
                porcentaje.setText(String.valueOf(actividad.getPorcentaje()));

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            actividad = new Actividad();
                            actividad.setActividad_id(getActivity_id());
                            actividad.setNombre(nombre.getText().toString());
                            actividad.setPorcentaje(Integer.parseInt(porcentaje.getText().toString()));

                            daoActividad.editar(actividad);
                            listadoActividades = daoActividad.listado(getMateria_id(), getCorte());
                            notifyDataSetChanged();
                            Toast.makeText(activity, "Actividad actualizada con éxito!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(activity, "ERROR", Toast.LENGTH_SHORT).show();
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
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialogo de confirmacion
                int posicion = Integer.parseInt(v.getTag().toString());
                actividad = listadoActividades.get(posicion);
                //guardar datos de actividad
                setActivity_id(actividad.getActividad_id());
                setMateria_id(actividad.getMateria_id());
                setCorte(actividad.getCorte());

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("¿Estas seguro de eliminar la actividad?");
                builder.setCancelable(false);
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        daoActividad.eliminar(getActivity_id());
                        listadoActividades = daoActividad.listado(getMateria_id(), getCorte());
                        notifyDataSetChanged();
                        Toast.makeText(activity, "Actividad eliminada con éxito!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
        return view;
    }
}
