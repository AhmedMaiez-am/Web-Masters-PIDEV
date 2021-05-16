/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;


/**
 *
 * @author Lenovo
 */
public class HomeForm0 extends Form{
    Form current ;
    Button btnRecompense,btnRecuperation;
    
    public HomeForm0(Form previous){
        current=this;
        
        setTitle("Home");
        setLayout(BoxLayout.y());
             

        btnRecompense=new Button("Recompense");
        btnRecuperation=new Button("Recuperation");
        btnRecompense.addActionListener(l->new HomeForm(current).show());
        btnRecuperation.addActionListener(l-> new HomeFormRecup(current).show());
        
        addAll(btnRecompense,btnRecuperation);
    }
    
}
