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
public class Pp {
    private int idp;
    private String emailp;
    private String nomp;
    private String prenomp;
    private String telp;
    private String passwordp;
    private String numcarte;
    private String passcarte;
    private String portefeuille;

    public Pp() {
    }

    public Pp(int idp, String emailp, String nomp, String prenomp, String telp, String passwordp, String numcarte, String passcarte, String portefeuille) {
        this.idp = idp;
        this.emailp = emailp;
        this.nomp = nomp;
        this.prenomp = prenomp;
        this.telp = telp;
        this.passwordp = passwordp;
        this.numcarte = numcarte;
        this.passcarte = passcarte;
        this.portefeuille = portefeuille;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public String getEmailp() {
        return emailp;
    }

    public void setEmailp(String emailp) {
        this.emailp = emailp;
    }

    public String getNomp() {
        return nomp;
    }

    public void setNomp(String nomp) {
        this.nomp = nomp;
    }

    public String getPrenomp() {
        return prenomp;
    }

    public void setPrenomp(String prenomp) {
        this.prenomp = prenomp;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getPasswordp() {
        return passwordp;
    }

    public void setPasswordp(String passwordp) {
        this.passwordp = passwordp;
    }

    public String getNumcarte() {
        return numcarte;
    }

    public void setNumcarte(String numcarte) {
        this.numcarte = numcarte;
    }

    public String getPasscarte() {
        return passcarte;
    }

    public void setPasscarte(String passcarte) {
        this.passcarte = passcarte;
    }

    public String getPortefeuille() {
        return portefeuille;
    }

    public void setPortefeuille(String portefeuille) {
        this.portefeuille = portefeuille;
    }


    
    
    
    
}
