package com.example.lkhedma.firstaid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class GestesDeSecoursBrulure extends AppCompatActivity {

    ListView liste;
    SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    Cursor cursor;
    GesteDeSecoursAdapter gesteDeSecoursAdapter;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Gestes de secours");
        setContentView(R.layout.activity_gestes_de_secours_brulure);
        Intent intent = getIntent();
        String requete = intent.getStringExtra("requete");
        String diagnostique = intent.getStringExtra("diagnostique");
        textView = (TextView) findViewById(R.id.txt);
        textView.setText(diagnostique);

        liste = (ListView) findViewById(R.id.geste_brulure);
        dbHelper = new DbHelper(this.getApplicationContext());
        gesteDeSecoursAdapter  = new GesteDeSecoursAdapter(this.getApplicationContext(), R.layout.item_geste_secour);
        liste.setAdapter(gesteDeSecoursAdapter);
        sqLiteDatabase = dbHelper.getReadableDatabase();
        cursor = dbHelper.getGestesDeSecours(sqLiteDatabase, requete, diagnostique);
        if (cursor.moveToNext()) {
            do {
                String icon,desc;

                icon = cursor.getString(0);
                desc = cursor.getString(1);

                GesteSecours gesteSecours = new GesteSecours(icon, desc);
                gesteDeSecoursAdapter.add(gesteSecours);
            }while (cursor.moveToNext());
        }

    }
}
