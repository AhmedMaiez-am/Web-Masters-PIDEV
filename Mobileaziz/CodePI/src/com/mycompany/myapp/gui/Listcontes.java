/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import static com.codename1.push.PushContent.setTitle;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Contes;
import com.mycompany.myapp.services.Service.Servicecontes;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Listcontes  extends Form{
    
Form current;
ArrayList<Contes> data = new ArrayList<>();

public Listcontes(Form previous) {
    setTitle("Listes  Des contes");
    
    
    
  //  data = Servicecontes.instance.;
   
data=Servicecontes.getInstance().getcontes();
       
    Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    
  

    for (int i= 0;i<data.size();i++){
        Container x = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container xx = new Container(new BoxLayout(BoxLayout.Y_AXIS));
         Container xxx = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Contes c = new Contes();
        c.setIdcontes(data.get(i).getIdcontes());
        c.setTitre(data.get(i).getTitre());
        //u.setType(data.get(i).getType());
        c.setAuteur(data.get(i).getAuteur());
      c.setContes(data.get(i).getContes());
//  u.setDescription(data.get(i).getDescription());
       // u.setPrix(data.get(i).getPrix());
       
        Label separation = new Label("----------------------------");
        Label email = new Label("titre : " + data.get(i).getTitre());
        Label firstname = new Label("auteur : "+ data.get(i).getAuteur());
       // Label lastname = new Label("contes : "+ data.get(i).getContes());
        Label Supp=new Label("");
        Supp.setUIID("NewTopLine");
        Style Suppst=new Style(Supp.getUnselectedStyle());
        Suppst.setFgColor(0xf21f1f);
   FontImage suppi=FontImage.createMaterial(FontImage.MATERIAL_DELETE, Suppst);
   Supp.setIcon(suppi);
   Supp.setTextPosition(RIGHT);
   Supp.addPointerPressedListener(l-> {
           Dialog dig =new Dialog("supression");
           if (dig.show("supression","vous voulezsupprimer cette contes?","annuler","oui")){
           dig.dispose();
           }
           else
           {
               if(Servicecontes.getInstance().deletecontes(c)); 
           }
             });  
           //update button
        Label Mod=new Label("");
        Mod.setUIID("NewTopLine");
        Style Modst=new Style(Mod.getUnselectedStyle());
        Modst.setFgColor(0xf7ad02);    
   FontImage modi=FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, Modst);
   Mod.setIcon(modi);
   Mod.setTextPosition(LEFT);
   Mod.addPointerPressedListener(l-> {
         System.out.println("hello update cours");
 new Modifconte(current,c).show();     
   }); 
   
            
        x.addAll(BoxLayout.encloseY(BoxLayout.encloseX(email),BoxLayout.encloseX(firstname,Mod,Supp)));
        //xx.addAll(supp,modif);
        y.addAll(x,xx,separation);
    }
    
    
    
    
    addAll(y);
//    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
 //               , e-> previous.showBack()); // Revenir vers l'interface précédente
}


}