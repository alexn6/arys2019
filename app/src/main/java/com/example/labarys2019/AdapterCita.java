package com.example.labarys2019;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterCita extends ArrayAdapter<Lead> {

    public  AdapterCita(Context context, List<Lead> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//        // ¿Existe el view actual?
//        if (null == convertView) {
//            convertView = inflater.inflate(
//                    R.layout.item_cita,
//                    parent,
//                    false);
//        }

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.item_cita_name,
                    parent,
                    false);
        }

        // Referencias del layout de itemCita
        TextView cita = convertView.findViewById(R.id.tv_cita);
        TextView autor = convertView.findViewById(R.id.tv_autor);


        // Lead actual.
        Lead lead = getItem(position);

        cita.setText(lead.getName());
        autor.setText(lead.getCompany());

        return convertView;
    }
}
