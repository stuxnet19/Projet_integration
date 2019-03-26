package com.example.nassim.projet_integration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

public class ImageFull extends AppCompatActivity {
    ImageView image ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_full);
        image = findViewById(R.id.imageFullScrean);
        Intent intent = getIntent();

        byte[] decodeString = Base64.decode((String)intent.getStringExtra("photo"),Base64.DEFAULT);
        Bitmap decodeByte = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
        image.setImageBitmap(decodeByte);

    }
}
