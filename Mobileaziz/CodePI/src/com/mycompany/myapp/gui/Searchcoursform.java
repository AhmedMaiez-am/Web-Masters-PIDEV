/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Cours;
import com.mycompany.myapp.services.Service.Servicecours;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Searchcoursform  extends Form {
    
     String t;
      SpanLabel sp = new SpanLabel();
      SpanLabel nom =new SpanLabel();
      SpanLabel type =new SpanLabel();
      SpanLabel description =new SpanLabel();
      SpanLabel prix=new  SpanLabel();
      SpanLabel rate=new SpanLabel();
      SpanLabel separation = new SpanLabel("----------------------------");
        
     TextField tfName = new TextField("","nom du cours");
     
        Button btnValider = new Button("search User");
    public Searchcoursform(Form previous) {
    
    //serviceUser s =new serviceUser();
        
        setTitle("Listes  Des cours");
       Style s = UIManager.getInstance().getComponentStyle("Title");
        
       FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        //serviceUser s =new serviceUser();
        //tfName.addDataChangeListener();
        Form hi = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
       TextField searchField     = new  TextField ( " " , " search cours " );
       searchField . getHintLabel () . setUIID ( " Titre " );
searchField . setUIID ( " Titre " );
searchField . getAllStyles () . setAlignment ( Component .LEFT);
hi. getToolbar () . setTitleComponent (searchField); 
       
       searchField.addDataChangeListener((i1, i2) -> { // <2>
    String t = searchField.getText();
    if(t.length() < 1) {
        for(Component cmp : hi.getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
    } else {
        t = t.toLowerCase();
        //ArrayList<Cours> c;
          //                  c =Servicecours.getInstance().getAllUsers();
                        
                       
                      /* sp2.setText(u1.getUser_login());
                       sp3.setText(u1.getUser_name());
                       sp4.setText(u1.getUser_address());
                       sp5.setText(u1.getUser_phone());
                       sp6.setText(u1.getUser_password());*/
        
                      for(Component cmp : hi.getContentPane()) {
            String val = null;
            if(cmp instanceof Label) {
                val = ((Label)cmp).getText();
                
            } else {
                if(cmp instanceof TextArea) {
                    val = ((TextArea)cmp).getText();
                } else {
                    val = (String)cmp.getPropertyValue("text");
                }
            }
            boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
            cmp.setHidden(!show); // <3>
            cmp.setVisible(show);
        }
    }
    hi.getContentPane().animateLayout(250);
});
hi.getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
   searchField.startEditingAsync(); // <4>
});
/*hi.add("A Game of Thrones").
        add("A Clash Of Kings").
        add("A Storm Of Swords").
        add("A Feast For Crows").
        add("A Dance With Dragons").
        add("The Winds of Winter").
        add("A Dream of Spring");*/
//hi.show();
ArrayList<Cours> c;
 c =Servicecours.getInstance().getAllUsers();
 for(Cours u :c){
     hi.add(u.getNom()).
        add(u.getType()).
        add(u.getDescription()).
        add(u.getPrix());
     //   add(u.getUser_address(0));
      //  add("The Winds of Winter").
       // add("A Dream of Spring");
 }
 
 
      /*  btnValider.addActionListener(new ActionListener() {
        //    @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfName.getText().length()==0)
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        
                        ArrayList <Cours> c;
                        c = Servicecours.getInstance().getAllUsers();
                        t = tfName.getText();
                        Cours u1=new Cours();
                        u1=extract_user(c);
                        System.out.println(u1);
                        sp.setText(u1.toString());
               //          l.setText(u1.toString());
                       
        nom.setText("nom du cours :"+u1.getNom());
         type.setText("type du cours :"+u1.getType());
       description.setText("description :"+u1.getDescription());
         prix.setText("prix :"+u1.getPrix());
      // rate.setText(u1.getRate());
                        
                        
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });*/
        Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));
      
     
     //  y.add(tfName);
       // y.add(btnValider);
       // y.add(sp);
        add(hi);
       y.add(nom);
       y.add(type);
       y.add(description);
       y.add(prix);
       //y.add(rate);
    
    y.add(separation);           
        addAll(y);
       
        //y.add(nom);
      // 
    
        
    }
    
    public Cours extract_user(ArrayList<Cours> c){
        Cours u=new Cours();
        for (Cours c1 : c) {
                            if (c1.getNom().equals(t)){
                          u=c1;      
                                
                            }
                        } 
        return u;

}

    
    
    
    }
    

