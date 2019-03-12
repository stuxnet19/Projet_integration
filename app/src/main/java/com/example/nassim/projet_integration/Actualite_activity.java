package com.example.nassim.projet_integration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ServerConnexion.Publication;
import ServerConnexion.Publication_adapter;

public class Actualite_activity extends AppCompatActivity {
    ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualite_activity);
        listView = (ListView)findViewById(R.id.my_list);
        List<Publication> publications = genererPublications() ;
        Publication_adapter adapter = new Publication_adapter(Actualite_activity.this,publications);
        listView.setAdapter(adapter);
    }
    private List<Publication> genererPublications(){
        List<Publication> publications = new ArrayList<Publication>();
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        publications.add(new Publication(null,"pseudo1","titre1","text1"));
        return publications ;

    }
}
