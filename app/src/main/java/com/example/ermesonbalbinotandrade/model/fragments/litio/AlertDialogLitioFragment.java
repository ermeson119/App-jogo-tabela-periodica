package com.example.ermesonbalbinotandrade.model.fragments.litio;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ermesonbalbinotandrade.R;
import com.example.ermesonbalbinotandrade.model.entity.TabelaPeriodica;

public class AlertDialogLitioFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_litio, null);
        TextView textViewNomeLitio = view.findViewById(R.id.textViewNomeLitio);
        TextView textViewSiglaLitio = view.findViewById(R.id.textViewSiglaLitio);
        TextView textViewMassaAtomicaLitio = view.findViewById(R.id.textViewMassaAtomicaLitio);

        Button buttonFechar = view.findViewById(R.id.buttonFechar);
        TabelaPeriodica tabelaPeriodica = new TabelaPeriodica("Litio", "Li","132");


        textViewNomeLitio.setText(tabelaPeriodica.getNome());
        textViewSiglaLitio.setText(tabelaPeriodica.getSigla());
        textViewMassaAtomicaLitio.setText(tabelaPeriodica.getMassaAtomica());

        buttonFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Dialogo fechado", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        builder.setView(view);
        return builder.create();

    }
}
