package com.example.ermesonbalbinotandrade.view.fragments.potassio;

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

public class AlertDialogPotasioFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_potassio, null);
        TextView textViewNomePotassio = view.findViewById(R.id.textViewNomePotassio);
        TextView textViewSiglaPotassio = view.findViewById(R.id.textViewSiglaPotassio);
        TextView textViewMassaAtomicaPotassio = view.findViewById(R.id.textViewMassaAtomicaPotassio);

        Button buttonFechar = view.findViewById(R.id.buttonFechar);
        TabelaPeriodica tabelaPeriodica = new TabelaPeriodica("Potassio", "K","39,10 u");


        textViewNomePotassio.setText(tabelaPeriodica.getNome());
        textViewSiglaPotassio.setText(tabelaPeriodica.getSigla());
        textViewMassaAtomicaPotassio.setText(tabelaPeriodica.getMassaAtomica());

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
