package com.example.lkhedma.firstaid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class Chute extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Signes");
        setContentView(R.layout.activity_chute);
    }

    public void diagnostiquer(View view) {

        ArrayList<String> listeDiagnistique = new ArrayList<>();

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


            if (s1.isChecked() && s2.isChecked()){
                listeDiagnistique.add("fracture deplacee");
            } else {
                if (s1.isChecked() && (s2.isChecked() == false)) {
                    listeDiagnistique.add("fracture non déplacee");
                } else {
                    if ((s1.isChecked() == false) && s2.isChecked()) {
                        listeDiagnistique.add("fracture deplacee");
                    }
                }
            }
             if (s5.isChecked() || s6.isChecked() || s7.isChecked() || s8.isChecked()) {
                 listeDiagnistique.add("fracture vertebrale");
             }
             if (s9.isChecked() || s10.isChecked() || s11.isChecked() || s12.isChecked() || s13.isChecked()) {
                 listeDiagnistique.add("traumatisme cranien");
             }

             if ((!s1.isChecked() &&
                     !s2.isChecked() &&
                     !s3.isChecked() &&
                     !s4.isChecked() &&
                     !s5.isChecked() &&
                     !s6.isChecked() &&
                     !s7.isChecked() &&
                     !s8.isChecked() &&
                     !s9.isChecked() &&
                     !s10.isChecked() &&
                     !s11.isChecked() &&
                     !s12.isChecked() &&
                     !s13.isChecked())) {
                 listeDiagnistique.add("Pas de trumatisme");
             }


        switch (listeDiagnistique.size()){
            case 1:
                Toast.makeText(getBaseContext(),listeDiagnistique.get(0), Toast.LENGTH_LONG).show();
                break;

            case 2:
                Toast.makeText(getBaseContext(),listeDiagnistique.get(0)+" "+listeDiagnistique.get(1), Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(getBaseContext(),listeDiagnistique.get(0)+" "+listeDiagnistique.get(1)+" "+listeDiagnistique.get(2), Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }

    }
}
