package com.example.nassim.projet_integration;
import ServerConnexion.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ServerConnexion.Connect;

public class Publier extends AppCompatActivity {
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public EditText titrePublication ;
    public EditText contenuPublication ;
    public Button publierButton ;
    public Button uploadImageButton ;
    private int GALLERY = 1, CAMERA = 2;
    public Bitmap image = null ;
    public Spinner spinner ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publier);

        spinner = findViewById(R.id.spinner);
        spinner.setPrompt("selectionnez un catégorie");
        List<String> list = new ArrayList<String>();

        list.add("Médecine générale");
        list.add("Médecine interne");
        list.add("Médecine nucléaire");
        list.add("Chirurgie");
        list.add("Allergologie");
        list.add("Anesthésiologie");
        list.add("Andrologie");
        list.add("Cardiologie");
        list.add("Dermatologie");
        list.add("Endocrinologie");
        list.add("Gastro-entérologie");
        list.add("Gériatrie");
        list.add("Gynécologie");
        list.add("Hématologie");
        list.add("Hépatologie");
        list.add("Infectiologie");
        list.add("Obstétrique");
        list.add("Oncologie");
        list.add("Odontologie");
        list.add("Neurologie");
        list.add("Néphrologie");
        list.add("Néonatologie");
        list.add("Ophtalmologie");
        list.add("Orthopédie");
        list.add("Oto-rhino-laryngologie");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        titrePublication = findViewById(R.id.text_titre_publier);
        contenuPublication = findViewById(R.id.text_contenu_publier);
        publierButton = findViewById(R.id.button_share);
        uploadImageButton = findViewById(R.id.button_add_photo);
        uploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });
        publierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categorie = String.valueOf(spinner.getSelectedItemPosition()+1);
                String titre_publication = titrePublication.getText().toString();
                String contenu_publication = contenuPublication.getText().toString();
                String data = PreferenceManager.getDefaultSharedPreferences(Publier.this).getString("data","default");
                try{

                    JSONObject jsonObject = new JSONObject(data);
                    String id_pro = (String) jsonObject.get("id_pro");
                    Date currentTime = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String formattedDate = df.format(currentTime);
                    HashMap<String,String> informations = new HashMap<>();
                    informations.put("contenu",contenu_publication);
                    informations.put("date_pub",formattedDate);
                    informations.put("id_cat",categorie);
                    informations.put("id_pro",id_pro);
                    informations.put("titre",titre_publication);

                    if (image!=null){
                        ByteArrayOutputStream bao = new ByteArrayOutputStream();
                        image.compress(Bitmap.CompressFormat.JPEG, 100, bao);
                        byte [] ba = bao.toByteArray();
                        String ba1=Base64.encodeToString(ba,Base64.DEFAULT);
                        informations.put("photo",ba1);
                    }
                    Connect connect = new Connect(Publier.this,informations,"share",connnexion_info.IP,connnexion_info.WEB_SERVICE_FOLDER_MAKE_PUBLICATION);
                    connect.execute();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void showPictureDialog()
    {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Choisir une photo");
        String[] pictureDialogItems = {"Galerie", "Camera" };

        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent galleryIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(galleryIntent, GALLERY);
                                break;
                            case 1:
                                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, CAMERA);
                                break;
                        }
                    }
                });

        pictureDialog.show();
    }
    public String strToUtf8(String str){
        byte[] ptext = str.getBytes(ISO_8859_1);
        String value = new String(ptext, UTF_8);
        return value ;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != this.RESULT_CANCELED)
        {
            if (requestCode == CAMERA) {
                image = (Bitmap) data.getExtras().get("data");
                Toast.makeText(Publier.this, "Image ready!", Toast.LENGTH_SHORT).show();
            }

            if (requestCode == GALLERY) {
                if (data != null) {
                    Uri contentURI = data.getData();
                    try {
                        image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                        Toast.makeText(Publier.this, "Image ready!", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(Publier.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }
    }
}
