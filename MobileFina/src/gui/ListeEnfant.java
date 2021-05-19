/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entites.Enfant;
import java.util.ArrayList;
import services.serviceEnfant;

/**
 *
 * @author HP
 */
public class ListeEnfant extends Form {
    
    
   public ListeEnfant(Resources res) {
   
       super(BoxLayout.y());
       setTitle("Liste des Enfants");
         
       Button retour = new Button("Retour");
        retour.requestFocus();
        retour.addActionListener(m -> new HomeDirecteur(res).show() );
        
        
                   serviceEnfant spp=new serviceEnfant();
                ArrayList<Enfant>pl=spp.getListalls();
          
                   Component[] listingsToAdd = new Component[pl.size()];
                     for(int iter = 0 ; iter < spp.getListalls().size() ; iter++) {
                         final Enfant p=pl.get(iter);
                         int m = pl.get(iter).getIde();
                         String s=String.valueOf(m);
                         MultiButton mb = new MultiButton();
                            mb.setTextLine1("email:"+ pl.get(iter).getNomenfant());
                            mb.setTextLine2("cv:"+ pl.get(iter).getPrenomenfant());
                           // mb.setTextLine3("diplomes:"+ pl.get(iter).getIde());
                           // mb.setTextLine4("password:"+ pl.get(iter).getAge());
                           // mb.setTextLine4("password:"+ pl.get(iter).getIdparent());
                           //   mb.setTextLine4("password:"+ pl.get(iter).getImage());
           
                       Button btnValider = new Button("Supprimer");
                        btnValider.setUIID(s);
                        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent et) {
           //     System.out.println(btnValider.getUIID());
                     
                        if( serviceEnfant.getInstance().supprimer(btnValider.getUIID())) {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                                   new ListeEnfant(res).show();
                                 
                        }
                        else
                            Dialog.show("Success","Ajouté accepted",new Command("OK"));
                    
                }
               
        });
        
         addAll(mb,btnValider);
                     }
                     addAll(retour);
   }

   

   

     
}
