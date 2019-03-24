package ServerConnexion;

public class Comment {
    public String pseudo_commentaire ;
    public String date_commenatire ;
    public String text_commenatire ;

    public Comment(String pseudo_commentaire,String date_commenatire,String text_commenatire){
        this.pseudo_commentaire = pseudo_commentaire ;
        this.date_commenatire = date_commenatire ;
        this.text_commenatire = text_commenatire ;
    }

    public String getDate_commenatire() {
        return date_commenatire;
    }

    public String getPseudo_commentaire() {
        return pseudo_commentaire;
    }

    public String getText_commenatire() {
        return text_commenatire;
    }

    public void setDate_commenatire(String date_commenatire) {
        this.date_commenatire = date_commenatire;
    }

    public void setPseudo_commentaire(String pseudo_commentaire) {
        this.pseudo_commentaire = pseudo_commentaire;
    }

    public void setText_commenatire(String text_commenatire) {
        this.text_commenatire = text_commenatire;
    }
}
