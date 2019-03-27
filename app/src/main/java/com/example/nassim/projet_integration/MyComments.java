package com.example.nassim.projet_integration;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import ServerConnexion.Connect;
import ServerConnexion.connnexion_info;

public class MyComments extends AppCompatActivity {
    ListView listView = null;
    HashMap<String,String> information = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comments);
        listView = findViewById(R.id.my_list3);
        listView.setAdapter(null);
        String data = PreferenceManager.getDefaultSharedPreferences(MyComments.this).getString("data","default");
        try {
            JSONObject jsonObject = new JSONObject(data);
            String id_pro = jsonObject.getString("id_pro");
            information.put("id_pro",id_pro);
            Connect connect = new Connect(MyComments.this,information,"actualite",connnexion_info.IP,connnexion_info.WEB_SERVICE_FOLDER_MY_COMMENTS,listView);
            connect.execute();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
