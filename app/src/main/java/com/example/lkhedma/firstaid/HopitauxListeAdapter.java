package com.example.lkhedma.firstaid;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LKHEDMA on 20/05/2017.
 */

public class HopitauxListeAdapter extends ArrayAdapter {
    List hopitauxList = new ArrayList<>();

    public HopitauxListeAdapter(Context context, int resource){
        super(context,resource);
    }

    static class LayoutHandler{
        ImageView icon;
        TextView nom, quartier, numero;
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        hopitauxList.add(object);
    }

    @Override
    public int getCount() {
        return hopitauxList.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return hopitauxList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        HopitauxListeAdapter.LayoutHandler layoutHandler;

        if(row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.item_hopital_list, parent, false);
            layoutHandler = new LayoutHandler();

            layoutHandler.icon = (ImageView) row.findViewById(R.id.list_hopital_icon);
            layoutHandler.nom = (TextView) row.findViewById(R.id.list_hopital_nom);
            layoutHandler.quartier = (TextView) row.findViewById(R.id.list_hopital_quartier);
            layoutHandler.numero = (TextView) row.findViewById(R.id.list_hopital_numero);

            row.setTag(layoutHandler);
        }else {
            layoutHandler = (HopitauxListeAdapter.LayoutHandler) row.getTag();
        }

        Hopital hopital = (Hopital) getItem(position);

        String uri = hopital.getPhoto().toString();
        int iconRecource = getContext().getResources().getIdentifier(uri, null, getContext().getPackageName());

        Drawable res = getContext().getResources().getDrawable(iconRecource);
        layoutHandler.icon.setImageDrawable(res);

        layoutHandler.nom.setText(hopital.getNom());
        layoutHandler.quartier.setText("Quartierb : "+hopital.getQuartier());
        layoutHandler.numero.setText(hopital.getNumero());

        return row;
        }

}
