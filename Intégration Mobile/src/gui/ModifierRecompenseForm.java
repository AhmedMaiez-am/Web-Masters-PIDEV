/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Recompense;
import services.ServiceRecompense;


/**
 *
 * @author Lenovo
 */
public class ModifierRecompenseForm  extends Form{
    
    Form current;
    Resources res;
    public  ModifierRecompenseForm(Form previous,Recompense rec,int id){
        setTitle("modifier Recompense");
         setLayout(BoxLayout.y());
         current=this;
        getContentPane().setScrollVisible(false);
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev->previous.showBack());
         TextField nomrec=new TextField(rec.getNomrec(),TextField.ANY);
       TextField nbrpoint=new TextField(String.valueOf(rec.getNbrPoint()),TextField.ANY);
        
        nomrec.setSingleLineTextArea(true);
        
        Button btnmodifier=new Button("modifier");
        btnmodifier.addPointerPressedListener(l->{
        
            rec.setNomrec(nomrec.getText());
            rec.setNbrPoint(Integer.valueOf(nbrpoint.getText()));
          
 
             if(ServiceRecompense.getInstance().modifierRecomepense(rec, rec.getIdrec())){
             System.out.println("hello");
            new ListRecompenseForm(previous).show();
             
        }   
              else{
               Dialog diag=new Dialog("modifier");
           diag.show("error","",new Command("ok"));
         }
        });
         
        
        //appel function service
       
        Button btnannuler=new Button("annuler");
        btnannuler.addActionListener(l->{
            new ListRecompenseForm(previous).show();
        });
        Container content=BoxLayout.encloseY(btnmodifier,btnannuler);
    
        addAll(nomrec,nbrpoint);
        add(content);
        show();
    }
    
    
    
    
}
