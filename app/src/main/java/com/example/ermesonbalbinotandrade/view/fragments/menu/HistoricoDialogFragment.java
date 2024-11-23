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

    private static final String ARG_HISTORICO = "historico";

    public static HistoricoDialogFragment newInstance(ArrayList<String> historico) {
        HistoricoDialogFragment fragment = new HistoricoDialogFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_HISTORICO, historico);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        ArrayList<String> historico = getArguments() != null ? getArguments().getStringArrayList(ARG_HISTORICO) : new ArrayList<>();

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_historico, null);

        // Configurar ListView
        ListView listView = view.findViewById(R.id.listViewHistorico);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                historico
        );
        listView.setAdapter(adapter);

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
