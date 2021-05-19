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
import entities.Contes;
import java.util.ArrayList;
import services.ServiceContes;

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
                new home(theme).show();
            }
        });
        
        ServiceContes sc = new ServiceContes();
        ArrayList<Contes> a = sc.afficherContes();
        
        Component[]listToAdd = new Component[a.size()];
        
        for (int i=0; i<sc.afficherContes().size();i++){
            final Contes c = a.get(i);
            
            MultiButton mb = new MultiButton();
            mb.setTextLine1("Titre : "+a.get(i).getTitre());
            mb.setTextLine2("Auteur : "+a.get(i).getAuteur());
            final String t = a.get(i).getTitre();
            final String tt = a.get(i).getAuteur();
            Button btn = new Button("Ajouter");
            
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Contes c = new Contes(t,tt);
                    if (ServiceContes.getInstance().ajouterInvContes(c)){
                      Dialog.show("Succés", "La conte a été ajouté avec succés à votre inventaire", new Command("OK"));
                      new ListeContesForm(theme).show();
                    }
                    else
                        Dialog.show("Succés", "La conte a été ajouté avec succés à votre inventaire", new Command("OK"));
                    new ListeContesForm(theme).show(); 
                }
            });
            addAll(mb,btn);
        }
    }
    
}
