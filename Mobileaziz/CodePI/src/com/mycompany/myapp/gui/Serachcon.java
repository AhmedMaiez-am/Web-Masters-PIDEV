/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Contes;
import com.mycompany.myapp.entities.Cours;
import com.mycompany.myapp.services.Service.Servicecontes;
import com.mycompany.myapp.services.Service.Servicecours;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Serachcon  extends Form{
 
     String t;
      SpanLabel sp = new SpanLabel();
      SpanLabel titre =new SpanLabel();
      SpanLabel auteur =new SpanLabel();
      SpanLabel contes =new SpanLabel();
      SpanLabel rate=new SpanLabel();
      SpanLabel separation = new SpanLabel("----------------------------");
        
     TextField tfName = new TextField("","nom du contes");
     
        Button btnValider = new Button("search conte");
    public Serachcon(Form previous) {
    
    //serviceUser s =new serviceUser();
        
        setTitle("Listes  Des contes");
        
        btnValider.addActionListener(new ActionListener() {
        //    @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfName.getText().length()==0)
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        
                        ArrayList <Contes> c;
                        c = Servicecontes.getInstance().getcontes();
                        t = tfName.getText();
                        Contes u1=new Contes();
                        u1=extract_user(c);
                        System.out.println(u1);
                        sp.setText(u1.toString());
               //          l.setText(u1.toString());
                       
        titre.setText("titre du contes :"+u1.getTitre());
         auteur.setText("auteur : :"+u1.getAuteur());
     //   contes.setText("contes :"+u1.getContes());
         //prix.setText("prix :"+u1.getPrix());
      // rate.setText(u1.getRate());
                        
                        
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));
      
     
        y.add(tfName);
        y.add(btnValider);
       // y.add(sp);
       y.add(titre);
       y.add(auteur);
       y.add(contes);
      // y.add(prix);
       //y.add(rate);
    
    y.add(separation);           
     addAll(y);
        //y.add(nom);
      // 
    
        
    }
    
    public Contes extract_user(ArrayList<Contes> c){
        Contes u=new Contes ();
        for (Contes c1 : c) {
                            if (c1.getTitre().equals(t)){
                          u=c1;      
                                
                            }
                        } 
        return u;
    }

    
}
