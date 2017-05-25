package com.example.lkhedma.firstaid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class Inhalation extends AppCompatActivity {

    Diagnostique diagnostique;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inhalation);
    }

    public void tousse(View view) {

        intent = new Intent(Inhalation.this, GestesDeSecours.class);
        Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
        animation1.setDuration(2000);
        view.startAnimation(animation1);
        diagnostique = new Diagnostique("Enfant tousse", "modere");
        intent.putExtra("diagnostiqueAccident",diagnostique.getNom().toString());
        intent.putExtra("gravite", diagnostique.getGravite().toString());
        startActivity(intent);

    }

    public void respire_pas(View view) {

        intent = new Intent(Inhalation.this, GestesDeSecours.class);
        Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
        animation1.setDuration(2000);
        view.startAnimation(animation1);
        diagnostique = new Diagnostique("Enfant ne respire pas", "grave");
        intent.putExtra("diagnostiqueAccident",diagnostique.getNom().toString());
        intent.putExtra("gravite", diagnostique.getGravite().toString());
        startActivity(intent);
    }
}
