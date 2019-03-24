package ServerConnexion;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.ImageView;

import com.example.nassim.projet_integration.Actualite_activity;
import com.example.nassim.projet_integration.R;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

/**
 * Created by nassim on 28/02/2019.
 */

public class Publication {
    // la publication est le model a aficher dans publication adapter
    public Bitmap image ;
    public String pseudo ;
    public String titre ;
    public String date_pub;
    public String publication_id ;
    public String contenu ;

    public Publication(String contenu,Bitmap image, String pseudo, String titre, String date_pub,String publication_id){
        this.image = image ;
        this.pseudo = pseudo ;
        this.titre = titre ;
        this.date_pub = date_pub ;
        this.publication_id = publication_id ;
        this.contenu = contenu ;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(String publication_id) {
        this.publication_id = publication_id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDate_pub() {
        return date_pub;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setDate_pub(String date_pub) {
        this.date_pub = date_pub;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public HashMap<String,String> getPublicationHashMap(Publication publication){
        HashMap<String,String> information = new HashMap<>();

        // get  String from bitmap object
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bm = image;
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        information.put("image",encodedImage);
        information.put("pseudo",pseudo);
        information.put("titre",titre);
        information.put("date_pub",date_pub);
        information.put("publication_id",publication_id);
        information.put("contenu",contenu) ;

        return information ;
    }
}
