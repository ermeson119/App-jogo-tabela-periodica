package com.example.ermesonbalbinotandrade.view.fragments.menu;

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

                    Intent intent = new Intent(getActivity(), EntradaUsuarioActivity.class);
                    startActivity(intent);
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                })
                .setNegativeButton("Não", (dialog, which) -> {
                    dialog.dismiss();
                });

        return builder.create();
    }
}
