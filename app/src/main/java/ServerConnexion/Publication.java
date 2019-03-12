package ServerConnexion;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by nassim on 28/02/2019.
 */

public class Publication {
    // la publication est le model a aficher dans publication adapter
    private View image ;
    public String pseudo ;
    public String titre ;
    public String text ;

    public Publication(ImageView image, String pseudo, String titre, String text){
        this.image = image ;
        this.pseudo = pseudo ;
        this.titre = titre ;
        this.text = text ;
    }
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public View getImage() {
        return image;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getText() {
        return text;
    }

    public void setImage(View  image) {
        this.image = image;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setText(String text) {
        this.text = text;
    }
}
