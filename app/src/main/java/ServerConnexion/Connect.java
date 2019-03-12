package ServerConnexion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nassim.projet_integration.Accueil;
import com.example.nassim.projet_integration.Actualite_activity;
import com.example.nassim.projet_integration.Connexion_activity;
import com.example.nassim.projet_integration.Inscription_Activity;
import com.example.nassim.projet_integration.MainActivity;
import com.example.nassim.projet_integration.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    public String webServiceFolder ;
    SharedPreferences sharedPreferences ;

    public Connect(Context context, HashMap<String,String> informations, String connexionType,String ip,String webServiceFolder){
        this.connexionType = connexionType ;
        this.context=context;
        this.informations = informations ;
        this.ip = ip ;
        this.webServiceFolder = webServiceFolder ;
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
        switch (connexionType){
            case "actualite" : requestPut = PutData.connecte(informations,connexionType,"http://"+ip+webServiceFolder);
                break ;
            case "inscription" : requestPut = PutData.connecte(informations,connexionType,"http://"+ip+webServiceFolder);
                break;
            case "connexion" : requestPut = PutData.connecte(informations,connexionType,"http://"+ip+webServiceFolder);
                break;
        }
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
            case "actualite" :{
                if (requestPut!=null){
                    try {
                        JSONArray jsonArray = new JSONArray(requestPut);
                        //--------------------------------------------
                        View view = View.inflate(context, R.layout.activity_actualite_activity, null);
                        ListView listView = (ListView) view.findViewById(R.id.my_list);
                        List<Publication> publications = genererPublications(jsonArray);
                        Publication_adapter adapter = new Publication_adapter(context,publications);
                        listView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
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
        }
        super.onPostExecute(aVoid);
    }
    private List<Publication> genererPublications(JSONArray jsonArray){
        List<Publication> publications = new ArrayList<Publication>();
        try {
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return publications;

    }
}
