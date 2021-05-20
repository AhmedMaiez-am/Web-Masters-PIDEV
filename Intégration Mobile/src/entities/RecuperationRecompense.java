/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Lenovo
 */
public class RecuperationRecompense {
    
    int idrecup;
    String nomrec;
    int nbrPoint;
    String emailp;
    String nomenfant;
    
    
    public RecuperationRecompense(){
        
    }

    public RecuperationRecompense(int idrecup, String nomrec, int nbrPoint, String emailp, String nomenfant) {
        this.idrecup = idrecup;
        this.nomrec = nomrec;
        this.nbrPoint = nbrPoint;
        this.emailp = emailp;
        this.nomenfant = nomenfant;
    }

    public RecuperationRecompense(String nomrec, int nbrPoint, String emailp, String nomenfant) {
        this.nomrec = nomrec;
        this.nbrPoint = nbrPoint;
        this.emailp = emailp;
        this.nomenfant = nomenfant;
    }

   
  

    public int getIdrecup() {
        return idrecup;
    }

    public void setIdrecup(int idrecup) {
        this.idrecup = idrecup;
    }

    public String getNomrec() {
        return nomrec;
    }

    public void setNomrec(String nomrec) {
        this.nomrec = nomrec;
    }

    public String getEmailp() {
        return emailp;
    }

    public void setEmailp(String emailp) {
        this.emailp = emailp;
    }

    public String getNomenfant() {
        return nomenfant;
    }

    public void setNomenfant(String nomenfant) {
        this.nomenfant = nomenfant;
    }

    public int getNbrPoint() {
        return nbrPoint;
    }

    public void setNbrPoint(int nbrPoint) {
        this.nbrPoint = nbrPoint;
    }

    @Override
    public String toString() {
        return "RecuperationRecompense{" + "idrecup=" + idrecup + ", nomrec=" + nomrec + ", emailp=" + emailp + ", nomenf=" + nomenfant + ", nbrPoint=" + nbrPoint + '}';
    }
    
    
    
    
}
