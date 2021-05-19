/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.MultiButton;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entites.Enfant;
import entites.Parents;
import java.util.ArrayList;
import services.serviceEnfant;
import services.serviceParents;

/**
 *
 * @author HP
 */
public class ListeParents extends Form {
    Resources res ;
    public ListeParents (Resources theme) {
   
       super(BoxLayout.y());
       setTitle("Liste des Parents");
       Button ret = new Button("Retour");
        //signIn.requestFocus();
        ret.addActionListener(a-> new HomeDirecteur(theme).show() );
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
         
                serviceParents spp=new serviceParents();
                ArrayList<Parents>pl=spp.getListalls();
           //  setupSideMenu();
                   Component[] listingsToAdd = new Component[pl.size()];
                     for(int iter = 0 ; iter < spp.getListalls().size() ; iter++) {
                         final Parents p=pl.get(iter);
           int m = pl.get(iter).getIdP();
                         String s=String.valueOf(m);
                         MultiButton mb2 = new MultiButton();
                            mb2.setTextLine1("email:"+ pl.get(iter).getEmailP());
                            mb2.setTextLine2("Nom:"+ pl.get(iter).getNomP());
                           // mb.setTextLine3("diplomes:"+ pl.get(iter).getIde());
                           // mb.setTextLine4("password:"+ pl.get(iter).getAge());
                           // mb.setTextLine4("password:"+ pl.get(iter).getIdparent());
                           //   mb.setTextLine4("password:"+ pl.get(iter).getImage());
           
                       Button bt = new Button("Supprimer");
                        bt.setUIID(s);                      
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                     
                        if( serviceParents.getInstance().supprimer(bt.getUIID())) {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                                  
                        }
                        else
                            Dialog.show("Success","Supprimé",new Command("OK"));
                    
                }
               
        });
        
         addAll(mb2,bt);
                     }
                     addAll(ret);
    }

   

   

     

    
}
