package com.example.lkhedma.firstaid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PreventionAccidentList extends AppCompatActivity {

    ListView liste;
    SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    Cursor cursor;
    PreventAccidentListAdapter preventAccidentListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevention_accident_list);

        liste = (ListView) findViewById(R.id.liste_prevention_accident);
        dbHelper = new DbHelper(this.getApplicationContext());
        preventAccidentListAdapter = new PreventAccidentListAdapter(this.getApplicationContext(), R.layout.item_prevention_accident);
        liste.setAdapter(preventAccidentListAdapter);
        sqLiteDatabase = dbHelper.getReadableDatabase();
        cursor = dbHelper.getAllPreventionAccident(sqLiteDatabase);
        if(cursor.moveToNext()) {
            do {

                String nom, icon;
                nom = cursor.getString(0);
                icon = cursor.getString(1);

                Accident accident = new Accident("nothing", nom, icon);
                preventAccidentListAdapter.add(accident);


            }while (cursor.moveToNext());
        }

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView selectedAccident = (TextView) view.findViewById(R.id.prevention_nom_accident);
                String nomAccident = selectedAccident.getText().toString();

                String requete = "SELECT "+FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_TITLE+", "+FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_SUBTITLE+", "+FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_FIRST_ICON+", "+FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_SECOND_ICON+", "+FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_DESCRIPTION+" FROM "+FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_TABLE_NAME+" WHERE "+FirstAidManager.PreventnionInstructionManager.PREVENTION_INSTRUCTION_ACCIDENT_NAME+" = ?";

                Intent intent = new Intent(PreventionAccidentList.this, PreventionInstructionList.class);
                intent.putExtra("requetePrevention", requete);
                intent.putExtra("nomAccident", nomAccident);
                startActivity(intent);
                Toast.makeText(PreventionAccidentList.this, nomAccident, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
