/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author asus
 */
public class Cours {
    private int idc;
    private String nom;
    private String type;
    private String description;
    private String cours;
    private String prix;
    private int rate;

    public Cours(int idc, String nom, String type, String description, String cours, String prix, int rate) {
        this.idc = idc;
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.cours = cours;
        this.prix = prix;
        this.rate = rate;
    }
    
    

    public Cours(String nom, String description, String prix, int rate) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.rate = rate;
    }

    public Cours() {
    }

    public int getIdc() {
        return idc;
    }


 
   public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCours() {
        return cours;
    }

    public void setCours(String cours) {
        this.cours = cours;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Cours{" + "nom=" + nom + ", type=" + type + ", description=" + description + ", prix=" + prix + ", rate=" + rate + '}';
    }

    public Cours(String nom, String type, String description, String cours, String prix, int rate) {
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.cours = cours;
        this.prix = prix;
        this.rate = rate;
    }

   
    
    
}
