package com.example.labarys2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PuntuacionCitaActivity extends AppCompatActivity {

    private Lead objectInfo;
    private TextView tvCita, tvAutor, tvPuntuacion;
    private EditText etPuntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion_cita);

        etPuntuacion = findViewById(R.id.itn_puntuacion);
        etPuntuacion.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "10")});

        objectInfo = (Lead)getIntent().getSerializableExtra("CITA_SELECTED");
        showInfoCita(objectInfo);

    }

    private void showInfoCita(Lead objectInfo) {
        tvCita = findViewById(R.id.tv_cita_act_edit);
        tvAutor = findViewById(R.id.tv_autor_act_edit);
        tvPuntuacion = findViewById(R.id.tv_puntuacion_act_edit);

        tvCita.setText(objectInfo.getName());
        tvAutor.setText(objectInfo.getCompany());
        tvPuntuacion.setText(objectInfo.getTitle());
    }

    // debe actualizar la puntuacion de la cita
    private void updatePuntuacion(String puntos){
        // se deberia agregar mas campos a las duplas (puntuacion y cant de votantes para el promedio)
        // controlar que el campo no se encuentre vacio
        // actualizar la info de la cita al relizarse el update exitoso tanto en el servidor como en la vista
        // agregar funcionalidad a los botones
    }

    // finalizamos el activity
    public void finishActivity(View view){
        finish();
    }
}
