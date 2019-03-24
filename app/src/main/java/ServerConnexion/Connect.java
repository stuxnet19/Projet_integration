package ServerConnexion;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nassim.projet_integration.Accueil;
import com.example.nassim.projet_integration.Actualite_activity;
import com.example.nassim.projet_integration.Commentaires;
import com.example.nassim.projet_integration.Connexion_activity;
import com.example.nassim.projet_integration.Inscription_Activity;
import com.example.nassim.projet_integration.MainActivity;
import com.example.nassim.projet_integration.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Connect extends AsyncTask<Void,Integer,Void> {
    public String requestPut ;
    public Context context ;
    public String connexionType ;
    public HashMap<String,String> informations ;
    public int inscriptionResult ;
    public String ip ;
    public ListView listView ;
    public String webServiceFolder ;
    SharedPreferences sharedPreferences ;

    public Connect(Context context, HashMap<String,String> informations, String connexionType,String ip,String webServiceFolder){
        this.connexionType = connexionType ;
        this.context=context;
        this.informations = informations ;
        this.ip = ip ;
        this.webServiceFolder = webServiceFolder ;
    }
    public Connect(Context context, HashMap<String,String> informations, String connexionType,String ip,String webServiceFolder,ListView listView){
        this.connexionType = connexionType ;
        this.context=context;
        this.informations = informations ;
        this.ip = ip ;
        this.webServiceFolder = webServiceFolder ;
        this.listView = listView ;
    }

    public String getRequestPut() {
        return requestPut;
    }

    public int getInscriptionResult() {
        return inscriptionResult;
    }

    public void setInscriptionResult(int inscriptionResult) {
        this.inscriptionResult = inscriptionResult;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        //url a changer
        requestPut = PutData.connecte(informations,connexionType,"http://"+ip+webServiceFolder);
        Log.d("+++++++++",informations.toString());
        Log.d("+++++++++",requestPut);
        return null ;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Toast.makeText(context,"on progress",Toast.LENGTH_SHORT).show();
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        switch (connexionType){

            case "commentaire" : {

                if ((requestPut != null)&&(requestPut!="0")){
                    try{
                        Intent intent = ((Activity) context).getIntent();
                        String contenu = intent.getStringExtra("contenu");
                        String pseudo = intent.getStringExtra("pseudo");
                        String date_pub = intent.getStringExtra("date_pub");
                        JSONArray jsonArray = new JSONArray(requestPut);
                        final List<Comment> commentaires = genererCommentaires(jsonArray,pseudo,contenu,date_pub);
                        Comment_adapter adapter = new Comment_adapter (context,commentaires);
                        listView.setAdapter(adapter);

                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
            break ;
            case "actualite" : {
                if ((requestPut!=null)&&(requestPut!="0")){
                    try {
                        JSONArray jsonArray = new JSONArray(requestPut);
                        //--------------------------------------------
                        final List<Publication> publications = genererPublications(jsonArray);
                        Publication_adapter adapter = new Publication_adapter(context,publications);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Publication value = (Publication)parent.getItemAtPosition(position);
                                Intent intent = new Intent(context,Commentaires.class);
                                intent.putExtra("publication_id",value.getPublicationHashMap(value).get("publication_id"));
                                intent.putExtra("contenu",value.getPublicationHashMap(value).get("contenu"));
                                intent.putExtra("pseudo",value.getPublicationHashMap(value).get("pseudo"));
                                intent.putExtra("date_pub",value.getPublicationHashMap(value).get("date_pub"));
                                context.startActivity(intent);
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
            case "inscription" : {
                setInscriptionResult(0);
                if (requestPut!=null){
                    if (requestPut.equals("1")){
                        setInscriptionResult(1);
                        Toast.makeText(context,"inscription réussit un mail de confirmation vous a était envoyé",Toast.LENGTH_LONG).show();
                    }
                    else Toast.makeText(context,"inscription échoué veillez vérifier que vous étes bien inscrit dans l'anuaire des médecins francais .",Toast.LENGTH_LONG).show();
                }
            }
            break;
            case "connexion" : {
                if (requestPut!=null){
                    Log.d("user info","++++++++++++++++++++"+requestPut+"++++++"+informations);
                    if (requestPut.equals("0")) Toast.makeText(context,"impossible de se connecter veillez vérifier que vous avez entré les informations corrécte et que vous avez confirmé votre inscription .",Toast.LENGTH_LONG).show();
                    else{
                        try {
                            JSONArray jsonArray = new JSONArray(requestPut);
                            Log.d("user info","===================="+jsonArray.getJSONObject(0));
                            Toast.makeText(context,"connexion réussit ",Toast.LENGTH_LONG).show();
                            // enregistrer les information de l'utilisateur dans un sharedpreferences
                            // sa va agire comme une sorte de session
                            // les informations serons suprimé dé que l'utilisateur clic sur le bouton deconexion
                            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.commit();
                            editor.putString("data",jsonArray.getJSONObject(0).toString());
                            editor.apply();
                            Intent intent = new Intent(context,Accueil.class);
                            context.startActivity(intent);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
            break;
        }
        super.onPostExecute(aVoid);
    }
    private List<Comment> genererCommentaires(JSONArray jsonArray,String pseudo,String contenu,String date_pub){
        List<Comment> commentairesList = new ArrayList<>();
        byte[] decodeString ;
        Bitmap decodeByte ;
        String date_comment ;
        String text_comment ;
        commentairesList.add(new Comment(pseudo,date_pub,contenu));
        try{
            for (int i=0 ;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                pseudo = (String) jsonObject.get("nom")+" "+jsonObject.get("prenom");
                date_comment = (String) jsonObject.get("date_com");
                text_comment = (String) jsonObject.get("contenu");
                Comment commmentaire = new Comment(pseudo,date_comment,text_comment);
                commentairesList.add(commmentaire) ;
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        return commentairesList ;
    }

    private List<Publication> genererPublications(JSONArray jsonArray){
        List<Publication> publications = new ArrayList<Publication>();
        byte[] decodeString ;
        Bitmap decodeByte;
        String pseudo ;
        String titre ;
        String date_pub ;
        String publication_id ;
        String contenu ;
        try {
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // transformer l'image en base 64 a imageview
                try{
                    decodeString = Base64.decode((String)jsonObject.get("photo"),Base64.DEFAULT);
                    decodeByte = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
                }
                catch (NullPointerException e){
                    // default image from drawable to base 64

                    Bitmap bitmapOrg = BitmapFactory.decodeResource(context.getResources(),  R.drawable.images);
                    ByteArrayOutputStream bao = new ByteArrayOutputStream();
                    bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 100, bao);
                    byte [] ba = bao.toByteArray();
                    String ba1=Base64.encodeToString(ba,Base64.DEFAULT);


                    decodeString = Base64.decode(ba1,Base64.DEFAULT);
                    decodeByte = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
                }
                //--------------------------------------------
                pseudo = jsonObject.get("nom")+" "+jsonObject.get("prenom");
                titre = (String) jsonObject.get("titre");
                date_pub = (String)jsonObject.get("date_pub");
                publication_id = (String)jsonObject.get("id_pub");
                contenu = (String)jsonObject.get("contenu");
                Publication publication = new Publication(contenu,decodeByte,pseudo,titre,date_pub,publication_id);
                publications.add(publication);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return publications;
    }
}
