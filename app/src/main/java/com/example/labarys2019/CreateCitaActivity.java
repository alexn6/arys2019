package com.example.labarys2019;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class CreateCitaActivity extends AppCompatActivity {

    public static final String CITA_KEY = "cita";
    public static final String AUTOR_KEY = "autor";
    public static final String PUNTUACIONES_KEY = "puntuaciones";
    public static final String ACUMULADO_KEY = "acumulado";
    public static final String DB_CLOUD = "cloudCitas";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cita);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    // guarda la cita ingresa en la db cloud
    public void saveCita(View view){
        final CollectionReference dbFirebase = FirebaseFirestore.getInstance().collection(DB_CLOUD);
        EditText etCita = findViewById(R.id.et_cita_create);
        EditText etAutor = findViewById(R.id.et_autor_create);
        String cita = etCita.getText().toString();
        String autor = etAutor.getText().toString();

        if(cita.isEmpty() || autor.isEmpty()){
            Toast.makeText(this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        final Map<String, Object> dataToSave = new HashMap<>();
        dataToSave.put(CITA_KEY, cita);
        dataToSave.put(AUTOR_KEY, autor);
        // controlamos q no haya un documento igual
        Query query = dbFirebase.whereEqualTo(CITA_KEY, cita).whereEqualTo(AUTOR_KEY, autor);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if(task.getResult().getDocuments().size() > 0){
//                        Toast.makeText(getApplicationContext(), "Ya existe un documento con estos datos ("+task.getResult().getDocuments().size()+")", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Ya existe un documento con estos datos", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else{
                        // cambiar esto
                        dataToSave.put(PUNTUACIONES_KEY, 1);
                        dataToSave.put(ACUMULADO_KEY, 5);

                        // agregar los datos a una coleccion en la db cloud
                        dbFirebase.add(dataToSave)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(getApplicationContext(), "El documento ha sido guardado con exito ", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), "Error al guardar el documento" +e, Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                } else {
                    String msgInfo = "Error al verificar la disponibilidad del documento: " + task.getException();
                    Toast.makeText(getApplicationContext(), msgInfo, Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }

    // finalizamos el activity
    public void finishActivity(View view){
        finish();
    }
}
