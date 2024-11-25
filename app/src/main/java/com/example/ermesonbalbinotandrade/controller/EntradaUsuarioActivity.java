package com.example.ermesonbalbinotandrade.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ermesonbalbinotandrade.R;
import com.example.ermesonbalbinotandrade.view.adpter.TabelaAdapeter;
import com.example.ermesonbalbinotandrade.model.entity.Dados;
import com.example.ermesonbalbinotandrade.model.entity.TabelaPeriodica;

import java.util.ArrayList;
import java.util.Random;

public class EntradaUsuarioActivity extends AppCompatActivity  {

    private EditText editTextNomeUsuario, editTextValor;
    private Button btnGerarSelecao;
    private ListView listViewItensSelecionados;
    private ArrayList<TabelaPeriodica> listaElementoCompleta;
    private ArrayList<TabelaPeriodica> listaSelecionada;
    private TabelaAdapeter adapter;
    private Random random;
    private Button btnProximaTela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_usuario);

        editTextNomeUsuario = findViewById(R.id.EditTextNomeUsuario);
        editTextValor = findViewById(R.id.EditTextValor);
        btnGerarSelecao = findViewById(R.id.btnGerarSelecao);
        listViewItensSelecionados = findViewById(R.id.ListViewItensSelecionados);
        btnProximaTela = findViewById(R.id.btnProximaTela);


        random = new Random();

        listaElementoCompleta = Dados.obterAdapter();

        btnGerarSelecao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeUsuario = editTextNomeUsuario.getText().toString().trim();
                String valorTexto = editTextValor.getText().toString().trim();

                if (nomeUsuario.isEmpty() || valorTexto.isEmpty()) {
                    Toast.makeText(EntradaUsuarioActivity.this, "Preencha todos os campos corretamente.", Toast.LENGTH_SHORT).show();
                    return;
                }
                gerarSelecao();
            }
        });

        btnProximaTela.setOnClickListener(v -> {
            if (listaSelecionada == null || listaSelecionada.isEmpty()) {
                Toast.makeText(this, "Nenhum item foi selecionado!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(EntradaUsuarioActivity.this, JogadasPerguntas.class);
            intent.putExtra("nomeUsuario", editTextNomeUsuario.getText().toString().trim());
            intent.putExtra("elementos", listaSelecionada);
            startActivity(intent);
        });

    }

    private void gerarSelecao() {
        String nomeUsuario = editTextNomeUsuario.getText().toString().trim();
        String valorTexto = editTextValor.getText().toString().trim();
        int valor = Integer.parseInt(valorTexto);

        if (nomeUsuario.isEmpty() || valorTexto.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos corretamente.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (valor < 1 || valor > 7) {
            Toast.makeText(this, "O valor deve estar no intervalo entre 1 a 7.", Toast.LENGTH_SHORT).show();
            return;
        }

        listaSelecionada = new ArrayList<>();
        ArrayList<TabelaPeriodica> copiaLista = new ArrayList<>(listaElementoCompleta);
        for (int i = 0; i < valor; i++) {
            listaSelecionada.add(copiaLista.remove(random.nextInt(copiaLista.size())));
        }

        // Atualiza a ListView
        adapter = new TabelaAdapeter(this, listaSelecionada);
        listViewItensSelecionados.setAdapter(adapter);

        Toast.makeText(this, "Itens selecionados para " + nomeUsuario, Toast.LENGTH_SHORT).show();
    }
}
