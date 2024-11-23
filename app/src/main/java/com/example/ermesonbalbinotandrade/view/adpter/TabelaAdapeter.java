package com.example.ermesonbalbinotandrade.view.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ermesonbalbinotandrade.R;
import com.example.ermesonbalbinotandrade.model.entity.TabelaPeriodica;

import java.util.ArrayList;

public class TabelaAdapeter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<TabelaPeriodica> intensPriodica;

    public TabelaAdapeter(Context context, ArrayList<TabelaPeriodica> intensPriodica) {
        inflater = LayoutInflater.from(context);
        this.intensPriodica = intensPriodica;
    }

    @Override
    public int getCount() {
        return intensPriodica.size();
    }

    @Override
    public TabelaPeriodica getItem(int position) {
        return intensPriodica.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tabela_item, parent, false);
        }

        TabelaPeriodica elemento = getItem(position);

        // Configura a imagem
        ImageView imagemElemento = convertView.findViewById(R.id.elemento_imagem);
        imagemElemento.setImageResource(elemento.getImagemEle());

        // Configura o nome do elemento
        TextView nomeElemento = convertView.findViewById(R.id.planeta_nome);
        nomeElemento.setText(elemento.getNome());

        // Configura a sigla do elemento
        TextView siglaElemento = convertView.findViewById(R.id.elemento_sigla);
        siglaElemento.setText(elemento.getSigla());

        return convertView;
    }

}
