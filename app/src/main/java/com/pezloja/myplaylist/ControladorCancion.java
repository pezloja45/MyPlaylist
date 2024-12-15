package com.pezloja.myplaylist;

import java.util.ArrayList;
import java.util.List;

public class ControladorCancion {
    static List<Cancion> lstCanciones = new ArrayList<>();

    public static List<Cancion> getLstCanciones() {
        return lstCanciones;
    }

    public static void initCanciones() {
        Cancion c;
        c = new Cancion("BADGYAL", "SAIKO", R.drawable.bagyal, R.raw.badgyal);
        lstCanciones.add(c);
        c = new Cancion("WYA", "Autor 2", R.drawable.wya, R.raw.wya);
        lstCanciones.add(c);
        c = new Cancion("HALO", "Autor 3", R.drawable.halo, R.raw.halo);
        lstCanciones.add(c);
    }
}
