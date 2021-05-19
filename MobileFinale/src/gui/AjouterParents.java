/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entites.Enfant;
import entites.Parents;
import services.serviceEnfant;
import services.serviceParents;

/**
 *
 * @author HP
 */
public class AjouterParents extends Form {
    Resources res ;
      
     public AjouterParents (Resources res) { 
               super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
              Toolbar tb = getToolbar();
    
          setTitle("Ajouter Compte Parent");
       setLayout(BoxLayout.y());
        
         TextField emailparent = new TextField("", "Email", 20, TextField.BASELINE);
         TextField nomparent = new TextField("", "Nom", 20, TextField.BASELINE);
         TextField prenomparent = new TextField("", "Prenom", 20, TextField.EMAILADDR);
         TextField passwordparent = new TextField("", "password", 20, TextField.BASELINE);
         TextField telp = new TextField("", "telp", 20, TextField.EMAILADDR);
         TextField numcarte = new TextField("", "numcarte", 20, TextField.BASELINE);
         TextField passcarte = new TextField("", "passcarte", 100, TextField.EMAILADDR);
         TextField portfeuille = new TextField("", "portfeuille", 100, TextField.EMAILADDR);
          Button btnp = new Button("Ajouter Compte");
        Button btn = new Button("Retour pour se Connecter");
        btnp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent et) {
                if ((nomparent.getText().length()==0)||(prenomparent.getText().length()==0)||(telp.getText().length()==0)||(numcarte.getText().length()==0)||(passwordparent.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                   

     Parents e = new  Parents(emailparent.getText(),nomparent.getText(), prenomparent.getText(),passwordparent.getText(),telp.getText(),numcarte.getText(),passcarte.getText(),portfeuille.getText());
                        if( serviceParents.getInstance().addParent(e))
                            Dialog.show("Success","Ajouté accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                    }
                    
                }
                
                
            
        });
        btn.addActionListener(m -> new LoginForm(res).show());
     
    addAll(emailparent,nomparent,prenomparent,passwordparent,telp,numcarte,passcarte,portfeuille ,btnp);
    }
        
        
     
}
