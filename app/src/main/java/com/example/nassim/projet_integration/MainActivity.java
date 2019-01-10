package com.example.nassim.projet_integration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button home_inscription ;
    public Button home_connexion ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home_connexion = (Button)findViewById(R.id.homme_connexion);
        home_inscription=(Button) findViewById(R.id.homme_inscription);

        home_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Inscription_Activity.class);
                startActivity(intent);
            }
        });
        home_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Connexion_activity.class);
                startActivity(intent);
            }
        });
    }
}
