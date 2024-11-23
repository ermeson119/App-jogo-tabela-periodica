package com.example.ermesonbalbinotandrade.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.ermesonbalbinotandrade.R;
import com.example.ermesonbalbinotandrade.view.adpter.TabelaAdapeter;
import com.example.ermesonbalbinotandrade.model.entity.Dados;
import com.example.ermesonbalbinotandrade.model.entity.TabelaPeriodica;
import com.example.ermesonbalbinotandrade.view.fragments.cesio.AlertDialogCesioFragment;
import com.example.ermesonbalbinotandrade.view.fragments.francio.AlertDialogFrancioFragment;
import com.example.ermesonbalbinotandrade.view.fragments.hidrogenio.AlertDialogHidrogenioFragment;
import com.example.ermesonbalbinotandrade.view.fragments.litio.AlertDialogLitioFragment;
import com.example.ermesonbalbinotandrade.view.fragments.potassio.AlertDialogPotasioFragment;
import com.example.ermesonbalbinotandrade.view.fragments.rubidio.AlertDialogRubidioFragment;
import com.example.ermesonbalbinotandrade.view.fragments.sodio.AlertDialogSodioFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener  {
    private ListView listViewTabela;
    private TabelaAdapeter adapter;
    private ArrayList<TabelaPeriodica> listaTabelaPeriodica;
    private Button buttonProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewTabela = findViewById(R.id.listaTabelaPeriodica);
        buttonProximo =  findViewById(R.id.buttonProximo);

        criarAdapter();
        listViewTabela.setAdapter(adapter);
        listViewTabela.setOnItemClickListener(this);

        buttonProximo.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, EntradaUsuarioActivity.class));
        });

    }

    private void criarAdapter() {
        listaTabelaPeriodica = Dados.obterAdapter();
        adapter = new TabelaAdapeter(this, listaTabelaPeriodica);
        listViewTabela.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TabelaPeriodica tabelaPeriodica = (TabelaPeriodica) parent.getItemAtPosition(position);
        abrirTela(tabelaPeriodica.getNome());
    }
    private void abrirTela(String nomePeriodica) {

        DialogFragment dialogFragment = null;

        nomePeriodica = nomePeriodica.toLowerCase();
        switch (nomePeriodica) {
            case "hidrogenio":
                dialogFragment = new AlertDialogHidrogenioFragment();
                break;

            case "litio":
                dialogFragment = new AlertDialogLitioFragment();
                break;

            case "sodio":
                dialogFragment = new AlertDialogSodioFragment();
                break;

            case "potassio":
                dialogFragment = new AlertDialogPotasioFragment();
                break;

            case "rubidio":
                dialogFragment = new AlertDialogRubidioFragment();
                break;

            case "cesio":
                dialogFragment = new AlertDialogCesioFragment();
                break;

            case "francio":
                dialogFragment = new AlertDialogFrancioFragment();
                break;
        }
        if (dialogFragment != null) {
            dialogFragment.show(getSupportFragmentManager(), "personalizado");
        }
    }

}