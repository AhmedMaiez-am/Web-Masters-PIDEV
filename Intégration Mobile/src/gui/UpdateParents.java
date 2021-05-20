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
import entities.Parents;
import services.ServiceLogin;

/**
 *
 * @author HP
 */
public class UpdateParents extends Form {
       public static int idparent ;

     public UpdateParents(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
              Toolbar tb = getToolbar();
  
          setTitle("Update Parent");
        setLayout(BoxLayout.y());
        
         TextField email = new TextField("", "Email", 20, TextField.BASELINE);
         TextField nom = new TextField("", "nom", 20, TextField.EMAILADDR);
         TextField prenom = new TextField("", "Prenom", 20, TextField.BASELINE);
         TextField telp = new TextField("", "Telephone", 20, TextField.EMAILADDR);
         TextField password = new TextField("", "Password", 20, TextField.BASELINE);
     //    TextField Carte = new TextField("", "Num Carte", 20, TextField.EMAILADDR);
    //     TextField passCarte = new TextField("", "Pass Carte", 20, TextField.EMAILADDR);
         Button btnValider = new Button("Update");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((nom.getText().length()==0)||(prenom.getText().length()==0)||(telp.getText().length()==0)||(password.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                   
                   Parents e = new  Parents(email.getText(), nom.getText(), prenom.getText(),telp.getText(),password.getText());
                    if( ServiceLogin.getInstance().updateParents(ServiceLogin.idparent , e))
                          Dialog.show("Success","Ajouté accepted",new Command("OK"));
                     else
                          Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                    }
                    
                }
                
                
            
        });
    addAll(email,nom,prenom,telp,password,btnValider);
    }
}
