package com.example.lkhedma.firstaid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactList extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    Cursor cursor;
    ContactAdapter contactAdapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        setTitle("Liste des contacts");


        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactList.this, NouveauContact.class);
                startActivity(intent);
                finish();

            }
        });
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

                TextView nomField = (TextView) view.findViewById(R.id.contact_id);
                final String nom = nomField.getText().toString();

                TextView numField = (TextView) view.findViewById(R.id.contact_num);
                final String numero = numField.getText().toString();

                TextView specField = (TextView) view.findViewById(R.id.contact_specification);
                String spec = specField.getText().toString();

                AlertDialog.Builder mbuilder = new  AlertDialog.Builder(ContactList.this);
                View mView = getLayoutInflater().inflate(R.layout.contact_information, null);

                final TextView nomDia = (TextView) mView.findViewById(R.id.nom_dia);
                nomDia.setText(nom);

                final TextView numDia = (TextView) mView.findViewById(R.id.num_dia);
                numDia.setText(numero);

                TextView specDia = (TextView) mView.findViewById(R.id.spec_dia);
                specDia.setText(spec);

                LinearLayout appel = (LinearLayout) mView.findViewById(R.id.appel);
                LinearLayout supp = (LinearLayout) mView.findViewById(R.id.supp);

                mbuilder.setView(mView);
                final AlertDialog dialog = mbuilder.create();

                appel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri call = Uri.parse("tel:" + numDia.getText());
                        Intent intent = new Intent(Intent.ACTION_DIAL, call);
                        startActivity(intent);
                    }
                });

                supp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dbHelper.deleteContact(sqLiteDatabase, nom);
                        Toast.makeText(ContactList.this, "Contact supprimé", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                        finish();
                        startActivity(getIntent());

                    }
                });

                dialog.show();





            }
        });

    }

//    public void addContact(View view){
//        Intent intent = new Intent(ContactList.this, NouveauContact.class);
//        startActivity(intent);
//        finish();
//    }
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
    return true;
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.urgence:
                Uri call = Uri.parse("tel:" + "14");
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
