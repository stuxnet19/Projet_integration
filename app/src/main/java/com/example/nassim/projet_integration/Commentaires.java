package com.example.nassim.projet_integration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import ServerConnexion.*;

import java.util.HashMap;

import ServerConnexion.Connect;


public class Commentaires extends AppCompatActivity {
    public Button commenter = null ;
    public Button fullScreanImageButton = null ;
    public ImageView imagePub ;
    ListView listView = null ;
    HashMap<String,String> informations = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentaires);

        listView = findViewById(R.id.comment_listView_id);
        commenter = findViewById(R.id.addComment);
        imagePub = findViewById(R.id.image_pub);
        fullScreanImageButton = findViewById(R.id.fullScreanImageButton);

        final Intent intent = getIntent() ;
        try{
            byte[] decodeString = Base64.decode((String)intent.getStringExtra("photo"),Base64.DEFAULT);
            Bitmap decodeByte = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
            imagePub.setImageBitmap(decodeByte);
        }
        catch (NullPointerException e){
            System.out.println(e);
        }
        informations.put("publication_id",intent.getStringExtra("publication_id"));
        listView.setAdapter(null);
        Connect connect = new Connect(Commentaires.this,informations,"commentaire",connnexion_info.IP,connnexion_info.WEB_SERVICE_FOLDER_COMMENTAIRES,listView);
        connect.execute();

        commenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Commentaires.this,MakeComment.class);
                intent1.putExtra("publication_id",intent.getStringExtra("publication_id"));
                startActivity(intent1);
            }
        });
        fullScreanImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Commentaires.this,ImageFull.class);
                intent1.putExtra("photo",intent.getStringExtra("photo"));
                startActivity(intent1);
            }
        });
    }
}
