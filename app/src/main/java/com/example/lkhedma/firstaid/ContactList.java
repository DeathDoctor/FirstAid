package com.example.lkhedma.firstaid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

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

        if (cursor.moveToNext()){
            do{
                String nom, specification;
                int num;
                nom = cursor.getString(0);
                num = cursor.getInt(1);
                specification = cursor.getString(2);

                Contact contact = new Contact(nom, num, specification);
                contactAdapter.add(contact);

            }while (cursor.moveToNext());
        }

    }

    public void addContact(View view){
        Intent intent = new Intent(ContactList.this, NouveauContact.class);
        startActivity(intent);
    }
}
