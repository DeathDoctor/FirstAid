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

public class PreventionInstructionAdapter extends ArrayAdapter {

    List instructionListe = new ArrayList<>();

    public PreventionInstructionAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler {
        TextView titre, sousTitre, description;
        ImageView icon1, icon2;
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        instructionListe.add(object);
    }

    @Override
    public int getCount() {
        return instructionListe.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return instructionListe.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        PreventionInstructionAdapter.LayoutHandler layoutHandler;

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.item_prevention_instruction, parent, false);

            layoutHandler = new LayoutHandler();

            layoutHandler.titre = (TextView) row.findViewById(R.id.instruction_titre);
            layoutHandler.sousTitre = (TextView) row.findViewById(R.id.instruction_sous_titre);
            layoutHandler.icon1 = (ImageView) row.findViewById(R.id.prevention_icon1);
            layoutHandler.icon2 = (ImageView) row.findViewById(R.id.prevention_icon2);
            layoutHandler.description = (TextView) row.findViewById(R.id.prevention_description);

            row.setTag(layoutHandler);
        } else {
            layoutHandler = (PreventionInstructionAdapter.LayoutHandler) row.getTag();
        }

        PreventionInstruction preventionInstruction = (PreventionInstruction) this.getItem(position);

        layoutHandler.titre.setText(preventionInstruction.getTitre());
        layoutHandler.sousTitre.setText(preventionInstruction.getSousTitre());
        layoutHandler.description.setText(preventionInstruction.getDescription().toString());

        String uri1 = preventionInstruction.getIcon1().toString();
        int iconRecource = getContext().getResources().getIdentifier(uri1, null, getContext().getPackageName());

        Drawable res = getContext().getResources().getDrawable(iconRecource);
        layoutHandler.icon1.setImageDrawable(res);

        String uri2 = preventionInstruction.getIcon2().toString();
        int iconRecource2 = getContext().getResources().getIdentifier(uri2, null, getContext().getPackageName());

        Drawable res2 = getContext().getResources().getDrawable(iconRecource2);
        layoutHandler.icon2.setImageDrawable(res2);

        return row;
    }
}
