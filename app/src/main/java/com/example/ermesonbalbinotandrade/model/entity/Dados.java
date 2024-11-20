package com.example.ermesonbalbinotandrade.model.entity;

import com.example.ermesonbalbinotandrade.R;

import java.util.ArrayList;

public class Dados  {
    public static ArrayList<TabelaPeriodica> obterAdapter() {
        ArrayList<TabelaPeriodica> listaElementos = new ArrayList<>();
        listaElementos.add(new TabelaPeriodica("Hidrogenio", R.drawable.ic_hidrogenio, "H"));
        listaElementos.add(new TabelaPeriodica("Litio", R.drawable.ic_litio, "Li"));
        listaElementos.add(new TabelaPeriodica("Sodio", R.drawable.ic_sodio, "Na"));
        listaElementos.add(new TabelaPeriodica("Potassio", R.drawable.ic_potassio, "K"));
        listaElementos.add(new TabelaPeriodica("Rubidio", R.drawable.ic_rubidio, "Rb"));
        listaElementos.add(new TabelaPeriodica("Cesio", R.drawable.ic_cesio, "Cs"));
        listaElementos.add(new TabelaPeriodica("Francio", R.drawable.ic_francio, "Fr"));

        return listaElementos;
    }
}
