package com.example.nassim.projet_integration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import ServerConnexion.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ServerConnexion.Comment;
import ServerConnexion.Comment_adapter;
import ServerConnexion.Connect;
import ServerConnexion.Publication;
import ServerConnexion.Publication_adapter;

public class Commentaires extends AppCompatActivity {
    ListView listView = null ;
    HashMap<String,String> informations = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentaires);
        listView = findViewById(R.id.comment_listView_id);
        Intent intent = getIntent() ;
        informations.put("publication_id",intent.getStringExtra("publication_id"));
        listView.setAdapter(null);
        Connect connect = new Connect(Commentaires.this,informations,"commentaire",connnexion_info.IP,connnexion_info.WEB_SERVICE_FOLDER_COMMENTAIRES,listView);
        connect.execute();

    }
}
