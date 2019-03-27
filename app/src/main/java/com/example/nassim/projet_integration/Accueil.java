package com.example.nassim.projet_integration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Accueil extends AppCompatActivity {
    public Button actualiteButton ;
    public Button publierButton ;
    public Button myPublicationsButton ;
    public Button myProfileButton ;
    public Button disconnectButton ;
    SharedPreferences sharedPreferences ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        actualiteButton = findViewById(R.id.actualite);
        publierButton = findViewById(R.id.publier);
        myPublicationsButton = findViewById(R.id.my_publications);
        myProfileButton = findViewById(R.id.my_profile);
        disconnectButton = findViewById(R.id.disconnect_app);

        actualiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accueil.this,Actualite_activity.class);
                startActivity(intent);
            }
        });
        publierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accueil.this,Publier.class);
                startActivity(intent);
            }
        });
        myPublicationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accueil.this,MyDiscussions.class);
                startActivity(intent);
            }
        });
        myProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accueil.this,MyComments.class);
                startActivity(intent);
            }
        });
        disconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = Accueil.this.getApplicationContext();
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(Accueil.this,"déconnexion",Toast.LENGTH_LONG).show();
                // retour a la page de connexion/inscription
               finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        // permet de quiter l'aplication si on apuis le le bouton back
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
