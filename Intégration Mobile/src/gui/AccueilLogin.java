/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author HP
 */
public class AccueilLogin extends Form {
    
     public AccueilLogin (Resources theme) {
         setTitle("WELCOME To KIDZY");
          Label lab = new Label("\n    Bienvenue KIDZY this is your new Platform for Education  ");
          Label lab1 = new Label("\n    Platform for Education  ");
          setLayout(BoxLayout.y());
    ImageViewer img=new ImageViewer();
    img.setImage(theme.getImage("cartoon.jpg"));
     Button Direc = new Button("Directeur");
     Button Tuteur = new Button("Tuteur");
     Button Parent = new Button("Parents");
     Direc.addActionListener(e -> new LoginDirecteur(theme).show());
     Parent.addActionListener(e1 -> new LoginForm1(theme).show());
     Tuteur.addActionListener(p -> new LoginTuteur(theme).show());
     addAll(lab,lab1,img,Direc,Tuteur,Parent);
     
     }
    
}
