package com.example.lkhedma.firstaid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

public class HopitalInformations extends AppCompatActivity {

    ImageView icon;
    TextView nom;
    TextView wilaya;
    TextView quartier;
    TextView numero;
    TextView site;
    String latt;
    String longt;
    Hopital hopital;

    SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hopital_informations);
        Intent intent = getIntent();
        String requete = intent.getStringExtra("requeteInformationsHopital");
        String nomHopital = intent.getStringExtra("nomHopital");

        dbHelper = new DbHelper(this.getApplicationContext());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        cursor = dbHelper.getHospitalInformations(sqLiteDatabase, requete, nomHopital);
        cursor.moveToNext();
        hopital = new Hopital(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));

        String uri = hopital.getPhoto().toString();
        int iconResource = getBaseContext().getResources().getIdentifier(uri, null, getBaseContext().getPackageName());
        Drawable res = getBaseContext().getResources().getDrawable(iconResource);

        icon = (ImageView) findViewById(R.id.hop_info_icon);
        nom = (TextView) findViewById(R.id.hop_info_nom);
        wilaya = (TextView) findViewById(R.id.hop_info_quartier);
        quartier = (TextView) findViewById(R.id.hop_info_wilaya);
        numero = (TextView) findViewById(R.id.hop_info_numero);
        site = (TextView) findViewById(R.id.hop_info_site);

        icon.setImageDrawable(res);
        nom.setText(hopital.getNom());
        wilaya.setText(wilaya.getText()+hopital.getWilaya());
        quartier.setText(quartier.getText()+hopital.getQuartier());
        numero.setText(numero.getText()+hopital.getNumero());
        site.setText(site.getText()+hopital.getSite());
    }

    public void appeler(View view) {

        Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
        animation1.setDuration(2000);
        view.startAnimation(animation1);
        Uri call = Uri.parse("tel:" + hopital.getNumero());
        Intent intent = new Intent(Intent.ACTION_DIAL, call);
        startActivity(intent);
    }

    public void map(View view) {

        Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
        animation1.setDuration(2000);
        view.startAnimation(animation1);

        double latt = Double.parseDouble(hopital.getLatt());
        double longt = Double.parseDouble(hopital.getLongt());
        Intent intent = new Intent(HopitalInformations.this, MapsActivity.class);

        intent.putExtra("latt", latt);
        intent.putExtra("longt", longt);

        startActivity(intent);

    }
}
