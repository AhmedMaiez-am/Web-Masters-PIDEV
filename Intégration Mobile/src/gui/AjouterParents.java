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
import com.twilio.Twilio;
import entities.Enfant;
import entities.Parents;
import services.serviceEnfant;
import services.serviceParents;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

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
         TextField passwordparent = new TextField("", "telp", 20, TextField.BASELINE);
         TextField telp = new TextField("", "password", 20, TextField.EMAILADDR);
         TextField numcarte = new TextField("", "numcarte", 20, TextField.BASELINE);
         TextField passcarte = new TextField("", "passcarte", 100, TextField.EMAILADDR);
          Button btnp = new Button("Ajouter Compte");
        Button btn = new Button("Retour pour se Connecter");
        btnp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent et) {
                if ((nomparent.getText().length()==0)||(prenomparent.getText().length()==0)||(telp.getText().length()==0)||(numcarte.getText().length()==0)||(passwordparent.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                   

     Parents e = new  Parents(emailparent.getText(),nomparent.getText(), prenomparent.getText(),passwordparent.getText(),telp.getText(),numcarte.getText(),passcarte.getText());
                        if( serviceParents.getInstance().addParent(e)){
                            Dialog.show("Success","Ajouté accepted",new Command("OK"));
                        new LoginForm1(res).show();
                        } else
                            Dialog.show("Success", "Ajouté accepted", new Command("OK"));
                             new LoginForm1(res).show();
                    
        
       
         String ACCOUNT_SID =
            "ACfeaf963c34a2bbd21d41a370d1011ce1";
     String AUTH_TOKEN =
            "30824205007b86b663a6c8895566dfc4";
          Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      
       Message message = Message.creator(new PhoneNumber(passwordparent.getText()),
        new PhoneNumber("+13346058453"),"felicitation, votre compte a été créer ! Vous pouvez vous connecter").create();
          Dialog.show("success","sms send successfully", new Command("ok"));
          
     
    
                    }
                    
                }
                
                
            
        });
        btn.addActionListener(m -> new LoginForm1(res).show());
     
    addAll(emailparent,nomparent,prenomparent,passwordparent,telp,numcarte,passcarte,btnp);
    }
        
        
     
}
