package com.example.ermesonbalbinotandrade.view.fragments.francio;

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

public class AlertDialogFrancioFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_francio, null);
        TextView textViewNomeFrancio = view.findViewById(R.id.textViewNomeFrancio);
        TextView textViewSiglaFrancio = view.findViewById(R.id.textViewSiglaFrancio);
        TextView textViewMassaAtomicaFrancio = view.findViewById(R.id.textViewMassaAtomicaFrancio);

        Button buttonFechar = view.findViewById(R.id.buttonFechar);
        TabelaPeriodica tabelaPeriodica = new TabelaPeriodica("Francio", "Fr","223 u");


        textViewNomeFrancio.setText(tabelaPeriodica.getNome());
        textViewSiglaFrancio.setText(tabelaPeriodica.getSigla());
        textViewMassaAtomicaFrancio.setText(tabelaPeriodica.getMassaAtomica());

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
