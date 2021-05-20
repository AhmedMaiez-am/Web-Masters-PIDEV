/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import entities.Recompense;
import services.ServiceRecompense;

/**
 *
 * @author Lenovo
 */
public class AddRecompenseForm  extends Form{
      Form current;
    
     public AddRecompenseForm(Form previous){
         current=this;
        setTitle("ajout");
         setLayout(BoxLayout.y());
         TextField tfname=new TextField("", "nom");
        
         TextField tfnbr=new TextField("", "nbre");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev->previous.showBack());

         Button btn=new Button("ajouter");
       
         btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if(tfname.getText().length()==0 ||tfnbr.getText().length()==0)
                   Dialog.show("Alert", "please fill all the blanks", new Command("ok"));
               else{
                   try {
                    Recompense r=new Recompense(tfname.getText(),Integer.parseInt(tfnbr.getText()));
                       
                    if(new ServiceRecompense().addRecompense(r))
                    {
                        refreshTheme();
                        Dialog.show("sucess","recompense ajouté", new Command("ok"));
                         new ListRecompenseForm(previous).show();
                    }
                    else
                        //Dialog.show("sucess","recompense ajouté" ,new Command("ok"));
                            new ListRecompenseForm(previous).show();
                    
                    
                   } catch (NumberFormatException e) {
                   
                   Dialog.show("alert","nbre de recompense must be a number", new Command("ok"));
                   }
               }
               
            }
            
        });
         
         
         addAll(tfname,tfnbr,btn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev->previous.showBack());
    }
     
    
  

  
}
