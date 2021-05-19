/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.regex.RE;
import com.mycompany.entities.RecuperationRecompense;
import com.mycompany.services.ServiceRecuperationRecompense;

/**
 *
 * @author Lenovo
 */
public class AddRecuperationForm extends Form{
    
      Form current;
    public AddRecuperationForm(Form previous){
        current=this;
                setTitle("ajout");

                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev->previous.showBack());
        
        TextField tn=new TextField("","nom recompense");
        TextField tnbr=new TextField("","nbre point");
        TextField temail=new TextField("","email");
        TextField tnomEn=new TextField("", "nom Enfant");
        Button btn=new Button("ajout");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if(tn.getText().length()==0 || tnbr.getText().length()==0 || temail.getText().length()==0
                       ||tnomEn.getText().length()==0){
                   Dialog.show("alert", "please fill all the blanks", new Command("ok"));
                              
               }
               if(!validateEmailAddress(temail.getText())){
                   Dialog.show("alert","verifier votre addresse email" , new Command("ok"));
               }
               else{
                   try{
                       RecuperationRecompense r=new RecuperationRecompense(tn.getText(), Integer.parseInt(tnbr.getText())
                               , temail.getText(),tnomEn.getText());
                   if(new ServiceRecuperationRecompense().addRecuperation(r)){
                        refreshTheme();
                       // Dialog.show("sucess","recompense ajouté", new Command("ok"));
                        new ListRecuperationForm(previous).show();
                   }
                    else
                        //Dialog.show("sucess","recompense ajouté" ,new Command("ok"));
                            new ListRecompenseForm(previous).show();
                   
                   
               }catch(NumberFormatException ex){
                 Dialog.show("alert","nbre de recompense must be a number", new Command("ok"));
                   
               }
                   
               }
            
        }
        }
        );
        
        addAll(tn,tnbr,temail,tnomEn,btn);
    }
    
    
    public boolean validateEmailAddress(String emailAddress) {
RE pattern = new RE("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
return pattern.match(emailAddress);
}
    
}
