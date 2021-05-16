/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Parent;
import com.mycompany.services.ServiceParent;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class ListParentForm extends Form{
    
    
    
    public ListParentForm(Form previous){
        
        setTitle("Liste Enfant");
        setLayout(BoxLayout.y());
         getContentPane().setScrollVisible(false);
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev->previous.showBack());

         ArrayList<Parent>list=ServiceParent.getInstance().getAllParent();
        for(Parent p:list){
            Label idp=new Label("idp: "+String.valueOf(p.getIdp()));
            Label nomTxt=new Label("nomp: "+p.getNomp());
            Label emailTxt=new Label("emailp: "+p.getEmailp());
            Label telpTxt=new Label("telp: "+p.getTelp());
              add(idp).add(nomTxt).add(emailTxt).add(telpTxt);

        }
        
        
        
        
    }
}
