package com.example.ermesonbalbinotandrade.view.fragments.hidrogenio;

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

public class AlertDialogHidrogenioFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_hidrogenio, null);
        TextView textViewNomeHidrogenio = view.findViewById(R.id.textViewNomeHidrogenio);
        TextView textViewSiglaHidrogenio = view.findViewById(R.id.textViewSiglaHidrogenio);
        TextView textViewMassaAtomicaHidrogenio = view.findViewById(R.id.textViewMassaAtomicaHidrogenio);

        Button buttonFechar = view.findViewById(R.id.buttonFechar);
        TabelaPeriodica tabelaPeriodica = new TabelaPeriodica("Hidrogenio", "H","1,008 u");


        textViewNomeHidrogenio.setText(tabelaPeriodica.getNome());
        textViewSiglaHidrogenio.setText(tabelaPeriodica.getSigla());
        textViewMassaAtomicaHidrogenio.setText(tabelaPeriodica.getMassaAtomica());

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
