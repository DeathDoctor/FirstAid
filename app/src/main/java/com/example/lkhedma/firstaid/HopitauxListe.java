package com.example.lkhedma.firstaid;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class HopitauxListe extends AppCompatActivity {

    Spinner spinner;
    ListView liste;
    SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    Cursor cursor;
    HopitauxListeAdapter hopitauxListeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hopitaux_liste);
        spinner = (Spinner) findViewById(R.id.wilaya_spinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.wilaya_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);

        //liste.setAdapter(hopitauxListeAdapter);

        //String wilaya = spinner.getSelectedItem().toString();
        //String requete = "SELECT "+FirstAidManager.HopitalManager.HOPITAL_PHOTO+", "+FirstAidManager.HopitalManager.HOPITAL_NAME+", "+FirstAidManager.HopitalManager.HOPITAL_QUARTIER+", "+FirstAidManager.HopitalManager.HOPITAL_NUMERO+" FROM "+FirstAidManager.HopitalManager.HOPITAL_TABLE_NAME+" WHERE "+FirstAidManager.HopitalManager.HOPITAL_WILAYA+" = ?";
        //cursor = dbHelper.getHospitals(sqLiteDatabase, requete, wilaya);



    }
    public void recherche(View view) {
        String requete = "SELECT "+FirstAidManager.HopitalManager.HOPITAL_PHOTO+", "+FirstAidManager.HopitalManager.HOPITAL_NAME+", "+FirstAidManager.HopitalManager.HOPITAL_QUARTIER+", "+FirstAidManager.HopitalManager.HOPITAL_NUMERO+" FROM "+FirstAidManager.HopitalManager.HOPITAL_TABLE_NAME+" WHERE "+FirstAidManager.HopitalManager.HOPITAL_WILAYA+" = ?";


        String wilaya = spinner.getSelectedItem().toString();
        dbHelper = new DbHelper(this.getApplicationContext());
        hopitauxListeAdapter = new HopitauxListeAdapter(this.getApplicationContext(), R.layout.item_hopital_list);
        sqLiteDatabase = dbHelper.getReadableDatabase();
        liste = (ListView) findViewById(R.id.liste_hopitaux);
        liste.setAdapter(hopitauxListeAdapter);
        cursor = dbHelper.getHospitals(sqLiteDatabase, requete, wilaya);
        if (cursor.moveToNext()) {
            do {
                String icon, nom, quartier, numero;

                icon = cursor.getString(0);
                nom = cursor.getString(1);
                quartier = cursor.getString(2);
                numero = cursor.getString(3);
                Hopital hopital = new Hopital(nom, icon, quartier, numero);
                hopitauxListeAdapter.add(hopital);
            }while (cursor.moveToNext());
        }

        Toast.makeText(this, String.valueOf(spinner.getSelectedItem().toString()), Toast.LENGTH_SHORT).show();


    }



}
