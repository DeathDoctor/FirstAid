package com.example.lkhedma.firstaid;

import android.content.Context;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_contact);



        nom = (EditText) findViewById(R.id.nom_contact);
        numero = (EditText) findViewById(R.id.numero_contact);
        specification = (EditText) findViewById(R.id.specification_contact);


    }

    public void addContact(View view) {
        db = new DbHelper(context);
        sqLiteDatabase = db.getWritableDatabase();
        Contact newContact = new Contact(nom.getText().toString(), Integer.parseInt(numero.getText().toString()), specification.getText().toString());
        db.addNewContact(newContact, sqLiteDatabase);
        Toast.makeText(getBaseContext(),"Contact ajoute", Toast.LENGTH_LONG).show();
        db.close();
    }

    public void essaie(String[] nombre_diagnostic) {
        String requete;
        switch (nombre_diagnostic.length){
            case 1:
                requete = "SELECT gete_faire WHERE diagnostic = "+nombre_diagnostic[0]+";";
                break;
            case 2:
                requete = "SELECT gete_faire WHERE diagnostic where = "+nombre_diagnostic[0]+" OR  diagnostic = "+nombre_diagnostic[1]+";";
        }
    }


}
