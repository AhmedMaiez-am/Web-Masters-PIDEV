/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import entities.RecuperationRecompense;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.scene.control.Alert;

/**
 *
 * @author Lenovo
 */
public class SmsForm extends Form{
    
    private Form previous;
    private String tel;
    public SmsForm(Form previous){
     
        setTitle("Envoi SMS");
        setLayout(BoxLayout.y());
        TextField tftel=new TextField("", "saisir votre numero", 13, TextField.ANY);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev->previous.showBack());
          Button btn=new Button("valider");
          
          btn.addActionListener(l->{
                  tel=tftel.getText();              
                  Sms(tel);
    });
          
        
        
        add(tftel);
        add(btn);     
    }
    
    public void Sms(String tel){
        
       
         String ACCOUNT_SID =
            "ACfeaf963c34a2bbd21d41a370d1011ce1";
     String AUTH_TOKEN =
            "30824205007b86b663a6c8895566dfc4";
          Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      
       Message message = Message.creator(new PhoneNumber(tel),
        new PhoneNumber("+13346058453"),"felicitation, votre fils a gagné une recompense merci de nous contacter").create();
          Dialog.show("success","sms send successfully", new Command("ok"));
//          Button btnr = new Button("Retour");
//          btnr.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent evt) {
//            new ListParentForm(current).show();             }
//         });

          
          
     
    }
    
}
