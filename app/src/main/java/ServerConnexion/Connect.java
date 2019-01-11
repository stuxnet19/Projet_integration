package ServerConnexion;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Connect extends AsyncTask<Void,Integer,Void> {
    public String requestGet ;
    public String requestPut ;
    public Context context ;
    public String connexionType ;
    public TextView textView ;
    public HashMap<String,String> informations ;

    public Connect(Context context, HashMap<String,String> informations, String connexionType){
        this.connexionType = connexionType ;
        this.context=context;
        this.informations = informations ;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        switch (connexionType){
            case "connexion" : {
                requestGet = ExtractData.getData("stck_tracker","name,price","http://192.168.1.33/licenc3php/test.php");
            }
            case "inscription" :{
                ArrayList<String> colones = new ArrayList<String>(Arrays.<String>asList(""));
                // url a changer
                requestPut = PutData.connecte(informations,connexionType,"http://192.168.1.33/licenc3php/test.php");
            }
        }
        Log.d("respons",requestGet+"-------------");
        return null ;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        textView.setText("début du traitement");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Toast.makeText(context,"on progress",Toast.LENGTH_SHORT).show();
        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        textView.setText(requestGet);
        super.onPostExecute(aVoid);
    }
}
