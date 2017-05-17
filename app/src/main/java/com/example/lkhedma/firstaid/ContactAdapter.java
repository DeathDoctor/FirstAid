package com.example.lkhedma.firstaid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LKHEDMA on 16/05/2017.
 */

public class ContactAdapter extends ArrayAdapter {
    List contactList = new ArrayList<>();

    public ContactAdapter(Context context, int resource){

        super(context, resource);
    }

    static class LayoutHandler{
        TextView nomContact, numContact, specificationContact;
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        contactList.add(object);
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        ContactAdapter.LayoutHandler layoutHandler;

        if(row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.item_contact_list, parent, false);

            layoutHandler = new LayoutHandler();
            layoutHandler.nomContact = (TextView) row.findViewById(R.id.contact_id);
            layoutHandler.numContact = (TextView) row.findViewById(R.id.contact_num);
            layoutHandler.specificationContact = (TextView) row.findViewById(R.id.contact_specification);

            row.setTag(layoutHandler);
        }else {
            layoutHandler = (ContactAdapter.LayoutHandler) row.getTag();
        }

        Contact contact = (Contact) this.getItem(position);

        layoutHandler.nomContact.setText(contact.getNom());
        layoutHandler.numContact.setText(String.valueOf(contact.getNumero()));
        layoutHandler.specificationContact.setText(contact.getSpecification());

        return row;

    }
}
