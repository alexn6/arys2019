package com.example.labarys2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(conexionNet()){
            Toast.makeText(getApplicationContext(), "Hay conexion!!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "No hay conexion a internet!!", Toast.LENGTH_SHORT).show();
        }
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

    private boolean conexionNet(){
        ToolSupport adminConexion = new ToolSupport();

        if(!adminConexion.isNetDisponible(getApplicationContext())){
//            Toast.makeText(getApplicationContext(), "No hay conexion a internet!!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!adminConexion.isOnlineNet()){
//            Toast.makeText(getApplicationContext(), "Hay conexion!!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
