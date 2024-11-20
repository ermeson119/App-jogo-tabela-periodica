package com.example.ermesonbalbinotandrade.model.fragments.cesio;

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

public class AlertDialogCesioFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_cesio, null);
        TextView textViewNomeCesio = view.findViewById(R.id.textViewNomeCesio);
        TextView textViewSiglaCesio = view.findViewById(R.id.textViewSiglaCesio);
        TextView textViewMassaAtomicaCesio = view.findViewById(R.id.textViewMassaAtomicaCesio);

        Button buttonFechar = view.findViewById(R.id.buttonFechar);
        TabelaPeriodica tabelaPeriodica = new TabelaPeriodica("Cesio", "Cs","132,91 u");


        textViewNomeCesio.setText(tabelaPeriodica.getNome());
        textViewSiglaCesio.setText(tabelaPeriodica.getSigla());
        textViewMassaAtomicaCesio.setText(tabelaPeriodica.getMassaAtomica());

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
