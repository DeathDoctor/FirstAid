package com.example.lkhedma.firstaid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Intoxication extends AppCompatActivity {



    RadioButton acideFort;
    RadioButton baseFort;
    RadioButton acideFaible;
    RadioButton autres;
    RadioGroup group;
    Diagnostique diagnostique;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Signes");
        setContentView(R.layout.activity_intoxication);
        setTitle("Signes");

        acideFort = (RadioButton)findViewById(R.id.acide_fort);
        baseFort = (RadioButton)findViewById(R.id.base_forte);
        acideFaible = (RadioButton)findViewById(R.id.acide_faible);
        autres = (RadioButton)findViewById(R.id.autres);
        group = (RadioGroup) findViewById(R.id.group);

    }

    public void diagnostiquer(View view) {

        if (acideFort.isChecked()) {

            diagnostique = new Diagnostique("Caustique fort (acide fort)", "grave");

        } else {
            if (baseFort.isChecked()) {

                diagnostique = new Diagnostique("Caustique fort (Base fort)", "grave");

            } else {
                if (acideFaible.isChecked()) {

                    diagnostique = new Diagnostique("Caustique moyen (acide faible)", "faible");

                } else {
                    if (autres.isChecked()) {

                        diagnostique = new Diagnostique ("Diagnostic non identifié", "modere");
                    }
                }
            }
        }
        Toast.makeText(this, diagnostique.getNom(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Intoxication.this, GestesDeSecours.class);

        intent.putExtra("diagnostiqueAccident",diagnostique.getNom().toString());
        intent.putExtra("gravite", diagnostique.getGravite().toString());
        startActivity(intent);

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
