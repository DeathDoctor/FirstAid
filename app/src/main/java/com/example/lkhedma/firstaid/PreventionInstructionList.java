package com.example.lkhedma.firstaid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class PreventionInstructionList extends AppCompatActivity {

    ListView liste;
    SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    Cursor cursor;
    PreventionInstructionAdapter preventionInstructionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevention_instruction_list);

        Intent intent = getIntent();
        String requete = intent.getStringExtra("requetePrevention");
        String nomAccident = intent.getStringExtra("nomAccident");

        setTitle(nomAccident);

        liste = (ListView) findViewById(R.id.liste_prevention_instruction);
        dbHelper = new DbHelper(this.getApplicationContext());
        preventionInstructionAdapter = new PreventionInstructionAdapter(this.getApplicationContext(), R.layout.item_prevention_instruction);
        liste.setAdapter(preventionInstructionAdapter);
        sqLiteDatabase = dbHelper.getReadableDatabase();
        cursor = dbHelper.getPreventionInstructions(sqLiteDatabase, requete, nomAccident);

        if (cursor.moveToNext()) {
            do {
                String titre, sousTitre, icon1, icon2, description;
                titre = cursor.getString(0);
                sousTitre = cursor.getString(1);
                icon1 = cursor.getString(2);
                icon2 = cursor.getString(3);
                description = cursor.getString(4);

                PreventionInstruction preventionInstruction = new PreventionInstruction(titre, sousTitre, icon1, icon2, description);
                preventionInstructionAdapter.add(preventionInstruction);

            } while (cursor.moveToNext());
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
