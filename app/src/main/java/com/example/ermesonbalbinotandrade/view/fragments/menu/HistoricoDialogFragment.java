package com.example.ermesonbalbinotandrade.view.fragments.menu;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ermesonbalbinotandrade.R;

import java.util.ArrayList;

public class HistoricoDialogFragment extends DialogFragment {
    //Define uma constante que será usada como chave para armazenar e recuperar o histórico
    private static final String ARG_HISTORICO = "historico"; //é usado como uma chave única para identificar o dado associado a ela no objeto Bundle


    public static HistoricoDialogFragment newInstance(ArrayList<String> historico) {
        HistoricoDialogFragment fragment = new HistoricoDialogFragment(); //Cria uma nova instância da classe.
        Bundle args = new Bundle(); //Cria um Bundle para armazenar os dados.
        args.putStringArrayList(ARG_HISTORICO, historico); //Armazena a lista de strings no Bundle usando a chave ARG_HISTORICO.
        fragment.setArguments(args); //Associa o Bundle ao fragmento.
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //getStringArrayList(ARG_HISTORICO): Recupera a lista usando a chave ARG_HISTORICO.
        // new ArrayList<>(): Garante que a variável não seja nula.
        ArrayList<String> historico = getArguments() != null ? getArguments().getStringArrayList(ARG_HISTORICO) : new ArrayList<>();

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_historico, null);

        ListView listView = view.findViewById(R.id.listViewHistorico);
        ArrayAdapter<String> adapter = new ArrayAdapter<>( //Cria um adaptador para exibir a lista.
                requireContext(),  // Obtém o contexto da atividade associada ao fragmento.
                android.R.layout.simple_list_item_1, // Layout padrão do Android para exibir itens em uma lista.
                historico  //Lista de strings a ser exibida.
        );
        listView.setAdapter(adapter); //Associa o adaptador ao ListView.

        // Configurar botão Fechar
        Button buttonFechar = view.findViewById(R.id.buttonFechar);
        buttonFechar.setOnClickListener(v -> dismiss());

        // Construir o Dialog
        return new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("Histórico")
                .setView(view)
                .create();
    }
}
