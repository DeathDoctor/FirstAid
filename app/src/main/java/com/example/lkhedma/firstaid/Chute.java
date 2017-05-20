package com.example.lkhedma.firstaid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Chute extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Signes");
        setContentView(R.layout.activity_chute);
    }

    public void diagnostiquer(View view) {

        Diagnostique titreDiagnostique = new Diagnostique("Pas de traumatisme", "faible");
        String requeteChute = "Pas de traumatisme";

        CheckBox s1 = (CheckBox) findViewById(R.id.s1);
        CheckBox s2 = (CheckBox) findViewById(R.id.s2);
        CheckBox s3 = (CheckBox) findViewById(R.id.s3);
        CheckBox s4 = (CheckBox) findViewById(R.id.s4);
        CheckBox s5 = (CheckBox) findViewById(R.id.s5);
        CheckBox s6 = (CheckBox) findViewById(R.id.s6);
        CheckBox s7 = (CheckBox) findViewById(R.id.s7);
        CheckBox s8 = (CheckBox) findViewById(R.id.s8);
        CheckBox s9 = (CheckBox) findViewById(R.id.s9);
        CheckBox s10 = (CheckBox) findViewById(R.id.s10);
        CheckBox s11 = (CheckBox) findViewById(R.id.s11);
        CheckBox s12 = (CheckBox) findViewById(R.id.s12);
        CheckBox s13 = (CheckBox) findViewById(R.id.s13);


        if (s3.isChecked() || s2.isChecked() || s3.isChecked()){
            requeteChute = "Traumatisme du membre";
            if (s1.isChecked()){
                titreDiagnostique.setNom("Fracture non déplacée");
                titreDiagnostique.setGravite("faible");

            } else {
                if (s2.isChecked()) {

                    titreDiagnostique = new Diagnostique("Fracture déplacée", "faible");
                } else {
                    if (s3.isChecked()){

                        titreDiagnostique = new Diagnostique("Entorse", "faible");
                    }
                }
            }
        }
        if (s4.isChecked()) {
            requeteChute = "Traumatisme du membre (luxation)";
            titreDiagnostique = new Diagnostique("Luxation", "faible");
        }

        if (s5.isChecked() || s6.isChecked() || s7.isChecked() || s8.isChecked()) {
            requeteChute = "Fracture vertébrale";
            titreDiagnostique = new Diagnostique("Fracture vertébrale", "grave");

        }

        if (s9.isChecked() || s10.isChecked() || s11.isChecked() || s12.isChecked() || s13.isChecked()) {
            requeteChute = "Traumatisme cranien ";
            titreDiagnostique = new Diagnostique("Traumatisme cranien", "grave");
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
//            titreDiagnostique = new Diagnostique("Pas de traumatisme", "grave");
//             }

        Toast.makeText(getBaseContext(), "titre :"+titreDiagnostique.nom+" \n"+requeteChute, Toast.LENGTH_LONG).show();

    }
}
