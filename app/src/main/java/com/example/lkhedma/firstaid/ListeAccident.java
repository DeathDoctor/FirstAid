package com.example.lkhedma.firstaid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ListeAccident extends AppCompatActivity {


    ListView liste;
    SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    Cursor cursor;
    ListeAccidentAdapter listeAccidentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Liste des accidents");
        setContentView(R.layout.activity_liste_accident);

        liste = (ListView) findViewById(R.id.liste_accident);
        dbHelper = new DbHelper(this.getApplicationContext());
        listeAccidentAdapter = new ListeAccidentAdapter(this.getApplicationContext(), R.layout.item_accident_list);
        liste.setAdapter(listeAccidentAdapter);
        sqLiteDatabase = dbHelper.getReadableDatabase();
        cursor = dbHelper.getAllAccidents(sqLiteDatabase);
        if(cursor.moveToNext()){
            do{

                String nom,id,icon;
                id = cursor.getString(0);
                nom = cursor.getString(1);
                icon = cursor.getString(2);

                Accident accident = new Accident(id, nom, icon);
                listeAccidentAdapter.add(accident);

            }while (cursor.moveToNext());
        }

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
                animation1.setDuration(2000);
                view.startAnimation(animation1);
                TextView tct = (TextView) view.findViewById(R.id.nom_accident);
                String nomAccident = tct.getText().toString();

                Intent intent = null;

                switch (nomAccident) {
                    case "Brulure":
                        intent = new Intent(ListeAccident.this, Brulure.class);
                        break;

                    case "Chute":
                        intent = new Intent(ListeAccident.this, Chute.class);
                        break;

                    case "Intoxication":
                        intent = new Intent(ListeAccident.this, Intoxication.class);
                        break;
                    case "Inhalation":
                        intent = new Intent(ListeAccident.this, Inhalation.class);
                        break;
                }

                startActivity(intent);

                Log.e("hadi", nomAccident);
            }
        });

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
