package com.example.labarys2019;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class PuntuacionCitaActivity extends AppCompatActivity {

    private InfoCita infoCitaSelected;
    private TextView tvCita, tvAutor, tvPuntuacion;
    private EditText etPuntuacion;

    public static final String CITA_KEY = "cita";
    public static final String AUTOR_KEY = "autor";
//    public static final String PUNTUACIONES_KEY = "puntuaciones";
//    public static final String ACUMULADO_KEY = "acumulado";
    public static final String DB_CLOUD = "cloudCitas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion_cita);

        etPuntuacion = findViewById(R.id.itn_puntuacion);
        etPuntuacion.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "10")});

        infoCitaSelected = (InfoCita)getIntent().getSerializableExtra("CITA_SELECTED");
        showInfoCita(infoCitaSelected);

    }

    private void showInfoCita(InfoCita infoCitaSelected) {
        tvCita = findViewById(R.id.tv_cita_act_edit);
        tvAutor = findViewById(R.id.tv_autor_act_edit);
        tvPuntuacion = findViewById(R.id.tv_puntuacion_act_edit);

        tvCita.setText(infoCitaSelected.getCita());
        tvAutor.setText(infoCitaSelected.getAutor());
        tvPuntuacion.setText(""+(infoCitaSelected.getAcumulado()/infoCitaSelected.getPuntuaciones()));
    }

    // debe actualizar la puntuacion de la cita
    public void updatePuntuacion(View view){
        // controlar que el campo no se encuentre vacio
        EditText etNuevaPuntuacion = findViewById(R.id.itn_puntuacion);
        final String nuevaPuntuacion = etNuevaPuntuacion.getText().toString();

        if(nuevaPuntuacion.isEmpty()){
            Toast.makeText(this, "Debe ingresar una puntuacion", Toast.LENGTH_SHORT).show();
            return;
        }
        // buscamos el dato en la db
        final CollectionReference dbFirebase = FirebaseFirestore.getInstance().collection(DB_CLOUD);
        // Create a query against the collection.
        Query query = dbFirebase.whereEqualTo(CITA_KEY, infoCitaSelected.getCita()).whereEqualTo(AUTOR_KEY, infoCitaSelected.getAutor());

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                String msgInfo = "";
                InfoCita cita;
                String idCita;
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        msgInfo += " " + document.getId() + " => " + document.getData() + "\n";
                        idCita = document.getId();
                        cita = document.toObject(InfoCita.class);
                        // actualizamos los datos (controlar que no este vacio el campo)
                        cita.setAcumulado(cita.getAcumulado() + Integer.parseInt(nuevaPuntuacion));
                        cita.setPuntuaciones(cita.getPuntuaciones() + 1);
                        dbFirebase.document(idCita).set(cita);
                        // aca deberia actualizar la info de la cita en la vista
                        TextView dataCita = findViewById(R.id.tv_puntuacion_act_edit);
                        dataCita.setText(""+(cita.getAcumulado() / cita.getPuntuaciones()));
                        Toast.makeText(getApplicationContext(), "Operacion realizada con exito", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String msgInfo = "Error getting documents: " + task.getException();
                    Toast.makeText(getApplicationContext(), msgInfo, Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(getApplicationContext(), "Recuperando datos objects QUERY: " + msgInfo, Toast.LENGTH_SHORT).show();
            }
        });

    }

    // finalizamos el activity
    public void finishActivity(View view){
        finish();
    }

//    private void updateDataView(InfoCita cita){
//
//    }
}
