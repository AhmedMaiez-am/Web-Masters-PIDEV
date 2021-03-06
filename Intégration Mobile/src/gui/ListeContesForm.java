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
import entities.ContesAhmed;
import java.util.ArrayList;
import services.ServiceContesAhmed;

/**
 *
 * @author maiez
 */
public class ListeContesForm extends Form{
    public ListeContesForm (Resources theme){
        super(BoxLayout.y());
        setTitle("Liste des contes");
        Toolbar tb = getToolbar();
        tb.setTitleCentered(true);
        
         tb.addCommandToLeftBar("Retour",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new homeAhmed(theme).show();
            }
        });
        
        ServiceContesAhmed sc = new ServiceContesAhmed();
        ArrayList<ContesAhmed> a = sc.afficherContes();
        
        Component[]listToAdd = new Component[a.size()];
        
        for (int i=0; i<sc.afficherContes().size();i++){
            final ContesAhmed c = a.get(i);
            
            MultiButton mb = new MultiButton();
            mb.setTextLine1("Titre : "+a.get(i).getTitre());
            mb.setTextLine2("Auteur : "+a.get(i).getAuteur());
            final String t = a.get(i).getTitre();
            final String tt = a.get(i).getAuteur();
            Button btn = new Button("Ajouter");
            
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ContesAhmed c = new ContesAhmed(t,tt);
                    if (ServiceContesAhmed.getInstance().ajouterInvContes(c)){
                      Dialog.show("Succ??s", "La conte a ??t?? ajout?? avec succ??s ?? votre inventaire", new Command("OK"));
                      new ListeContesForm(theme).show();
                    }
                    else
                        Dialog.show("Succ??s", "La conte a ??t?? ajout?? avec succ??s ?? votre inventaire", new Command("OK"));
                    new ListeContesForm(theme).show(); 
                }
            });
            addAll(mb,btn);
        }
    }
    
}
