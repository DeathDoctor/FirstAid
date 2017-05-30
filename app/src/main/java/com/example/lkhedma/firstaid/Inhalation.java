package com.example.lkhedma.firstaid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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

        setTitle("Signes");
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
