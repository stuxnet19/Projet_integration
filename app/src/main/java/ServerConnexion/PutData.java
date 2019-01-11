package ServerConnexion;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class PutData {
    private static PutData instance = new PutData();
    private PutData(){ }

    public static PutData getInstance() {
        return instance;
    }
    private static HashMap<String,String> prepareRequestStringInscription(String mail,String nom,String prenom,String mdp){
        HashMap<String,String> request = new HashMap<>();
        request.put("mail",mail);
        request.put("nom",nom);
        request.put("prenom",prenom);
        request.put("mdp",mdp);
        return request ;
    }
    public static String connecte(HashMap<String,String> information,String connexionType,String stringUrl){
        try {

            URL url = new URL(stringUrl);

            // get an urlconnectionobject from the URL object
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");

            //set the output capability on the URL Connection
            con.setDoOutput(true);

            // get an outputstream from the connexion
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            switch (connexionType){
                case "inscription" : {
                    String nom = information.get("nom");
                    String prenom = information.get("prenom");
                    String mail = information.get("mail");
                    String mdp = information.get("mdp");
                    out.writeBytes(ParameterStringBuilder.getParamsString(prepareRequestStringInscription(mail,nom,prenom,mdp)));
                }
            }
            // force bytes to be writen
            out.flush();
            out.close();
            //
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine ;
            StringBuffer content = new StringBuffer();
            while((inputLine = in.readLine())!=null){
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            return content.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null ;

    }
}
