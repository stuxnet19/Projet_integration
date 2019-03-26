package com.example.nassim.projet_integration;
import ServerConnexion.*;
import android.content.Intent;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ServerConnexion.Connect;

import static ServerConnexion.Connect.ISO_8859_1;


public class MakeComment extends AppCompatActivity {
    public Button makeCommentButton ;
    public EditText makeCommentText;
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_comment);
        makeCommentButton = (Button)findViewById(R.id.makeCommentButton);
        makeCommentText = (EditText)findViewById(R.id.makeCommentText);
        makeCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent() ;
                String publication_id = intent.getStringExtra("publication_id");
                String data =  PreferenceManager.getDefaultSharedPreferences(MakeComment.this).getString("data","default");
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String id_pro = (String) jsonObject.get("id_pro");
                    String nom = (String)jsonObject.get("nom");
                    String prenom = (String)jsonObject.get("prenom");

                    Date currentTime = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String formattedDate = df.format(currentTime);

                    String contenu_comment = makeCommentText.getText().toString();
                    HashMap<String,String> informations = new HashMap<>();

                    informations.put("id_pub",publication_id);
                    informations.put("id_pro",id_pro);
                    informations.put("date_com",formattedDate);
                    informations.put("contenu",contenu_comment);

                    Connect connect = new Connect(MakeComment.this,informations,"make_comment",connnexion_info.IP,connnexion_info.WEB_SERVICE_FOLDER_MAKE_COMMENTAIRES);
                    connect.execute();
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public String strToUtf8(String str){
        byte[] ptext = str.getBytes(ISO_8859_1);
        String value = new String(ptext, UTF_8);
        return value ;
    }
}
