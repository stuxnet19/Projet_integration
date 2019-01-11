package Users;

import java.util.HashMap;

/**
 * Created by nassim on 10/01/2019.
 */

public class Professionnels {
    public HashMap<String,String> informations ;
    private String civilite ;
    private String nom ;
    private String prenom ;
    private String profession ;
    private String mode_exercice ;
    private String site ;
    private int num_voie ;
    private String type_voie ;
    private String nom_voie ;
    private int code_postale ;
    private String comune ;
    private String Tel1 ;
    private String Tel2 ;
    private String mail ;
    private Boolean confirme ;
    private String mot_de_passe ;

    public String getCivilite() {
        return civilite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getMode_exercice() {
        return mode_exercice;
    }

    public void setMode_exercice(String mode_exercice) {
        this.mode_exercice = mode_exercice;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getNum_voie() {
        return num_voie;
    }

    public void setNum_voie(int num_voie) {
        this.num_voie = num_voie;
    }

    public String getType_voie() {
        return type_voie;
    }

    public void setType_voie(String type_voie) {
        this.type_voie = type_voie;
    }

    public String getNom_voie() {
        return nom_voie;
    }

    public void setNom_voie(String nom_voie) {
        this.nom_voie = nom_voie;
    }

    public int getCode_postale() {
        return code_postale;
    }

    public void setCode_postale(int code_postale) {
        this.code_postale = code_postale;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getTel1() {
        return Tel1;
    }

    public void setTel1(String tel1) {
        Tel1 = tel1;
    }

    public String getTel2() {
        return Tel2;
    }

    public void setTel2(String tel2) {
        Tel2 = tel2;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean getConfirme() {
        return confirme;
    }

    public void setConfirme(Boolean confirme) {
        this.confirme = confirme;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public Professionnels(HashMap<String,String> informations){
        this.informations = informations ;
    }

    public HashMap<String, String> getInformations() {
        return informations;
    }

    public void setInformations(HashMap<String, String> informations) {
        this.informations = informations;
    }
}
