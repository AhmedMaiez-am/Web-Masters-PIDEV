/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Quiz;
import services.ServiceQuiz;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author IHEB
 */
public class Search  extends Form{
    
 
     String t;
      SpanLabel sp = new SpanLabel();
      SpanLabel titre =new SpanLabel();
      SpanLabel type =new SpanLabel();
      SpanLabel contes =new SpanLabel();
      SpanLabel rate=new SpanLabel();
      SpanLabel separation = new SpanLabel("----------------------------");
        
     TextField tfName = new TextField("","nom du Quiz");
     
        Button btnS = new Button("Rechercher Quiz");
        
    public Search(Resources theme) {
        super(BoxLayout.y());
         Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        //  setUIID("lolo");
        tb.addCommandToLeftBar("Back", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListQuiz(theme).show();
            }
        });
    
    //serviceUser s =new serviceUser();
        
        setTitle("Listes  Des Quizs");
        
        btnS.addActionListener((ActionListener) (ActionEvent evt) -> {
            if (tfName.getText().length()==0)
                Dialog.show("Alert", "Le Champ est vide", new Command("OK"));
            else
            {
                try {
                    
                    ArrayList <Quiz> c;
                    c = ServiceQuiz.getInstance().getListalls();
                    t = tfName.getText();
                    Quiz u1=new Quiz();
                    u1=extract_user(c);
                    System.out.println(u1);
                    sp.setText(u1.toString());
                    //          l.setText(u1.toString());
                    
                    titre.setText("Nom de Quiz :"+u1.getTitle());
                            titre.setUIID("text");
                    if (u1.getIsamericain() == 1)
                    type.setText("Type de Quiz : Americain");
                    else
                    type.setText("Type de Quiz : Normal");    
                     titre.setUIID("text");
                    //   contes.setText("contes :"+u1.getContes());
                    //prix.setText("prix :"+u1.getPrix());
                    // rate.setText(u1.getRate());
                    
                    
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                }
                
            }
        }
        );
        Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));
      
     
        y.add(tfName);
        y.add(btnS);
       // y.add(sp);
       y.add(titre);
       y.add(type);
      
    btnS.setUIID("nono");
    
    y.add(separation);           
     addAll(y);
        //y.add(nom);
      // 
     
        
    }
    
    public Quiz extract_user(ArrayList<Quiz> c){
        Quiz u=new Quiz ();
        for (Quiz c1 : c) {
                            if (c1.getTitle().equals(t)){
                          u=c1;      
                                
                            }
                        } 
        return u;
    }

    
}