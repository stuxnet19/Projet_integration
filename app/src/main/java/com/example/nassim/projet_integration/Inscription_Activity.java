package com.example.nassim.projet_integration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import ServerConnexion.* ;
public class Inscription_Activity extends AppCompatActivity {
    public EditText nom ;
    public EditText prenom ;
    public EditText mail ;
    public EditText mdp ;
    public Button inscription ;
    public HashMap<String,String> informations = new HashMap<>();
    @Override
    /**
     * constructeur
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_);
        init();
    }


    public void init(){
        nom = (EditText)findViewById(R.id.nom_inscription);
        prenom = (EditText)findViewById(R.id.prenom_inscription);
        mail = (EditText)findViewById(R.id.mail_inscription);
        mdp = (EditText)findViewById(R.id.mdp_inscription);
        inscription = (Button)findViewById(R.id.btn_valider_inscription);

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                informations.put("mail",mail.getText().toString());
                informations.put("nom",nom.getText().toString());
                char character = prenom.getText().toString().charAt(0) ;
                informations.put("prenom",prenom.getText().toString().replace(character,Character.toUpperCase(character)));
                informations.put("password",mdp.getText().toString());
                Connect connect = new Connect(Inscription_Activity.this,informations,"inscription",connnexion_info.IP,connnexion_info.WEB_SERVICE_FOLDER_INSCRIPTION);
                connect.execute();
                finish();
            }
        });
    }
}
