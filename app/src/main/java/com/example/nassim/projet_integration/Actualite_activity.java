package com.example.nassim.projet_integration;
import ServerConnexion.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ServerConnexion.Connect;
import ServerConnexion.Publication;
import ServerConnexion.Publication_adapter;

public class Actualite_activity extends AppCompatActivity {
    ListView listView;
    HashMap<String,String> information = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualite_activity);
        listView = findViewById(R.id.my_list);
        information.put("categorie","null");
        information.put("anatomie","null");
        information.put("mot_cle","null");

        Connect connect = new Connect(Actualite_activity.this,information,"actualite",connnexion_info.IP,connnexion_info.WEB_SERVICE_FOLDER_ACTUALITE,listView);
        connect.execute();
    }
}
