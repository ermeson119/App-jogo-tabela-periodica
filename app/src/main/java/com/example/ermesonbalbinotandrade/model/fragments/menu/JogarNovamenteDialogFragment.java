package com.example.ermesonbalbinotandrade.model.fragments.menu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ermesonbalbinotandrade.controller.EntradaUsuarioActivity;

public class JogarNovamenteDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Jogar Novamente")
                .setMessage("Você deseja Jogar novamente?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    // Reinicia o jogo
                    Intent intent = new Intent(getActivity(), EntradaUsuarioActivity.class);
                    startActivity(intent);
                    if (getActivity() != null) {
                        getActivity().finish(); // Fecha a atividade atual
                    }
                })
                .setNegativeButton("Não", (dialog, which) -> {
                    // Apenas fecha o diálogo
                    dialog.dismiss();
                });

        return builder.create();
    }
}
