/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author HP
 */
public class Home extends Form {
   
    public Home (Resources res){
        
       setTitle("Home Parent");
    //   ImageViewer iv = new ImageViewer(res.getImage("catrn.jpg"));
   //   ImageViewer img = new ImageViewer();
       // img = new ImageViewer(res.getImage("catrn.jpg"));
       // addAll(img);
        Toolbar tb = getToolbar();
       
        Label lab = new Label("\n    Bienvenue ");
   tb.addCommandToSideMenu("Home", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Dialog.show("Alert!", "This is already our home", "OK", null);
            }
        });
            tb.addCommandToSideMenu("Liste Enfants", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new ListeEnfant(res).show();
            }
        });
            tb.addCommandToSideMenu("Ajouter Enfant", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new AjouterEnfant(res).show();
            }
        });
            tb.addCommandToSideMenu("Modifier", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new UpdateParents(res).show();
            }
        });
          tb.addCommandToSideMenu("Consulter Compte", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new ConsulterParent(res).show();
            }
        });    
           addAll(lab);
        }}



