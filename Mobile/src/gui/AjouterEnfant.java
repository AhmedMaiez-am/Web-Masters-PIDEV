/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.io.FileSystemStorage;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import entites.Enfant;
import java.io.IOException;
import services.serviceEnfant;

/**
 *
 * @author HP
 */
public class AjouterEnfant extends Form {
     String path ;
    public AjouterEnfant(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
              Toolbar tb = getToolbar();
       // tb.addCommandToRightBar("Back", null, new ActionListener() {
         // @Override
                 //   public void actionPerformed(ActionEvent evt) {
    //  new listEvenementImage(res).show();        
//}    } );
    
          setTitle("AJOUTER UN Enfant");
        setLayout(BoxLayout.y());
        
         TextField nom = new TextField("", "Nom", 20, TextField.BASELINE);
         TextField prenom = new TextField("", "Prenom", 20, TextField.EMAILADDR);
         TextField age = new TextField("", "Age", 20, TextField.BASELINE);
         TextField idparent = new TextField("", "IdParent", 20, TextField.EMAILADDR);
         TextField password = new TextField("", "Password", 20, TextField.BASELINE);
         TextField image = new TextField("", "image", 20, TextField.EMAILADDR);
         
        Button AjouterImg = new Button("ajouter image ");
         AjouterImg.addActionListener(e -> {
         
            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (ev != null && ev.getSource() != null) {
                        path = (String) ev.getSource();
                        System.out.println(path.substring(1));
                        Image img = null;
                       image.setText(path);
                        System.out.println(path);
                        
                    }
                }
            }, Display.GALLERY_IMAGE);
        });
         Button btnValider = new Button("Add Enfant");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((nom.getText().length()==0)||(prenom.getText().length()==0)||(age.getText().length()==0)||(idparent.getText().length()==0)||(password.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                   

                      Enfant e = new  Enfant(nom.getText(),  prenom.getText(),Integer.parseInt(age.getText()),Integer.parseInt(idparent.getText()),password.getText(), image.getText());
                        if( serviceEnfant.getInstance().addEnfant(e))
                            Dialog.show("Success","Ajouté accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                    }
                    
                }
                
                
            
        });
    addAll(nom,prenom,age,idparent,password,image,AjouterImg,btnValider);
    }
        
        
    
}
