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
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Inventairecours;
import java.util.ArrayList;
import services.ServiceInventaireCours;

/**
 *
 * @author maiez
 */
public class InventaireCoursForm extends Form{
    public InventaireCoursForm (Resources theme){
        super(BoxLayout.y());
       setTitle("Inventaire Cours");
        Toolbar tb = getToolbar();
        tb.setTitleCentered(true);
        
         tb.addCommandToLeftBar("Retour",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new homeAhmed(theme).show();
            }
        });
                
         ServiceInventaireCours sc = new ServiceInventaireCours();
         ArrayList<Inventairecours> a = sc.afficherInventaireCours();
         
         Component[]listToAdd = new Component[a.size()];
         
         for (int i=0; i<sc.afficherInventaireCours().size();i++){
             final String data = "Nom du cours : "+a.get(i).getNomc()+" Type du cours : "+a.get(i).getTypecc()+" Description du cours : "+a.get(i).getDescriptioncc();
             final Inventairecours c = a.get(i);
             int m = a.get(i).getIdcc();
             String s = String.valueOf(m);
             MultiButton mb = new MultiButton();
             mb.setTextLine1("Nom : "+a.get(i).getNomc());
             mb.setTextLine2("Type : "+a.get(i).getTypecc());
             mb.setTextLine3("Description : "+a.get(i).getDescriptioncc());
             
             Button btn = new Button("Supprimer");
                        btn.setUIID(s);
                      

        btn.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent evt) {
                     ServiceInventaireCours.getInstance().supprimerInvCours(btn.getUIID()) ;

                            Dialog.show("Success","Le cours a été supprimé avec succés",new Command("OK"));
                     new InventaireCoursForm(theme).show();
                 }
            
               
        });
                  
             addAll(mb,btn);
         }
    }
}
