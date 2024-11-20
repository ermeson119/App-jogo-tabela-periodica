package com.example.ermesonbalbinotandrade.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ermesonbalbinotandrade.R;
import com.example.ermesonbalbinotandrade.model.entity.TabelaPeriodica;

import java.util.ArrayList;

public class JogadasPerguntas extends AppCompatActivity {

    private LinearLayout perguntasLayout;
    private TextView textViewCoracoes, textViewPontuacao;
    private Button btnFinalizar;
    private ArrayList<TabelaPeriodica> elementosSelecionados;
    private ArrayList<String> tentativasUsuario = new ArrayList<>();

    private int tentativasRestantes = 3; // Número inicial de corações
    private int pontuacao = 0; // Pontuação do usuário

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogadas_perguntas);

        perguntasLayout = findViewById(R.id.perguntasLayout);
        textViewCoracoes = findViewById(R.id.textViewCoracoes);
        textViewPontuacao = findViewById(R.id.textViewPontuacao);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        // Recebe os dados enviados da tela anterior
        elementosSelecionados = (ArrayList<TabelaPeriodica>) getIntent().getSerializableExtra("elementos");

        if (elementosSelecionados == null || elementosSelecionados.isEmpty()) {
            Toast.makeText(this, "Nenhum item recebido!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Configura os corações e pontuação iniciais
        atualizarCoracoes();
        textViewPontuacao.setText("Pontuação: " + pontuacao);

        // Gera perguntas para cada elemento
        gerarPerguntas();

        // Botão para finalizar e ir para ResultadoActivity
        btnFinalizar.setOnClickListener(v -> finalizarJogo());
    }

    private void gerarPerguntas() {
        for (TabelaPeriodica elemento : elementosSelecionados) {
            // Cria um layout para cada pergunta
            View perguntaView = getLayoutInflater().inflate(R.layout.item_pergunta, null);

            TextView textViewElemento = perguntaView.findViewById(R.id.textViewElemento);
            CheckBox checkBox1 = perguntaView.findViewById(R.id.checkBox1);
            CheckBox checkBox2 = perguntaView.findViewById(R.id.checkBox2);
            CheckBox checkBox3 = perguntaView.findViewById(R.id.checkBox3);
            CheckBox checkBox4 = perguntaView.findViewById(R.id.checkBox4);

            // Define o nome do elemento como título da pergunta
            textViewElemento.setText("Qual é o número atômico do elemento: " + elemento.getSigla());

            // Carrega as perguntas do XML
            String[] opcoes = carregarPerguntas(elemento.getNome());

            if (opcoes.length != 4) {
                Toast.makeText(this, "Erro ao carregar perguntas para " + elemento.getNome(), Toast.LENGTH_SHORT).show();
                return;
            }

            // Define as opções nos checkboxes
            checkBox1.setText(opcoes[0]); // Resposta correta
            checkBox2.setText(opcoes[1]);
            checkBox3.setText(opcoes[2]);
            checkBox4.setText(opcoes[3]);

            // Listener para validar resposta
            View.OnClickListener validarResposta = v -> {
                CheckBox selecionado = (CheckBox) v;
                String respostaSelecionada = selecionado.getText().toString();
                String respostaCorreta = opcoes[0]; // Sempre a primeira opção é correta

                // Armazena a tentativa do usuário
                tentativasUsuario.add(respostaSelecionada);

                if (respostaSelecionada.equals(respostaCorreta)) {
                    pontuacao += 10;
                    textViewPontuacao.setText("Pontuação: " + pontuacao);
                    Toast.makeText(this, "Correto!", Toast.LENGTH_SHORT).show();
                } else {
                    tentativasRestantes--;
                    atualizarCoracoes();
                    Toast.makeText(this, "Errado! O correto é: " + respostaCorreta, Toast.LENGTH_SHORT).show();
                    if (tentativasRestantes == 0) {
                        finalizarJogo(); // Encerra o jogo após 3 erros
                        return;
                    }
                }

                // Desabilitar todos os checkboxes após a resposta
                checkBox1.setEnabled(false);
                checkBox2.setEnabled(false);
                checkBox3.setEnabled(false);
                checkBox4.setEnabled(false);
            };

            checkBox1.setOnClickListener(validarResposta);
            checkBox2.setOnClickListener(validarResposta);
            checkBox3.setOnClickListener(validarResposta);
            checkBox4.setOnClickListener(validarResposta);

            // Adiciona a pergunta ao layout principal
            perguntasLayout.addView(perguntaView);
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

    private void atualizarCoracoes() {
        StringBuilder coracoes = new StringBuilder();
        for (int i = 0; i < tentativasRestantes; i++) {
            coracoes.append("❤ ");
        }
        textViewCoracoes.setText(coracoes.toString());
    }

    private void finalizarJogo() {
        // Exibe mensagem de jogo encerrado
        Toast.makeText(this, "Jogo encerrado! Confira seus resultados.", Toast.LENGTH_LONG).show();

        // Redireciona para ResultadoActivity
        Intent intent = new Intent(JogadasPerguntas.this, ResultadoActivity.class);
        intent.putExtra("elementos", elementosSelecionados); // Passa os elementos
        intent.putExtra("tentativas", tentativasUsuario); // Passa as tentativas do usuário
        intent.putExtra("pontuacao", pontuacao); // Passa a pontuação
        startActivity(intent);
        finish(); // Fecha a tela atual
    }
}
