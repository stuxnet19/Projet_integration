package com.example.nassim.projet_integration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Inscription_Activity extends AppCompatActivity {
    public String nom ;
    public String prenom ;
    public String mail ;
    public String mdp ;
    public Button inscription ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_);
        init();
    }
    public void init(){
        nom = ((TextView)findViewById(R.id.nom_inscription)).getText().toString();
        prenom = ((TextView)findViewById(R.id.prenom_inscription)).getText().toString() ;
        mail = ((TextView)findViewById(R.id.mail_inscription)).getText().toString();
        mdp = ((TextView)findViewById(R.id.mdp_inscription)).getText().toString();
        inscription = (Button)findViewById(R.id.btn_valider_inscription);
        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
