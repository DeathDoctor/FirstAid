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
 * Created by LKHEDMA on 13/04/2017.
 */

public class ListeAccidentAdapter extends ArrayAdapter {


    List liste_accidents = new ArrayList();
    public ListeAccidentAdapter(Context context, int resource) {
        super(context, resource);

    }

    static class LayoutHandler{
        TextView id, nom;
        ImageView icon;
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        liste_accidents.add(object);
    }

    @Override
    public int getCount() {
        return liste_accidents.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return liste_accidents.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View row = convertView;
        LayoutHandler layoutHandler;

        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.item_accident_list, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.id = (TextView) row.findViewById(R.id.id_accident);
            layoutHandler.nom = (TextView) row.findViewById(R.id.nom_accident);
            layoutHandler.icon = (ImageView) row.findViewById(R.id.icon_accident);
            row.setTag(layoutHandler);

        }else {
            layoutHandler = (LayoutHandler) row.getTag();

        }

        Accident accident = (Accident) this.getItem(position);
        layoutHandler.id.setText(accident.getId());
        layoutHandler.nom.setText(accident.getNom());

        String uri = accident.getIcon().toString();
        int iconRecource = getContext().getResources().getIdentifier(uri, null, getContext().getPackageName());

        Drawable res = getContext().getResources().getDrawable(iconRecource);
        layoutHandler.icon.setImageDrawable(res);

        return row;
    }
}
