package com.example.lkhedma.firstaid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HopitauxListe extends AppCompatActivity {

    Spinner spinner;
    ListView liste;
    SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    Cursor cursor;
    HopitauxListeAdapter hopitauxListeAdapter;
    String wilaya;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hopitaux_liste);
        setTitle("Liste des hopitaux");
        spinner = (Spinner) findViewById(R.id.wilaya_spinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.wilaya_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);

        //liste.setAdapter(hopitauxListeAdapter);

        button = (Button) findViewById(R.id.chercher);
        // animation code
        final Animation animation = new AlphaAnimation(1,0);
        animation.setDuration(1500);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        button.startAnimation(animation);
        //String wilaya = spinner.getSelectedItem().toString();
        //String requete = "SELECT "+FirstAidManager.HopitalManager.HOPITAL_PHOTO+", "+FirstAidManager.HopitalManager.HOPITAL_NAME+", "+FirstAidManager.HopitalManager.HOPITAL_QUARTIER+", "+FirstAidManager.HopitalManager.HOPITAL_NUMERO+" FROM "+FirstAidManager.HopitalManager.HOPITAL_TABLE_NAME+" WHERE "+FirstAidManager.HopitalManager.HOPITAL_WILAYA+" = ?";
        //cursor = dbHelper.getHospitals(sqLiteDatabase, requete, wilaya);


        liste = (ListView) findViewById(R.id.liste_hopitaux);

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
                animation1.setDuration(2000);
                view.startAnimation(animation1);

                TextView item = (TextView) view.findViewById(R.id.list_hopital_nom);
                String nomHopital = item.getText().toString();
                String requete = "SELECT "+FirstAidManager.HopitalManager.HOPITAL_NAME+", "+
                                            FirstAidManager.HopitalManager.HOPITAL_PHOTO+", "+
                                            FirstAidManager.HopitalManager.HOPITAL_WILAYA+", "+
                                            FirstAidManager.HopitalManager.HOPITAL_QUARTIER+", "+
                                            FirstAidManager.HopitalManager.HOPITAL_NUMERO+", "+
                                            FirstAidManager.HopitalManager.HOPITAL_SITE+", "+
                                            FirstAidManager.HopitalManager.HOPITAL_LAT+", "+
                                            FirstAidManager.HopitalManager.HOPITAL_LONG+" FROM "+
                                            FirstAidManager.HopitalManager.HOPITAL_TABLE_NAME+" WHERE "+
                                            FirstAidManager.HopitalManager.HOPITAL_NAME+" = ?";
                ;

                Intent intent = new Intent(HopitauxListe.this, HopitalInformations.class);
                intent.putExtra("requeteInformationsHopital", requete);
                intent.putExtra("nomHopital", nomHopital);
                startActivity(intent);

            }
        });
        wilaya = spinner.getSelectedItem().toString();



    }
    public void recherche(View view) {
        view.clearAnimation();
        dbHelper = new DbHelper(this.getApplicationContext());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        hopitauxListeAdapter = new HopitauxListeAdapter(this.getApplicationContext(), R.layout.item_hopital_list);
        String requete = "SELECT "+FirstAidManager.HopitalManager.HOPITAL_PHOTO+", "+FirstAidManager.HopitalManager.HOPITAL_NAME+", "+FirstAidManager.HopitalManager.HOPITAL_QUARTIER+", "+FirstAidManager.HopitalManager.HOPITAL_NUMERO+" FROM "+FirstAidManager.HopitalManager.HOPITAL_TABLE_NAME+" WHERE "+FirstAidManager.HopitalManager.HOPITAL_WILAYA+" = ?";
        wilaya = spinner.getSelectedItem().toString();
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
