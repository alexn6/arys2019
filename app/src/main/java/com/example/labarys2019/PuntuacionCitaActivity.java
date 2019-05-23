package com.example.labarys2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PuntuacionCitaActivity extends AppCompatActivity {

    private InfoCita objectInfo;
    private TextView tvCita, tvAutor, tvPuntuacion;
    private EditText etPuntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion_cita);

        etPuntuacion = findViewById(R.id.itn_puntuacion);
        etPuntuacion.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "10")});

        objectInfo = (InfoCita)getIntent().getSerializableExtra("CITA_SELECTED");
        showInfoCita(objectInfo);

    }

    private void showInfoCita(InfoCita objectInfo) {
        tvCita = findViewById(R.id.tv_cita_act_edit);
        tvAutor = findViewById(R.id.tv_autor_act_edit);
        tvPuntuacion = findViewById(R.id.tv_puntuacion_act_edit);

        tvCita.setText(objectInfo.getCita());
        tvAutor.setText(objectInfo.getAutor());
        tvPuntuacion.setText(""+(objectInfo.getAcumulado()/objectInfo.getPuntuaciones()));
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
