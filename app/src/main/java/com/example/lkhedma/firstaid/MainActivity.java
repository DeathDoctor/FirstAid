package com.example.lkhedma.firstaid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {
    Intent intent;
    Button contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        contactList = (Button) findViewById(R.id.contactlist0);
        contactList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ContactList.class);
                startActivity(intent);
            }
        });
    }



//    public void contact(View view) {
//        intent = new Intent(MainActivity.this, ContactList.class);
//        startActivity(intent);
//    }

    public void guide(View view) {
        intent = new Intent(MainActivity.this, PreventionAccidentList.class);
        startActivity(intent);
    }

    public void accident(View view) {
        intent = new Intent(MainActivity.this, ListeAccident.class);
        startActivity(intent);
    }

    public void carte(View view){
        intent = new Intent(MainActivity.this, HopitauxListe.class);
        startActivity(intent);
    }
}
