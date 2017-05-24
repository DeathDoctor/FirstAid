package com.example.lkhedma.firstaid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ContactList extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    Cursor cursor;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Liste des contacts");
        setContentView(R.layout.activity_contact_list);

        listView = (ListView) findViewById(R.id.contacts_list);
        dbHelper = new DbHelper(this.getApplicationContext());
        contactAdapter = new ContactAdapter(this.getApplicationContext(), R.layout.item_contact_list);
        listView.setAdapter(contactAdapter);
        sqLiteDatabase = dbHelper.getReadableDatabase();
        cursor = dbHelper.getAllContacts(sqLiteDatabase);

        if (cursor.moveToNext()) {
            do {
                String nom, specification;
                int num;
                nom = cursor.getString(0);
                num = cursor.getInt(1);
                specification = cursor.getString(2);

                Contact contact = new Contact(nom, num, specification);
                contactAdapter.add(contact);

            } while (cursor.moveToNext());
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
                animation1.setDuration(2000);
                view.startAnimation(animation1);

                TextView numField = (TextView) view.findViewById(R.id.contact_num);
                String numero = numField.getText().toString();


                Uri call = Uri.parse("tel:" + numero);
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent);
            }
        });

    }

    public void addContact(View view){
        Intent intent = new Intent(ContactList.this, NouveauContact.class);
        startActivity(intent);
        finish();
    }
}
