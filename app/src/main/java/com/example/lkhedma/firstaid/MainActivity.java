package com.example.lkhedma.firstaid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void contactL(View view) {
        intent = new Intent(MainActivity.this, ContactList.class);
        startActivity(intent);
    }

    public void guide(View view) {
        intent = new Intent(MainActivity.this, Guide.class);
        startActivity(intent);
    }

    public void accident(View view) {
        intent = new Intent(MainActivity.this, ListeAccident.class);
        startActivity(intent);
    }

    public void brulure(View view){
        intent = new Intent(MainActivity.this, Brulure.class);
        startActivity(intent);
    }
}
