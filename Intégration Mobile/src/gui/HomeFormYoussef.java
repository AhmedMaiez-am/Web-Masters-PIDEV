/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Lenovo
 */
public class HomeFormYoussef extends Form{
     Form current;
  Resources theme;
 
    Container cn;
    public HomeFormYoussef(Form previous){
        
        current=this;
        setTitle("Recompense");
        setLayout(BoxLayout.y());
      /* ImageViewer background=new ImageViewer(theme.getImage("back-logo.png"));
        cn=new Container(new FlowLayout(Component.CENTER));
        cn.add(background);
        add(background);*/
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev->previous.showBack());

        add(new Label("choisir une option"));
        Button btnajoutRec=new Button("ajouter Recompense ");
        Button btnafficheRec=new Button("afficher Recompense");
        
        btnajoutRec.addActionListener(e->new AddRecompenseForm(current).show());
        
        btnafficheRec.addActionListener(e->new ListRecompenseForm(current).show());
        addAll(btnajoutRec,btnafficheRec);
        
    }
    
    
}
