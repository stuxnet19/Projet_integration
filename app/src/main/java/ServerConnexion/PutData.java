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
import java.util.Map;

public class PutData {
    private static PutData instance = new PutData();
    private PutData(){ }

    public static PutData getInstance() {
        return instance;
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
            out.writeBytes(ParameterStringBuilder.getParamsString(information));

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
