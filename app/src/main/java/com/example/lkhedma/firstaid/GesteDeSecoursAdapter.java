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
 * Created by LKHEDMA on 06/05/2017.
 */

public class GesteDeSecoursAdapter extends ArrayAdapter {

    List gestesDeSecours = new ArrayList<>();
    public GesteDeSecoursAdapter(Context context, int resource) {
        super(context,resource);

    }

    static class LayoutHandler{
        ImageView  icon;
        TextView description;
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        gestesDeSecours.add(object);
    }

    @Override
    public int getCount() {
        return gestesDeSecours.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return gestesDeSecours.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        GesteDeSecoursAdapter.LayoutHandler layoutHandler;

        if(row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.item_geste_secour, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.icon = (ImageView) row.findViewById(R.id.icon_geste);
            layoutHandler.description = (TextView) row.findViewById(R.id.description_geste);

            row.setTag(layoutHandler);

        } else {
            layoutHandler = (GesteDeSecoursAdapter.LayoutHandler) row.getTag();
        }

        GesteSecours gesteSecours = (GesteSecours) this.getItem(position);
        String uri = gesteSecours.getIcon().toString();
        int iconRecource = getContext().getResources().getIdentifier(uri, null, getContext().getPackageName());

        Drawable res = getContext().getResources().getDrawable(iconRecource);
        layoutHandler.icon.setImageDrawable(res);

        layoutHandler.description.setText(gesteSecours.getDescription());

        return row;

    }
}
