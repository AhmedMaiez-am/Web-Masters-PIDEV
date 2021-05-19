/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Lenovo
 */
public class HomeFormRecup extends Form{
    private Button btnafficher,btnajouter,btnconsultEnf,btnconsultPar;
    Form previous;
    Form current;
    public HomeFormRecup(Form previous){
         current=this;

        setTitle("Recuperation");
        
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev->previous.showBack());

        btnajouter=new Button("ajouter ");
        btnafficher=new Button("afficher list  ");
        btnconsultEnf=new Button("consulter list enfant");
        btnconsultPar=new Button("Consulter list Parent");
        btnajouter.addActionListener(l->new AddRecuperationForm(current).show());
        btnafficher.addActionListener(l->new ListRecuperationForm(current).show());
        btnconsultEnf.addActionListener(l->new ListEnfantForm(current).show());
        btnconsultPar.addActionListener(l->new ListParentForm(current).show());
        addAll(btnajouter,btnafficher,btnconsultEnf,btnconsultPar);
    }
    
    
}
