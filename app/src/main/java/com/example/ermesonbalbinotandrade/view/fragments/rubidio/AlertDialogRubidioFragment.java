package com.example.ermesonbalbinotandrade.view.fragments.rubidio;

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

public class AlertDialogRubidioFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_rubidio, null);
        TextView textViewNomeRubidio = view.findViewById(R.id.textViewNomeRubidio);
        TextView textViewSiglaRubidio = view.findViewById(R.id.textViewSiglaRubidio);
        TextView textViewMassaAtomicaRubidio = view.findViewById(R.id.textViewMassaAtomicaRubidio);

        Button buttonFechar = view.findViewById(R.id.buttonFechar);
        TabelaPeriodica tabelaPeriodica = new TabelaPeriodica("Rubidio", "Rb","85,47 u");


        textViewNomeRubidio.setText(tabelaPeriodica.getNome());
        textViewSiglaRubidio.setText(tabelaPeriodica.getSigla());
        textViewMassaAtomicaRubidio.setText(tabelaPeriodica.getMassaAtomica());

        buttonFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        builder.setView(view);
        return builder.create();

    }
}
