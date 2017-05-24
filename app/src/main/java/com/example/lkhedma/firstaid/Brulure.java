package com.example.lkhedma.firstaid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class Brulure extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Signes");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brulure);

    }



    public void diagnostiquer(View view) {

        String diagnostique = "Brulure simple";
        String source;
        String gravite = "faible";


        RadioButton orifice = (RadioButton) findViewById(R.id.orifice);
        RadioButton visage = (RadioButton) findViewById(R.id.visage);
        RadioButton mains = (RadioButton) findViewById(R.id.mains);
        RadioButton autres = (RadioButton) findViewById(R.id.autres);

        RadioButton peauRouge = (RadioButton) findViewById(R.id.peau_rouge);
        RadioButton cloques = (RadioButton) findViewById(R.id.cloques);
        RadioButton peauNoir = (RadioButton) findViewById(R.id.peau_noir);

        RadioButton inf_paum = (RadioButton) findViewById(R.id.inf_paum);
        RadioButton sup_paum = (RadioButton) findViewById(R.id.sup_paum);

        RadioButton chaleur = (RadioButton) findViewById(R.id.chaleur);
        RadioButton electricite = (RadioButton) findViewById(R.id.electricite);
        RadioButton chimique = (RadioButton) findViewById(R.id.chimique);
        RadioButton liquide = (RadioButton) findViewById(R.id.liquide);


        if(chaleur.isChecked()){
            source = chaleur.getText().toString();
        }else {
            if (electricite.isChecked()){
                source = electricite.getText().toString();
            }else {
                if(chimique.isChecked()){
                    source = chimique.getText().toString();
                } else {
                    source = liquide.getText().toString();
                }
            }
        }

        if (peauNoir.isChecked() || sup_paum.isChecked() || orifice.isChecked() || electricite.isChecked()) {
            diagnostique = "Brulure grave";
            gravite = "grave";

        } else {
            if((peauRouge.isChecked() && inf_paum.isChecked()) && (autres.isChecked() || visage.isChecked())){
                diagnostique = "Brulure simple";
                gravite = "faible";
            }else {
                if (cloques.isChecked() && (inf_paum.isChecked() && autres.isChecked())){
                    diagnostique = "Brulure simple";
                    gravite = "faible";
                }
            }
        }

        //String requete = "SELECT "+FirstAidManager.GesteSecourManager.ICON_GESTE_SECOUR+ ", "+ FirstAidManager.GesteSecourManager.DESCRIPTION_GESTE_SECOUR+ " FROM " +FirstAidManager.GesteSecourManager.GESTE_SECOUR_TABLE_NAME+ " WHERE "+FirstAidManager.GesteSecourManager.DIAGNOSTIC_GESTE_SECOUR+" = ?";
        Toast.makeText(getBaseContext(),diagnostique, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(Brulure.this, GestesDeSecours.class);
        intent.putExtra("diagnostiqueAccident",diagnostique);
        //intent.putExtra("requeteGestesSecours",requete);
        intent.putExtra("gravite", gravite);
        startActivity(intent);


   }


}
