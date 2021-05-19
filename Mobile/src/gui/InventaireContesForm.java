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
import entities.Inventairecontes;
import java.util.ArrayList;
import services.ServiceInventaireContes;

/**
 *
 * @author maiez
 */
public class InventaireContesForm extends Form {
    public InventaireContesForm (Resources theme){
        super(BoxLayout.y());
        setTitle("Inventaire Contes");
        Toolbar tb = getToolbar();
        tb.setTitleCentered(true);

         tb.addCommandToLeftBar("Retour",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new home(theme).show();
            }
        });
        ServiceInventaireContes sc = new ServiceInventaireContes();
        ArrayList<Inventairecontes> b = sc.afficherInventaireContes();
        
        Component[]listToAdd = new Component[b.size()];
        
        for (int i=0; i<sc.afficherInventaireContes().size();i++){
            final Inventairecontes c = b.get(i);
            int m =  b.get(i).getIdcontesc();
            String s = String.valueOf(m);
            MultiButton mb = new MultiButton();
            mb.setTextLine1("Titre :"+b.get(i).getTitrec());
            mb.setTextLine2("Auteur :"+b.get(i).getAuteurc());
            
            Button btn = new Button("Supprimer");
            btn.setUIID(s);
            
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (ServiceInventaireContes.getInstance().supprimerInvContes(btn.getUIID())){
                       Dialog.show("Succée de suppression", "La conte a été bien supprimé de votre inventaire", new Command("OK"));
                        new InventaireContesForm(theme).show();
                    }else
                        Dialog.show("Succée de suppression", "La conte a été bien supprimé de votre inventaire", new Command("OK"));
                    new InventaireContesForm(theme).show();
                }
            });
            addAll(mb,btn);
        }
    }
}
