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

public class AdaptadorMateria extends BaseAdapter {
    ArrayList<Materia> listadoMaterias;
    daoMateria daoMateria;
    Materia materia;
    Activity activity;
    int materia_id = 0;

    public AdaptadorMateria(ArrayList<Materia> listadoMaterias, daoMateria daoMateria, Activity activity) {
        this.listadoMaterias = listadoMaterias;
        this.daoMateria = daoMateria;
        this.activity = activity;
    }

    public int getMateria_id() {
        return materia_id;
    }

    public void setMateria_id(int materia_id) {
        this.materia_id = materia_id;
    }

    @Override
    public int getCount() {
        return listadoMaterias.size();
    }

    @Override
    public Object getItem(int position) {
        materia = listadoMaterias.get(position);
        return materia;
    }

    @Override
    public long getItemId(int position) {
        materia = listadoMaterias.get(position);
        return materia.getMateria_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_materia, null);
        }
        materia = listadoMaterias.get(position);
        TextView nombre = (TextView) view.findViewById(R.id.txtItemNombreMateria);
        TextView descripcion = (TextView) view.findViewById(R.id.txtItemDescripcionMateria);
        Button btnEditar = (Button) view.findViewById(R.id.btnEditarMateria);
        final Button btnEliminar = (Button) view.findViewById(R.id.btnEliminarMateria);
        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linLayItemMateria);

        nombre.setText(materia.nombre);
        descripcion.setText(materia.descripcion);
        btnEditar.setTag(position);
        btnEliminar.setTag(position);
        linearLayout.setTag(position);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vista interna que lleva a los cortes
                int posicion = Integer.parseInt(v.getTag().toString());
                materia = listadoMaterias.get(posicion);
                Intent i = new Intent(activity.getApplicationContext(), CorteActivity.class);
                i.putExtra("materia_id", materia.getMateria_id());
                i.putExtra("nombre", materia.getNombre());
                activity.startActivity(i);
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vista de editar materia
                int posicion = Integer.parseInt(v.getTag().toString());
                final Dialog dialog = new Dialog(activity);
                dialog.setTitle("Agregar materia");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.crear_materia);
                dialog.show();

                final EditText nombre = (EditText)dialog.findViewById(R.id.etNombre);
                final EditText descripcion = (EditText)dialog.findViewById(R.id.etDescripcion);
                Button guardar = (Button)dialog.findViewById(R.id.btnGuardarMateria);
                Button cancelar = (Button)dialog.findViewById(R.id.btnCancelarMateria);

                materia = listadoMaterias.get(posicion);
                setMateria_id(materia.getMateria_id());
                nombre.setText(materia.getNombre());
                descripcion.setText(materia.getDescripcion());

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            materia = new Materia();
                            materia.setMateria_id(getMateria_id());
                            materia.setNombre(nombre.getText().toString());
                            materia.setDescripcion(descripcion.getText().toString());

                            daoMateria.editar(materia);
                            listadoMaterias = daoMateria.listado();
                            notifyDataSetChanged();
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
                materia = listadoMaterias.get(posicion);
                setMateria_id(materia.getMateria_id());
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("¿Estas seguro de eliminar la materia?");
                builder.setCancelable(false);
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        daoMateria.eliminar(getMateria_id());
                        listadoMaterias = daoMateria.listado();
                        notifyDataSetChanged();
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
