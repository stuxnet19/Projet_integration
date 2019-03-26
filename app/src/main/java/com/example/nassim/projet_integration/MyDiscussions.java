package com.example.nassim.projet_integration;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import ServerConnexion.Connect;
import ServerConnexion.connnexion_info;

public class MyDiscussions extends AppCompatActivity {
    ListView listView = null;
    HashMap<String,String> information = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_discussions);
        listView = findViewById(R.id.my_list2);
        listView.setAdapter(null);
        String data = PreferenceManager.getDefaultSharedPreferences(MyDiscussions.this).getString("data","default");
        try {
            JSONObject jsonObject = new JSONObject(data);
            String id_pro = jsonObject.getString("id_pro");
            information.put("id_pro",id_pro);
            Connect connect = new Connect(MyDiscussions.this,information,"actualite",connnexion_info.IP,connnexion_info.WEB_SERVICE_FOLDER_MY_DISCUSSIONS,listView);
            connect.execute();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
