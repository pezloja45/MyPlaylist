package com.pezloja.myplaylist;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_musica;
    boolean esPrimera = true;
    AdaptadorCancion adaptadorCancion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_musica = findViewById(R.id.rv_musica);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayoutManager disposicion = new LinearLayoutManager(getApplicationContext());
            rv_musica.setLayoutManager(disposicion);
        } else {
            GridLayoutManager disposicion = new GridLayoutManager(getApplicationContext(), 2);
            rv_musica.setLayoutManager(disposicion);
        }

        adaptadorCancion = new AdaptadorCancion(ControladorCancion.getLstCanciones());
        rv_musica.setAdapter(adaptadorCancion);

        if (savedInstanceState != null) {
            esPrimera = savedInstanceState.getBoolean("esPrimera");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("esPrimera", esPrimera);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (esPrimera) {
            ControladorCancion.initCanciones();
            esPrimera = false;
        }
    }
}