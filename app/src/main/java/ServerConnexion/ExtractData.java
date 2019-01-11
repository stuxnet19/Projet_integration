package ServerConnexion;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class ExtractData {
    private static ExtractData instance = new ExtractData();
    private ExtractData(){ }
    public static ExtractData getInstance(){return instance;}
    private static HashMap<String,String> prepareRequestString(String tableName, String colonesList){

        HashMap<String,String> request = new HashMap<>();
        request.put("tableGet",tableName);
        request.put("colonesGet",colonesList);
        request.put("operation","getValues");

        return request;
    }
    public static String getData(String tableName,String colonesList,String stringUrl){

        try {
            URL url = new URL(stringUrl);

            // get an urlconnectionobject from the URL object
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");

            //set the output capability on the URL Connection
            con.setDoOutput(true);

            // get an outputstream from the connexion
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamsString(prepareRequestString(tableName, colonesList)));
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
            int status2 = con.getResponseCode();

            return content.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null ;

    }
}
