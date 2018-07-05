package com.example.lkhedma.firstaid;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NouveauContact extends AppCompatActivity {

    private EditText nom;
    private EditText numero;
    private EditText specification;

    Context context = this;
    DbHelper db;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Nouveau contact");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_contact);



        nom = (EditText) findViewById(R.id.nom_contact);
        numero = (EditText) findViewById(R.id.numero_contact);
        specification = (EditText) findViewById(R.id.specification_contact);



    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public boolean valid() {

        boolean valid = true;
        if(isEmpty(nom)){
            nom.setError("Introduisez le nom");
            valid = false;
        }
        if (isEmpty(numero)) {
            numero.setError("Introduisez le numéro");
            valid = false;
        }
        if (isEmpty(specification)) {
            specification.setError("Introduisez la spécification");
            valid = false;
        }

        return valid;

    }

    public void addContact(View view) {
        if (valid()){

            db = new DbHelper(context);
            sqLiteDatabase = db.getWritableDatabase();
            Contact newContact = new Contact(nom.getText().toString(), numero.getText().toString(), specification.getText().toString());
            db.insertIntoContacts(newContact, sqLiteDatabase);
            Toast.makeText(getBaseContext(),"Contact ajoute", Toast.LENGTH_LONG).show();
            db.close();
            Intent intent = new Intent(NouveauContact.this, ContactList.class);
            startActivity(intent);
            finish();


        }


//        db = new DbHelper(context);
//        sqLiteDatabase = db.getWritableDatabase();
//        Contact newContact = new Contact(nom.getText().toString(), Integer.parseInt(numero.getText().toString()), specification.getText().toString());
//        db.insertIntoContacts(newContact, sqLiteDatabase);
//        Toast.makeText(getBaseContext(),"Contact ajoute", Toast.LENGTH_LONG).show();
//        db.close();
//        Intent intent = new Intent(NouveauContact.this, ContactList.class);
//        startActivity(intent);
//

    }




}
