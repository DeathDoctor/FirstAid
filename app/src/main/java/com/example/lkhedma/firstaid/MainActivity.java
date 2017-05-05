package com.example.lkhedma.firstaid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toListeAccident = (Button) findViewById(R.id.acc);
        toListeAccident.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListeAccident.class);
                startActivity(intent);
            }
        });

        Button toContact = (Button) findViewById(R.id.contact);
        toContact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NouveauContact.class);
                startActivity(intent);
            }
        });
    }

    public void brulure(View view){
        Intent intent = new Intent(MainActivity.this, Brulure.class);
        startActivity(intent);
    }
}
