/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author asus
 */
public class Contes {
    private int idcontes;
    private String titre;
    private String auteur;
    private String contes;
    private int  rate;

    public Contes(int idcontes, String titre, String auteur, String contes, int rate) {
        this.idcontes = idcontes;
        this.titre = titre;
        this.auteur = auteur;
        this.contes = contes;
        this.rate = rate;
    }

    public Contes() {
        
    }

    public int getIdcontes() {
        return idcontes;
    }

    public void setIdcontes(int idcontes) {
        this.idcontes = idcontes;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Contes{" + "idcontes=" + idcontes + ", titre=" + titre + ", auteur=" + auteur + ", contes=" + contes + ", rate=" + rate + '}';
    }

    public Contes(String titre, String auteur, String contes, int rate) {
        this.titre = titre;
        this.auteur = auteur;
        this.contes = contes;
        this.rate = rate;
    }
    
    
    
}
