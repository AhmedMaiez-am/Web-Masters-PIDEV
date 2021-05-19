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
public class Parent {
    
    int idp;
    String nomp;
    String telp;
    String emailp;
   
    
    public Parent(){
        
    }

    public Parent(int idp, String nomp, String telp, String emailp) {
        this.idp = idp;
        this.nomp = nomp;
        this.telp = telp;
        this.emailp = emailp;
    }

    public Parent(String nomp, String telp, String emailp) {
        this.nomp = nomp;
        this.telp = telp;
        this.emailp = emailp;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public String getNomp() {
        return nomp;
    }

    public void setNomp(String nomp) {
        this.nomp = nomp;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getEmailp() {
        return emailp;
    }

    public void setEmailp(String emailp) {
        this.emailp = emailp;
    }

    @Override
    public String toString() {
        return "Parent{" + "idp=" + idp + ", nomp=" + nomp + ", telp=" + telp + ", emailp=" + emailp + '}';
    }
    
    
    
    
    
}
