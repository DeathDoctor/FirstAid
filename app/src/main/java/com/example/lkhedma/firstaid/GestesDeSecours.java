package com.example.lkhedma.firstaid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class GestesDeSecours extends AppCompatActivity {

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
        setContentView(R.layout.activity_gestes_de_secours);
        Intent intent = getIntent();
        String requete = "SELECT "+FirstAidManager.GesteSecourManager.ICON_GESTE_SECOUR+ ", "+ FirstAidManager.GesteSecourManager.DESCRIPTION_GESTE_SECOUR+ " FROM " +FirstAidManager.GesteSecourManager.GESTE_SECOUR_TABLE_NAME+ " WHERE "+FirstAidManager.GesteSecourManager.DIAGNOSTIC_GESTE_SECOUR+" = ?";
        String diagnostique = intent.getStringExtra("diagnostiqueAccident");
        String gravite = intent.getStringExtra("gravite");
        textView = (TextView) findViewById(R.id.txt);
        switch (gravite){
            case "faible":
                textView.setBackgroundColor(Color.parseColor("#00C853"));
                break;
            case "modere":
                textView.setBackgroundColor(Color.parseColor("#FF9800"));
                break;
            case "grave":
                textView.setBackgroundColor(Color.parseColor("#D32F2F"));
                break;
        }

        textView.setText(diagnostique);

        switch (diagnostique) {
            case "Fracture non déplacée":
            case  "Fracture déplacée":
            case "Entorse":
                diagnostique = "Traumatisme du membre";
                break;
            case "Luxation":
                diagnostique = "Traumatisme du membre (luxation)";
                break;
            case "Caustique fort (acide fort)":
            case "Caustique fort (Base fort)":
            case "Caustique moyen (acide faible)":
            case "Diagnostic non identifié":
                diagnostique = "Intoxication";
                break;
        }


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
