package com.example.ermesonbalbinotandrade.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ermesonbalbinotandrade.R;
import com.example.ermesonbalbinotandrade.model.entity.TabelaPeriodica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JogadasPerguntas extends AppCompatActivity {

    private LinearLayout perguntasLayout;
    private TextView textViewCoracoes, textViewPontuacao;
    private Button btnFinalizar;
    private ArrayList<TabelaPeriodica> elementosSelecionados;
    private ArrayList<String> tentativasUsuario = new ArrayList<>();
    private HashMap<String, ArrayList<String>> historicoJogador;

    private int tentativasRestantes = 3;
    private int pontuacao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogadas_perguntas);

        perguntasLayout = findViewById(R.id.perguntasLayout);
        textViewCoracoes = findViewById(R.id.textViewCoracoes);
        textViewPontuacao = findViewById(R.id.textViewPontuacao);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        elementosSelecionados = (ArrayList<TabelaPeriodica>) getIntent().getSerializableExtra("elementos");

        historicoJogador = new HashMap<>();


        if (elementosSelecionados == null || elementosSelecionados.isEmpty()) {
            Toast.makeText(this, "Nenhum item recebido!", Toast.LENGTH_SHORT).show();
            return;
        }


        atualizarCoracoes();
        textViewPontuacao.setText("Pontuação: " + pontuacao);

        gerarPerguntas();

        btnFinalizar.setOnClickListener(v -> finalizarJogo());
    }

    private void gerarPerguntas() {
        for (TabelaPeriodica elemento : elementosSelecionados) {

            View perguntaView = getLayoutInflater().inflate(R.layout.item_pergunta, null);

            TextView textViewElemento = perguntaView.findViewById(R.id.textViewElemento);
            ImageView imageViewElemento = perguntaView.findViewById(R.id.imageViewElemento);
            CheckBox checkBox1 = perguntaView.findViewById(R.id.checkBox1);
            CheckBox checkBox2 = perguntaView.findViewById(R.id.checkBox2);
            CheckBox checkBox3 = perguntaView.findViewById(R.id.checkBox3);
            CheckBox checkBox4 = perguntaView.findViewById(R.id.checkBox4);

            imageViewElemento.setImageResource(elemento.getImagemEle());
            textViewElemento.setText("Qual é o número atômico do elemento: " + elemento.getNome());

            String[] opcoes = carregarPerguntas(elemento.getNome());

            if (opcoes.length != 4) {
                Toast.makeText(this, "Erro ao carregar perguntas para " + elemento.getNome(), Toast.LENGTH_SHORT).show();
                return;
            }

            checkBox1.setText(opcoes[0]);
            checkBox2.setText(opcoes[1]);
            checkBox3.setText(opcoes[2]);
            checkBox4.setText(opcoes[3]);

            View.OnClickListener validarResposta = v -> {
                CheckBox selecionado = (CheckBox) v;
                String respostaSelecionada = selecionado.getText().toString();
                String respostaCorreta = opcoes[0];

                tentativasUsuario.add(respostaSelecionada);

                String nomeUsuario = getIntent().getStringExtra("nomeUsuario");

                if (respostaSelecionada.equals(respostaCorreta)) {
                    pontuacao += 10;
                    textViewPontuacao.setText("Pontuação: " + pontuacao);
                } else {
                    tentativasRestantes--;
                    atualizarCoracoes();
                    if (tentativasRestantes == 0) {
                        finalizarJogo();
                        return;
                    }
                }

                if (nomeUsuario != null) {
                    ArrayList<String> registros = historicoJogador.getOrDefault(nomeUsuario, new ArrayList<>());
                    registros.add("Jogada: " + respostaSelecionada + ", Pontuação: " + pontuacao);
                    historicoJogador.put(nomeUsuario, registros);
                }

                checkBox1.setEnabled(false);
                checkBox2.setEnabled(false);
                checkBox3.setEnabled(false);
                checkBox4.setEnabled(false);
            };

            checkBox1.setOnClickListener(validarResposta);
            checkBox2.setOnClickListener(validarResposta);
            checkBox3.setOnClickListener(validarResposta);
            checkBox4.setOnClickListener(validarResposta);

            perguntasLayout.addView(perguntaView);
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

    private void atualizarCoracoes() {
        StringBuilder coracoes = new StringBuilder();
        for (int i = 0; i < tentativasRestantes; i++) {
            coracoes.append("❤ ");
        }
        textViewCoracoes.setText(coracoes.toString());
    }


    private void finalizarJogo() {
        Toast.makeText(this, "Jogo encerrado! Confira seus resultados.", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(JogadasPerguntas.this, ResultadoActivity.class);
        intent.putExtra("elementos", elementosSelecionados);
        intent.putExtra("tentativas", tentativasUsuario);
        intent.putExtra("pontuacao", pontuacao);
        intent.putExtra("historico", historicoJogador);
        startActivity(intent);
        finish();
    }


}
