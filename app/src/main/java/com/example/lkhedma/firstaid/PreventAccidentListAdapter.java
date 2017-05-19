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
 * Created by LKHEDMA on 18/05/2017.
 */

public class PreventAccidentListAdapter extends ArrayAdapter {

    List preventionAccidentList = new ArrayList<>();

    public PreventAccidentListAdapter(Context context, int resource) {
        super(context, resource);

    }

    static class LayoutHandler{
        ImageView icon;
        TextView nom;
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        preventionAccidentList.add(object);
    }

    @Override
    public int getCount() {
        return preventionAccidentList.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return preventionAccidentList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        PreventAccidentListAdapter.LayoutHandler layoutHandler;

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.item_prevention_accident, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.icon = (ImageView) row.findViewById(R.id.icon_prevention_accident);
            layoutHandler.nom = (TextView) row.findViewById(R.id.prevention_nom_accident);

            row.setTag(layoutHandler);
        } else {
            layoutHandler = (PreventAccidentListAdapter.LayoutHandler) row.getTag();
        }

        Accident accident = (Accident) this.getItem(position);

        String uri = accident.getIcon().toString();
        int iconRecource = getContext().getResources().getIdentifier(uri, null, getContext().getPackageName());

        Drawable res = getContext().getResources().getDrawable(iconRecource);
        layoutHandler.icon.setImageDrawable(res);

        layoutHandler.nom.setText(accident.getNom());

        return row;

    }
}
