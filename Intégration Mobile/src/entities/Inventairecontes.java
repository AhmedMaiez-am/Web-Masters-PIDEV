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
public class Inventairecontes {
    
    private int idcontesc;
    private String titrec;
    private String auteurc;

    public Inventairecontes() {
    }

    public Inventairecontes(int idcontesc, String titrec, String auteurc) {
        this.idcontesc = idcontesc;
        this.titrec = titrec;
        this.auteurc = auteurc;
    }

    public Inventairecontes(String titrec, String auteurc) {
        this.titrec = titrec;
        this.auteurc = auteurc;
    }

    public int getIdcontesc() {
        return idcontesc;
    }

    public void setIdcontesc(int idcontesc) {
        this.idcontesc = idcontesc;
    }

    public String getTitrec() {
        return titrec;
    }

    public void setTitrec(String titrec) {
        this.titrec = titrec;
    }

    public String getAuteurc() {
        return auteurc;
    }

    public void setAuteurc(String auteurc) {
        this.auteurc = auteurc;
    }
    
    
}
