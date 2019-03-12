package com.example.nassim.projet_integration;

import ServerConnexion.*;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.HashMap;

import ServerConnexion.Connect;

public class Connexion_activity extends AppCompatActivity {
    public Button connexion ;
    public String mail ;
    public String mdp ;
    public HashMap<String,String> informations = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion_activity);
        init();
    }
    public void init(){
        connexion = (Button) findViewById(R.id.btn_valider_connexion) ;
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = ((TextView)findViewById(R.id.mail_connexion)).getText().toString();
                mdp = ((TextView)findViewById(R.id.mdp_connexion)).getText().toString();
                informations.put("mail",mail);
                informations.put("mdp",mdp);
                Connect connect = new Connect(Connexion_activity.this,informations,"connexion",connnexion_info.IP,connnexion_info.WEB_SERVICE_FOLDER_CONNEXION);
                connect.execute();
            }
        });
    }
}
