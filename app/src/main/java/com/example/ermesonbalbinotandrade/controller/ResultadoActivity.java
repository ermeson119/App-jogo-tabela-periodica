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
import com.example.ermesonbalbinotandrade.model.fragments.menu.HistoricoDialogFragment;
import com.example.ermesonbalbinotandrade.model.fragments.menu.JogarNovamenteDialogFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultadoActivity extends AppCompatActivity {
    private TableLayout tableLayoutGabarito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        // Inicializa a Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Resultado");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inicializa as views
        tableLayoutGabarito = findViewById(R.id.tableLayoutGabarito);

        // Receber os dados da tela anterior
        ArrayList<TabelaPeriodica> elementos = (ArrayList<TabelaPeriodica>) getIntent().getSerializableExtra("elementos");
        ArrayList<String> tentativas = getIntent().getStringArrayListExtra("tentativas");

        // Gerar gabarito
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
        String itemName = item.getTitle().toString();

        switch (itemName) {
            case "Jogar Novamente":
                new JogarNovamenteDialogFragment().show(getSupportFragmentManager(), "jogarNovamente");
                return true;

            case "Mostrar Histórico":
                mostrarHistorico(); // Chamando o método mostrarHistorico
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mostrarHistorico() {
        HashMap<String, ArrayList<String>> historicoJogador = obterHistoricoJogador();

        if (historicoJogador == null || historicoJogador.isEmpty()) {
            // Mostra uma mensagem se o histórico estiver vazio
            Toast.makeText(this, "Nenhum histórico disponível.", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<String> historico = new ArrayList<>();

        for (Map.Entry<String, ArrayList<String>> entrada : historicoJogador.entrySet()) {
            String jogada = entrada.getKey(); // Nome da jogada, ex: "Jogada 1"
            ArrayList<String> detalhes = entrada.getValue(); // Detalhes, ex: ["Acertos: 3", "Erros: 1"]

            // Formata os detalhes de forma legível
            StringBuilder detalhesFormatados = new StringBuilder();
            for (String detalhe : detalhes) {
                detalhesFormatados.append(detalhe).append("\n");
            }

            historico.add(jogada + "\n" + detalhesFormatados.toString().trim()); // Remove espaços extras
        }

        // Passa o histórico formatado para o DialogFragment
        HistoricoDialogFragment dialogFragment = HistoricoDialogFragment.newInstance(historico);
        dialogFragment.show(getSupportFragmentManager(), "mostrarHistorico");
    }



    private HashMap<String, ArrayList<String>> obterHistoricoJogador() {
        Intent intent = getIntent();
        if (intent.hasExtra("historico")) {
            return (HashMap<String, ArrayList<String>>) intent.getSerializableExtra("historico");
        }
        return new HashMap<>(); // Retorna um HashMap vazio para evitar NullPointerException
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
