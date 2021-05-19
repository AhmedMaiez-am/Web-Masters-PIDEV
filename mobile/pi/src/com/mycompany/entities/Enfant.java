/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Lenovo
 */
public class Enfant {
    
    int idenfant;
    String nomenfant;
    String prenomenf;
    int nbrPoint;
    int idparent;
    public Enfant(){
        
    }

    public Enfant(int idenfant, String nomenfant, String prenomenf, int nbrPoint, int idparent) {
        this.idenfant = idenfant;
        this.nomenfant = nomenfant;
        this.prenomenf = prenomenf;
        this.nbrPoint = nbrPoint;
        this.idparent = idparent;
    }

   
    public Enfant(String nomenfant, String prenomenf, int nbrPoint) {
        this.nomenfant = nomenfant;
        this.prenomenf = prenomenf;
        this.nbrPoint = nbrPoint;
    }

    public int getIdenfant() {
        return idenfant;
    }

    public void setIdenfant(int idenfant) {
        this.idenfant = idenfant;
    }

    public int getIdparent() {
        return idparent;
    }

    public void setIdparent(int idparent) {
        this.idparent = idparent;
    }

    public String getNomenfant() {
        return nomenfant;
    }

    public void setNomenfant(String nomenfant) {
        this.nomenfant = nomenfant;
    }

    public String getPrenomenf() {
        return prenomenf;
    }

    public void setPrenomenf(String prenomenf) {
        this.prenomenf = prenomenf;
    }

    public int getNbrPoint() {
        return nbrPoint;
    }

    public void setNbrPoint(int nbrPoint) {
        this.nbrPoint = nbrPoint;
    }

    @Override
    public String toString() {
        return "Enfant{" + "idenfant=" + idenfant + ", nomenfant=" + nomenfant + ", prenomenf=" + prenomenf + ", nbrPoint=" + nbrPoint + ", idparent=" + idparent + '}';
    }

    
    
    
    
    
}
