package com.example.labarys2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
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
