/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author asus
 */
public class Homeform extends Form {

    
    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    private Resources theme;
    public Homeform() {
        current = this; //Récupération de l'interface(Form) en cours
     //   setTitle("Home");
       // setLayout(BoxLayout.y());

        //add(new Label("Choose an option"));
        //Button btnAddcours = new Button("Add cours");
        //Button btnListcours= new Button("List cours");
        //Button btnrecherche= new Button("List cours");
        

       //btnAddcours.addActionListener(e -> new Addcours(current).show());
        //btnListcours.addActionListener(e -> new Listcours(current).show());
        
        //btnrecherche.addActionListener(e -> new Searchcoursform(current).show());
//addAll(btnAddcours, btnListcours,btnrecherche);
  //
    
    Form current = null;
    Form hi = new Form("espace tuteur");
        
        //Form page1=new Form("Page 1",new FlowLayout(Component.CENTER, Component.CENTER));
        Addcours addcours =new Addcours(current);
       // addcours.add(new Label("Page 1"));
        
         addcours.getToolbar().addCommandToOverflowMenu("return", null, ev->{
            hi.show();
        });
        
        //page1.getToolbar().addMaterialCommandToLeftBar("test", FontImage.MATERIAL_BACKSPACE, null);
        
       /// Form page2 =new Form("Page 2",new FlowLayout(Component.CENTER, Component.CENTER));
       Listcours listcours = new Listcours (current);
       listcours.add(new Label("listes des cours"));
       listcours.getToolbar().addCommandToOverflowMenu("re", null, ev->{
            hi.show();
        });
        
        
        //Form page3=new Form("Page 3",new FlowLayout(Component.CENTER, Component.CENTER));
       Searchcoursform sea =new Searchcoursform (current);
        sea.add(new Label(" recherche cours"));
        
        sea.getToolbar().addCommandToOverflowMenu("return", null, ev->{
            hi.show();
        });
       /// Form page2 =new Form("Page 2",new FlowLayout(Component.CENTER, Component.CENTER));
       Listcontes listcontes = new Listcontes (current);
       listcontes.add(new Label("listes des cours"));
       listcontes.getToolbar().addCommandToOverflowMenu("re", null, ev->{
            hi.show();
        });
         Addcontes addcontes =new Addcontes(current);
       // addcours.add(new Label("Page 1"));
        
         addcontes.getToolbar().addCommandToOverflowMenu("return", null, ev->{
            hi.show();
        });
        Serachcon src =new Serachcon (current);
        src.add(new Label(" recherche cours"));
        
        src.getToolbar().addCommandToOverflowMenu("return", null, ev->{
            hi.show();
        });
        
        hi.getToolbar().addCommandToSideMenu("ajouter cours",theme.getImage("round.png"), e->{
            addcours.show();
        });
        hi.getToolbar().addCommandToSideMenu("liste des cours", theme.getImage("round.png"), e->{
            listcours.show();
        });
        hi.getToolbar().addCommandToSideMenu("recherche cours",null, e->{
            sea.show();
        });
         hi.getToolbar().addCommandToSideMenu("liste des contes", theme.getImage("round.png"), e->{
            listcontes.show();
        });
        
          hi.getToolbar().addCommandToSideMenu("ajouter contes",theme.getImage("round.png"), e->{
            addcontes.show();
        });
         
          hi.getToolbar().addCommandToSideMenu("recherche cours",null, e->{
            src.show();
        });
          
         hi.show();
        

    }
    
}
