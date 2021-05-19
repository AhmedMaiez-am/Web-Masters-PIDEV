/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Recompense;
import com.mycompany.services.ServiceRecompense;

import java.util.ArrayList;
import javafx.scene.control.ToolBar;


/**
 *
 * @author Lenovo
 */
public class ListRecompenseForm extends Form{

      Form current;
    Resources theme;
          
      public ListRecompenseForm(Form previous){
        setTitle("Liste Recompense");
          setLayout(BoxLayout.y());
      
          getContentPane().setScrollVisible(false);
          
         /* SpanLabel sp =new SpanLabel();
          sp.setText(ServiceRecompense.getInstance().getAllRecompense().toString());
      
          add(sp);*/
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev->previous.showBack());
        
           search();
           ArrayList<Recompense> list=ServiceRecompense.getInstance().getAllRecompense();
          
           for(Recompense rec:list){
                     
    
             /* String urlImage="back-logo.png";
             Image placeholder=Image.createImage(12, 90);
             EncodedImage en=EncodedImage.createFromImage(placeholder,false);
             URLImage urlim=URLImage.createToStorage(en, urlImage, urlImage, URLImage.RESIZE_SCALE);*/
          
            addButton(/*urlim,*/ rec, previous );
              /* ScaleImageLabel image=new ScaleImageLabel(urlim);
            Container containerImg=new Container();
         image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
        */
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
      
  
      private void  addButton(/*Image img ,*/Recompense rec,Form previous){
          
         /* int height =Display.getInstance().convertToPixels(1.5f);
          int width=Display.getInstance().convertToPixels(1f);
          
          Button image=new Button(img.fill(width, height));
          image.setUIID("label");
         Container cnt=BorderLayout.west(image);*/
                  Container cnt=new Container(BorderLayout.center());
          
          Label nomtext=new Label("nom= "+rec.getNomrec(),"newtopline");
       
          Label nbrText=new Label("nbr= " +rec.getNbrPoint(),"newtopline");
                  
        
          //button supprimer
          Label lsupprimer=new Label("");
          //lsupprimer.setUIID("Newstopline");
          Style supprimerStyle= new Style(lsupprimer.getUnselectedStyle());
          supprimerStyle.setFgColor(0xf21f1f);
          FontImage supprimerImage=FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
          lsupprimer.setIcon(supprimerImage);
          lsupprimer.setTextPosition(RIGHT);
          
          
          
          //click delete button
          lsupprimer.addPointerPressedListener(l->{
              Dialog dig=new Dialog("suppression");
              dig.show("Suppression","vous voulez supprimer",new Command("oui"));
              if(ServiceRecompense.getInstance().supprimerRecompense(rec.getIdrec())){
                     
                      new ListRecompenseForm(previous).show();
                  }
       
          });
         
           //button modifier
          Label lmodifier=new Label("");
          Style modifierStyle=new Style(lmodifier.getUnselectedStyle());
          modifierStyle.setFgColor(0xf7ad02);
          FontImage mfontimage=FontImage.createMaterial(FontImage.MATERIAL_EDIT, modifierStyle);
          lmodifier.setIcon(mfontimage);
          lmodifier.setTextPosition(LEFT);
          
          lmodifier.addPointerPressedListener(
                  l->{
          new ModifierRecompenseForm(previous, rec,rec.getIdrec()).show();
                  } );
          
         cnt.add(BorderLayout.WEST,BoxLayout.encloseY(BoxLayout.encloseX(nomtext,nbrText,lsupprimer,lmodifier)));
          
          
          add(cnt);
          
          
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
//tekhou el val ta3 el champ : champ li 3malt 3lih el recherche type span label (emplacement : container->container->spanlabel )
String val = ( (Label)  ((Container) ((Container) ((Container)cmp).getComponentAt(0)    ).getComponentAt(0)     ).getComponentAt(0)).getText();
System.out.println( ( (Label)  ((Container) ((Container) ((Container)cmp).getComponentAt(0)    ).getComponentAt(0)     ).getComponentAt(0)).getText()       );

boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
cmp.setHidden(!show);
cmp.setVisible(show);
}
}
getContentPane().animateLayout(250);
});
          
}


}