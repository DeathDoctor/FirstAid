package com.example.lkhedma.firstaid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

public class Chute extends AppCompatActivity {

    CheckBox s1;
    CheckBox s2;
    CheckBox s3;
    CheckBox s4;
    CheckBox s5;
    CheckBox s6;
    CheckBox s7;
    CheckBox s8;
    CheckBox s9;
    CheckBox s10;
    CheckBox s11;
    CheckBox s12;
    CheckBox s13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Signes");
        setContentView(R.layout.activity_chute);
        s1 = (CheckBox) findViewById(R.id.s1);
        s2 = (CheckBox) findViewById(R.id.s2);
        s3 = (CheckBox) findViewById(R.id.s3);
        s4 = (CheckBox) findViewById(R.id.s4);
        s5 = (CheckBox) findViewById(R.id.s5);
        s6 = (CheckBox) findViewById(R.id.s6);
        s7 = (CheckBox) findViewById(R.id.s7);
        s8 = (CheckBox) findViewById(R.id.s8);
        s9 = (CheckBox) findViewById(R.id.s9);
        s10 = (CheckBox) findViewById(R.id.s10);
        s11 = (CheckBox) findViewById(R.id.s11);
        s12 = (CheckBox) findViewById(R.id.s12);
        s13 = (CheckBox) findViewById(R.id.s13);

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


        Diagnostique diagnostique = new Diagnostique("Pas de traumatisme", "faible");


        if (s3.isChecked() || s2.isChecked() || s1.isChecked()){
            if (s3.isChecked()){

                diagnostique.setNom("Entorse");
                diagnostique.setGravite("modere");

            } else {
                if (s2.isChecked()) {

                    diagnostique.setNom("Fracture déplacée");
                    diagnostique.setGravite("modere");

                } else {

                    if (s1.isChecked()){
                        diagnostique.setNom("Fracture non déplacée");
                        diagnostique.setGravite("modere");


                    }
                }
            }
        }
        if (s4.isChecked()) {
            diagnostique.setNom("Luxation");
            diagnostique.setGravite("modere");
        }

        if (s5.isChecked() || s6.isChecked() || s7.isChecked() || s8.isChecked()) {
            diagnostique.setNom("Fracture vertébrale");
            diagnostique.setGravite("grave");

        }

        if (s9.isChecked() || s10.isChecked() || s11.isChecked() || s12.isChecked() || s13.isChecked()) {
            diagnostique.setNom("Traumatisme cranien");
            diagnostique.setGravite("grave");
        }
//        if ((!s1.isChecked() &&
//                     !s2.isChecked() &&
//                     !s3.isChecked() &&
//                     !s4.isChecked() &&
//                     !s5.isChecked() &&
//                     !s6.isChecked() &&
//                     !s7.isChecked() &&
//                     !s8.isChecked() &&
//                     !s9.isChecked() &&
//                     !s10.isChecked() &&
//                     !s11.isChecked() &&
//                     !s12.isChecked() &&
//                     !s13.isChecked())) {
//            Diagnostique = new Diagnostique("Pas de traumatisme", "grave");
//             }

        //String requeteCH = "SELECT "+FirstAidManager.GesteSecourManager.ICON_GESTE_SECOUR+ ", "+ FirstAidManager.GesteSecourManager.DESCRIPTION_GESTE_SECOUR+ " FROM " +FirstAidManager.GesteSecourManager.GESTE_SECOUR_TABLE_NAME+ " WHERE "+FirstAidManager.GesteSecourManager.DIAGNOSTIC_GESTE_SECOUR+" = ?";

        //Toast.makeText(getBaseContext(), "titre :"+Diagnostique.nom+" \n"+requete, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Chute.this, GestesDeSecours.class);
        intent.putExtra("diagnostiqueAccident",diagnostique.getNom().toString());
        intent.putExtra("gravite", diagnostique.getGravite().toString());
        startActivity(intent);

    }
}
