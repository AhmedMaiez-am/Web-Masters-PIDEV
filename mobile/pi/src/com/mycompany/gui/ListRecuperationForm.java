/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.entities.RecuperationRecompense;
import com.mycompany.services.ServiceRecompense;
import com.mycompany.services.ServiceRecuperationRecompense;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class ListRecuperationForm extends Form{
      Form current;
      
    public ListRecuperationForm(Form previous){
        current=this;
        setTitle("liste Recuperation");
        setLayout(BoxLayout.y());
       getContentPane().setScrollVisible(false);
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev->previous.showBack());

        ArrayList<RecuperationRecompense> lRec=ServiceRecuperationRecompense.getInstance().getAllRecuperation();
         search();    
        for(RecuperationRecompense rec:lRec){
           
           
        
            addButton(rec, previous);
        }
        
    
        }
        
      public void bindButtonSelection(Button btn,Label l){
          
          btn.addActionListener(e->{
              if(btn.isSelected())
              {
                  updateArrowPosition (btn ,  l);
              }
          });
          
      }
      void updateArrowPosition(Button btn,Label l){
          
          l.getUnselectedStyle().setMargin(LEFT, btn.getX()+btn.getWidth()/2 - l.getWidth()/2);
          l.getParent().repaint();
          
      }
            
    private void addButton(RecuperationRecompense r,Form previous){
        
            //button supprimer
          Label lsupprimer=new Label("");
          //lsupprimer.setUIID("Newstopline");
          Style supprimerStyle= new Style(lsupprimer.getUnselectedStyle());
          supprimerStyle.setFgColor(0xf21f1f);
          FontImage supprimerImage=FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
          lsupprimer.setIcon(supprimerImage);
          lsupprimer.setTextPosition(RIGHT);
           Label nomTxt=new Label("nom recompense= "+r.getNomrec());
           
             Label nbrTxt=new Label("nbr point= "+String.valueOf(r.getNbrPoint()));
             
             Label emailTxt=new Label("email= "+r.getEmailp()); 
             

          Label nomenfTxt=new Label("nom enfant= "+r.getNomenf());
          
          Container cn=new Container(BorderLayout.center());
          //click delete button
          lsupprimer.addPointerPressedListener(l->{
              Dialog dig=new Dialog("suppression");
              dig.show("Suppression","vous voulez supprimer?",new Command("oui"));
              if(ServiceRecuperationRecompense.getInstance().supprimerRecuperation(r.getIdrecup())){
                     
                      new ListRecuperationForm(previous).show();
                  }
       
          });
           
          //button mail
          Label lmail=new Label("");
          Style mailStyle=new Style(lmail.getUnselectedStyle());
          mailStyle.setFgColor(0xf7ad02);
          FontImage mailImg=FontImage.createMaterial(FontImage.MATERIAL_MAIL, mailStyle);
          lmail.setIcon(mailImg);
          lmail.setTextPosition(RIGHT);
          //click button mail
          lmail.addPointerPressedListener(l->{
              new MailForm(previous,r).show();
          });
          
                Container cn1=new Container(BorderLayout.center());
                   Container cn2=new Container(BorderLayout.center());
                   Container cn3=new Container(BorderLayout.center());
          cn1.add(BorderLayout.WEST,BoxLayout.encloseY(BoxLayout.encloseX(nomTxt)));
        cn2.add(BorderLayout.WEST,BoxLayout.encloseY(BoxLayout.encloseX(nbrTxt)));
        cn3.add(BorderLayout.WEST,BoxLayout.encloseY(BoxLayout.encloseX(nomenfTxt)));
          add(cn1).add(cn2).add(cn3);
         
          //add(nomTxt ).add(nbrTxt).add(nomenfTxt);
        cn.add(BorderLayout.WEST,BoxLayout.encloseY(BoxLayout.encloseX(emailTxt,lsupprimer,lmail)));
        add(cn);
       
    }
    
    
      public void search(){
          TextField searchField; 
         searchField = new TextField("", "rechercher par nom ");
       
searchField.getHintLabel().setUIID("Title");
searchField.setUIID("Title");
getToolbar().setTitleComponent(searchField);
//if field content changed
searchField.addDataChangeListener((int i1, int i2) -> {
String t = searchField.getText();
if(t.length() < 1) {
for(Component cmp : getContentPane()) {
cmp.setHidden(false);
cmp.setVisible(true);
}
} else {
t = t.toLowerCase();
for(Component cmp: getContentPane()) {
//tekhou el val ta3 el champ : champ li 3malt 3lih el recherche type label (emplacement : container->container->label )
String val = ( (Label)((Container) ((Container)((Container)cmp).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getText();
    System.out.println( ((Label)((Container) ((Container)((Container)cmp).getComponentAt(0)).getComponentAt(0)).getComponentAt(0)).getText());    

//System.out.println( ( (Label)  ((Container) ((Container) ((Container)cmp).getComponentAt(0)    ).getComponentAt(0)     ).getComponentAt(0)).getText()       );
boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
cmp.setHidden(!show);
cmp.setVisible(show);
}
}
getContentPane().animateLayout(250);
});
          
}

    
    
    
}
