package com.example.lkhedma.firstaid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class Brulure extends AppCompatActivity {


    RadioButton orifice;
    RadioButton visage;
    RadioButton mains;
    RadioButton autres;

    RadioButton peauRouge;
    RadioButton cloques;
    RadioButton peauNoir;

    RadioButton inf_paum;
    RadioButton sup_paum;

    RadioButton chaleur;
    RadioButton electricite;
    RadioButton chimique;
    RadioButton liquide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Signes");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brulure);
        orifice = (RadioButton) findViewById(R.id.orifice);
        visage = (RadioButton) findViewById(R.id.visage);
        mains = (RadioButton) findViewById(R.id.mains);
        autres = (RadioButton) findViewById(R.id.autres);

        peauRouge = (RadioButton) findViewById(R.id.peau_rouge);
        cloques = (RadioButton) findViewById(R.id.cloques);
        peauNoir = (RadioButton) findViewById(R.id.peau_noir);

        inf_paum = (RadioButton) findViewById(R.id.inf_paum);
        sup_paum = (RadioButton) findViewById(R.id.sup_paum);

        chaleur = (RadioButton) findViewById(R.id.chaleur);
        electricite = (RadioButton) findViewById(R.id.electricite);
        chimique = (RadioButton) findViewById(R.id.chimique);
        liquide = (RadioButton) findViewById(R.id.liquide);

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


    public void diagnostiquer(View view) {

        Diagnostique diagnostique = new Diagnostique("Brulure simple","faible");

//        RadioButton orifice = (RadioButton) findViewById(R.id.orifice);
//        RadioButton visage = (RadioButton) findViewById(R.id.visage);
//        RadioButton mains = (RadioButton) findViewById(R.id.mains);
//        RadioButton autres = (RadioButton) findViewById(R.id.autres);
//
//        RadioButton peauRouge = (RadioButton) findViewById(R.id.peau_rouge);
//        RadioButton cloques = (RadioButton) findViewById(R.id.cloques);
//        RadioButton peauNoir = (RadioButton) findViewById(R.id.peau_noir);
//
//        RadioButton inf_paum = (RadioButton) findViewById(R.id.inf_paum);
//        RadioButton sup_paum = (RadioButton) findViewById(R.id.sup_paum);
//
//        RadioButton chaleur = (RadioButton) findViewById(R.id.chaleur);
//        RadioButton electricite = (RadioButton) findViewById(R.id.electricite);
//        RadioButton chimique = (RadioButton) findViewById(R.id.chimique);
//        RadioButton liquide = (RadioButton) findViewById(R.id.liquide);



        if (peauNoir.isChecked() || sup_paum.isChecked() || orifice.isChecked() || electricite.isChecked()) {
            diagnostique.setNom("Brulure grave");
            diagnostique.setGravite("grave");

        } else {
            if((peauRouge.isChecked() && inf_paum.isChecked()) && (autres.isChecked() || visage.isChecked())){
                diagnostique.setNom("Brulure simple");
                diagnostique.setGravite("faible");
            }else {
                if (cloques.isChecked() && (inf_paum.isChecked() && autres.isChecked())){
                    diagnostique.setNom("Brulure simple");
                    diagnostique.setGravite("faible");
                }
            }
        }

        Toast.makeText(getBaseContext(),diagnostique.getNom(), Toast.LENGTH_LONG).show();

        Intent intent = new Intent(Brulure.this, GestesDeSecours.class);
        intent.putExtra("diagnostiqueAccident",diagnostique.getNom());
        intent.putExtra("gravite", diagnostique.getGravite());
        startActivity(intent);


   }


}
