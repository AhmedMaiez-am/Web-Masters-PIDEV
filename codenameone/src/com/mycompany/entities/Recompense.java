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
public class Recompense {
    int idrec;
    String nomrec;
    int nbrPoint;

    
public Recompense(){
    
}

    public Recompense(int idrec, String nomrec, int nbrPoint) {
        this.idrec = idrec;
        this.nomrec = nomrec;
        this.nbrPoint = nbrPoint;
    }

    public Recompense( String nomrec, int nbrPoint) {
      
        this.nomrec = nomrec;
        this.nbrPoint = nbrPoint;
    }

    public int getIdrec() {
        return idrec;
    }

    public void setIdrec(int idrec) {
        this.idrec = idrec;
    }

    public String getNomrec() {
        return nomrec;
    }

    public void setNomrec(String nomrec) {
        this.nomrec = nomrec;
    }

    public int getNbrPoint() {
        return nbrPoint;
    }

    public void setNbrPoint(int nbrPoint) {
        this.nbrPoint = nbrPoint;
    }

    @Override
    public String toString() {
        return "  nomrec=" + nomrec+"\n" + "nbrPoint=" + nbrPoint + '\n';
    }

    
   
    
    
}
