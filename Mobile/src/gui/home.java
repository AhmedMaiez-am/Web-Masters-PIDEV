/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author maiez
 */
public class home extends Form{
    Form current;
    
    public home(Resources res){
        current=this;
        setTitle("Espace Inventaires");
        setLayout(BoxLayout.y());
        
        ImageViewer img=new ImageViewer();
        img.setImage(res.getImage("logo.png"));
        
        
        Button btnInvCours = new Button ("Inventaire Cours");
        Button btnInvContes = new Button ("Inventaire Contes");
        Button btnListeCours = new Button ("Liste Cours");
        Button btnListeContes = new Button ("Liste Contes");
        
        btnInvCours.addActionListener(e -> new InventaireCoursForm(res).show());
        btnInvContes.addActionListener(e-> new InventaireContesForm(res).show());
        btnListeCours.addActionListener(e-> new ListeCoursForm(res).show());
        btnListeContes.addActionListener(e-> new ListeContesForm(res).show());
        addAll(img,btnInvCours,btnInvContes,btnListeCours,btnListeContes);
        
        
    }
}
