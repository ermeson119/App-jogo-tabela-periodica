package com.example.ermesonbalbinotandrade.controller;

import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ermesonbalbinotandrade.R;
import com.example.ermesonbalbinotandrade.model.entity.TabelaPeriodica;

import java.util.ArrayList;

public class ResultadoActivity extends AppCompatActivity {
    private TableLayout tableLayoutGabarito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        tableLayoutGabarito = findViewById(R.id.tableLayoutGabarito);

        // Receber os dados da tela anterior
        ArrayList<TabelaPeriodica> elementos = (ArrayList<TabelaPeriodica>) getIntent().getSerializableExtra("elementos");
        ArrayList<String> tentativas = getIntent().getStringArrayListExtra("tentativas");

        // Gerar gabarito
        if (elementos != null && tentativas != null) {
            gerarGabarito(elementos, tentativas);
        }
    }

    private void gerarGabarito(ArrayList<TabelaPeriodica> elementos, ArrayList<String> tentativas) {
        for (int i = 0; i < elementos.size(); i++) {
            TabelaPeriodica elemento = elementos.get(i);

            // Carregar as perguntas do elemento
            String[] opcoes = carregarPerguntas(elemento.getNome());

            // A resposta correta é sempre a primeira posição do array
            String respostaCorreta = opcoes[0].trim();

            // Verifique se existe uma tentativa correspondente
            String tentativaUsuario = (i < tentativas.size()) ? tentativas.get(i).trim() : "Não respondido";

            // Criar uma nova linha na tabela
            TableRow row = new TableRow(this);

            // Adicionar o nome do elemento
            TextView nomeElemento = new TextView(this);
            nomeElemento.setText(elemento.getNome());
            row.addView(nomeElemento);

            // Adicionar o número atômico correto
            TextView numeroAtomico = new TextView(this);
            numeroAtomico.setText(respostaCorreta);
            row.addView(numeroAtomico);

            // Adicionar a tentativa do usuário
            TextView tentativa = new TextView(this);
            tentativa.setText(tentativaUsuario);
            row.addView(tentativa);

            // Adicionar a imagem de acerto ou erro
            ImageView resultado = new ImageView(this);

            // Comparar resposta correta com a tentativa do usuário
            if (tentativaUsuario.equals(respostaCorreta)) {
                resultado.setImageResource(R.drawable.ic_acerto);
            } else {
                resultado.setImageResource(R.drawable.ic_error);
            }
            row.addView(resultado);

            // Adicionar a linha na tabela
            tableLayoutGabarito.addView(row);
        }
    }


    private String[] carregarPerguntas(String nomeElemento) {
        switch (nomeElemento.toLowerCase()) { // Padroniza para minúsculas
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
                return new String[] {}; // Retorna vazio se não encontrar o nome
        }
    }


}
