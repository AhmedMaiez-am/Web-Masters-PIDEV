/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Parents;
import java.util.ArrayList;
import services.ServiceLogin;
import services.serviceParents;

/**
 *
 * @author HP
 */
public class ConsulterParent extends Form {
    public static String idparent ;

    public ConsulterParent (Resources res){ 
         super(BoxLayout.y());
       setTitle("Votre Compte ");
       
   Button signIn = new Button("Retour");
       // Button signUp = new Button("Sign Up");
     
        signIn.requestFocus();
        signIn.addActionListener(e -> new Home(res).show() );
                
                Parents pl=ServiceLogin.getInstance().getEvent(ServiceLogin.idparent);
               // System.out.println(ServiceLogin.idparent );
                //  nameLabel.setText("Email:"+ pl.getEmailP());
                  //Labeel.setText("Nom:"+ pl.getNomP());
                  //Labeel.setWidth(200);
                  // lb.setText("Prenom:"+ pl.getPrenomP());
                 //  lb1.setText("Portfeuille:"+ pl.getTelP());
                         MultiButton mb = new MultiButton();
                           mb.setTextLine1("Votre Informations ");
                         MultiButton mb1 = new MultiButton(); 
                            mb1.setTextLine1("Email:"+ pl.getEmailP());
                         MultiButton mb2 = new MultiButton();
                            mb2.setTextLine1("Nom:"+ pl.getNomP());
                         MultiButton mb3 = new MultiButton();
                            mb3.setTextLine1("PreNom:"+ pl.getPrenomP());
                         MultiButton mb4 = new MultiButton();
                            mb4.setTextLine1("Tel:"+ pl.getTelP());
                     addAll(signIn,mb,mb1,mb2,mb3,mb4);
    }}