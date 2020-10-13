package com.example.gestiondenotas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class AdaptadorNota extends BaseAdapter {
    ArrayList<Nota> listadoNotas;
    DaoNota daoNota;
    Nota nota;
    Activity activity;
    int nota_id = 0, activity_id = 0;

    public AdaptadorNota(ArrayList<Nota> listadoNotas, DaoNota daoNota, Activity activity) {
        this.listadoNotas = listadoNotas;
        this.daoNota = daoNota;
        this.activity = activity;
    }

    public int getNota_id() {
        return nota_id;
    }

    public void setNota_id(int nota_id) {
        this.nota_id = nota_id;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    @Override
    public int getCount() {
        return listadoNotas.size();
    }

    @Override
    public Object getItem(int position) {
        nota = listadoNotas.get(position);
        return nota;
    }

    @Override
    public long getItemId(int position) {
        nota = listadoNotas.get(position);
        return nota.getNota_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_materia, null);
        }
        nota = listadoNotas.get(position);
        final TextView nombre = (TextView) view.findViewById(R.id.txtItemNombreMateria);
        //TextView porcentaje = (TextView) view.findViewById(R.id.txtItemDescripcionMateria);
        Button btnEditar = (Button) view.findViewById(R.id.btnEditarMateria);
        btnEditar.setVisibility(View.INVISIBLE);
        final Button btnEliminar = (Button) view.findViewById(R.id.btnEliminarMateria);

        nombre.setText(String.valueOf(nota.getNota()));
        btnEliminar.setTag(position);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialogo de confirmacion
                int posicion = Integer.parseInt(v.getTag().toString());
                nota = listadoNotas.get(posicion);
                //guardar datos de actividad
                setNota_id(nota.getNota_id());
                setActivity_id(nota.getActividad_id());

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("¿Estas seguro de eliminar la nota?");
                builder.setCancelable(false);
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        daoNota.eliminar(getNota_id());
                        listadoNotas = daoNota.listado(getActivity_id());
                        notifyDataSetChanged();
                        Toast.makeText(activity, "Nota eliminada con éxito!", Toast.LENGTH_SHORT).show();
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
