package com.example.labarys2019;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String CITA_KEY = "cita";
    public static final String AUTOR_KEY = "autor";
//    TextView tv_get_citas;

//    private CollectionReference dbFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        dbFirebase = FirebaseFirestore.getInstance().collection("cloudCitas");
//        tv_get_citas = findViewById(R.id.tv_get_citas);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

//    public void saveCitaInCollection(View view){
//        EditText etCita = findViewById(R.id.et_cita);
//        EditText etAutor = findViewById(R.id.et_autor);
//        String cita = etCita.getText().toString();
//        String autor = etAutor.getText().toString();
//
//        if(cita.isEmpty() || autor.isEmpty()){
//            Toast.makeText(this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        Map<String, Object> dataToSave = new HashMap<>();
//        dataToSave.put(CITA_KEY, cita);
//        dataToSave.put(AUTOR_KEY, autor);
//
//        // agregar los datos a una coleccion en la db cloud
//        dbFirebase.add(dataToSave)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(getApplicationContext(), "El documento ha sido guardado con ID: "+ documentReference.getId(), Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getApplicationContext(), "Error al guardar el documento" +e, Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

//    public void getAllCitas(View view){
//        dbFirebase.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                String msgInfo = "";
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        msgInfo += " " + document.getId() + " => " + document.getData() + "\n";
//                    }
//                } else {
//                    msgInfo += "Error getting documents: " + task.getException();
//                }
//                Toast.makeText(getApplicationContext(), "Recuperando datos: " + msgInfo, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    // deberia mostrar los datos recuperados de la db cloud en una lista
    public void showDataInList(){

    }

    public void passToPuntuacion(View view){
        Intent toPuntuacion = new Intent(this, ListCitasActivity.class);
        startActivity(toPuntuacion);
    }

    public void passToCreateCita(View view){
        Intent toCreate = new Intent(this, CreateCitaActivity.class);
        startActivity(toCreate);
    }
}
