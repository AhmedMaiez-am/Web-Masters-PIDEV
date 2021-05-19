/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author maiez
 */
public class Contes {
    private int idconte;
    private String titre;
    private String auteur;
    private String contes;
    private String rate;

    public Contes() {
    }

    public Contes(int idconte, String titre, String auteur, String contes, String rate) {
        this.idconte = idconte;
        this.titre = titre;
        this.auteur = auteur;
        this.contes = contes;
        this.rate = rate;
    }

    public Contes(String titre, String auteur, String contes, String rate) {
        this.titre = titre;
        this.auteur = auteur;
        this.contes = contes;
        this.rate = rate;
    }

    public Contes(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
    }

    public int getIdconte() {
        return idconte;
    }

    public void setIdconte(int idconte) {
        this.idconte = idconte;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getContes() {
        return contes;
    }

    public void setContes(String contes) {
        this.contes = contes;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
    
    
}
