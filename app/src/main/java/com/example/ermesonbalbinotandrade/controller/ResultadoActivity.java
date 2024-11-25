package com.example.ermesonbalbinotandrade.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ermesonbalbinotandrade.R;
import com.example.ermesonbalbinotandrade.model.entity.TabelaPeriodica;
import com.example.ermesonbalbinotandrade.view.fragments.menu.HistoricoDialogFragment;
import com.example.ermesonbalbinotandrade.view.fragments.menu.JogarNovamenteDialogFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultadoActivity extends AppCompatActivity {
    private TableLayout tableLayoutGabarito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Resultado");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tableLayoutGabarito = findViewById(R.id.tableLayoutGabarito);

        ArrayList<TabelaPeriodica> elementos = (ArrayList<TabelaPeriodica>) getIntent().getSerializableExtra("elementos");
        ArrayList<String> tentativas = getIntent().getStringArrayListExtra("tentativas");


        if (elementos != null && tentativas != null) {
            gerarGabarito(elementos, tentativas);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String itemNome = item.getTitle().toString();

        switch (itemNome) {
            case "Jogar Novamente":
                new JogarNovamenteDialogFragment().show(getSupportFragmentManager(), "jogarNovamente");
                return true;

            case "Mostrar Histórico":
                mostrarHistorico();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mostrarHistorico() {
        HashMap<String, ArrayList<String>> historicoJogador = obterHistoricoJogador();

        if (historicoJogador == null || historicoJogador.isEmpty()) {
            Toast.makeText(this, "Nenhum histórico disponível.", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<String> historico = new ArrayList<>();

        for (Map.Entry<String, ArrayList<String>> entrada : historicoJogador.entrySet()) {
            String jogada = entrada.getKey();
            ArrayList<String> detalhes = entrada.getValue();

            StringBuilder detalhesFormatados = new StringBuilder();
            for (String detalhe : detalhes) {
                detalhesFormatados.append(detalhe).append("\n");
            }

            // Adiciona a jogada e os detalhes formatados à lista de histórico
            historico.add(jogada + "\n" + detalhesFormatados.toString().trim());
        }


        HistoricoDialogFragment dialogFragment = HistoricoDialogFragment.newInstance(historico);
        dialogFragment.show(getSupportFragmentManager(), "mostrarHistorico");
    }



    private HashMap<String, ArrayList<String>> obterHistoricoJogador() {
        Intent intent = getIntent();
        if (intent.hasExtra("historico")) {
            // Retorna o histórico obtido como um HashMap
            return (HashMap<String, ArrayList<String>>) intent.getSerializableExtra("historico");
        }
        return new HashMap<>();
    }



    private void gerarGabarito(ArrayList<TabelaPeriodica> elementos, ArrayList<String> tentativas) {
        for (int i = 0; i < elementos.size(); i++) {
            TabelaPeriodica elemento = elementos.get(i); // Obtém o elemento atual


            String[] opcoes = carregarPerguntas(elemento.getNome());

            String respostaCorreta = opcoes[0].trim();

            String tentativaUsuario = (i < tentativas.size()) ? tentativas.get(i).trim() : "Não respondido";

            TableRow row = new TableRow(this);

            TextView nomeElemento = new TextView(this);
            nomeElemento.setText(elemento.getNome());
            row.addView(nomeElemento);

            TextView numeroAtomico = new TextView(this);
            numeroAtomico.setText(respostaCorreta);
            row.addView(numeroAtomico);

            TextView tentativa = new TextView(this);
            tentativa.setText(tentativaUsuario);
            row.addView(tentativa);


            ImageView resultado = new ImageView(this);
            if (tentativaUsuario.equals(respostaCorreta)) {
                resultado.setImageResource(R.drawable.ic_acerto);
            } else {
                resultado.setImageResource(R.drawable.ic_error);
            }
            row.addView(resultado);

            tableLayoutGabarito.addView(row);
        }
    }

    private String[] carregarPerguntas(String nomeElemento) {
        switch (nomeElemento.toLowerCase()) {
            case "hidrogenio":
                return getResources().getStringArray(R.array.perguntas_HIDROGENIO);
            case "litio":
                return getResources().getStringArray(R.array.perguntas_LITIO);
            case "sodio":
                return getResources().getStringArray(R.array.perguntas_SODIO);
            case "potassio":
                return getResources().getStringArray(R.array.perguntas_POTASSIO);
            case "rubidio":
                return getResources().getStringArray(R.array.perguntas_RUBIDIO);
            case "cesio":
                return getResources().getStringArray(R.array.perguntas_CESIO);
            case "francio":
                return getResources().getStringArray(R.array.perguntas_FRANCIO);
            default:
                return new String[] {};
        }
    }


}
