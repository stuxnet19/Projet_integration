package com.example.nassim.projet_integration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Connexion_activity extends AppCompatActivity {
    public Button connexion ;
    public String mail ;
    public String mdp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion_activity);
        init();
    }
    public void init(){
        connexion = (Button) findViewById(R.id.btn_valider_connexion) ;
        mail = ((TextView)findViewById(R.id.mail_connexion)).getText().toString();
        mdp = ((TextView)findViewById(R.id.mdp_connexion)).getText().toString();
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
