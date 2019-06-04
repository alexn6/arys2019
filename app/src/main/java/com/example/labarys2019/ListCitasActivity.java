package com.example.labarys2019;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListCitasActivity extends AppCompatActivity implements FragCita.OnFragmentInteractionListener{

    FragCita fragCita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_citas);

        mostrarCitasSupportFragment();
    }

    public void mostrarCitasSupportFragment(){
        fragCita = new FragCita();
        getSupportFragmentManager().beginTransaction().add(R.id.container_list_citas, fragCita).commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}
}
