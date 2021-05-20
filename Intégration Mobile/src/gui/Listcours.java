/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Cours;
import services.Servicecours;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Listcours extends Form{

   // Form current;
//ArrayList<Cours> data = new ArrayList<>();

//public Listcours(Form previous) {
    //setTitle("Listes  Des COURS");
    //data = Servicecours.getInstance().getAllUsers();
    //Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    //for (int i=0;i<data.size();i++){
      //  Cours u = new Cours();
        // u.setIdc(data.get(i).getIdc());
        //u.setNom(data.get(i).getNom());
       // u.setType(data.get(i).getType());
       // u.setDescription(data.get(i).getDescription());
        //u.setPrix(data.get(i).getType());
        //u.setRate(data.get(i).getRate());
        
        
//        Button modif = new Button("Modifier");
//        Button supp = new Button("Supprimer");
        //CheckBox box = new CheckBox();
        
        
//        modif.addActionListener(e -> new ModifierAbonnementForm(current,ab).show());
//        supp.addActionListener(new ActionListener(){
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            ServiceAbonnement.getInstance().deleteAbonnement(ab);
//            Dialog.show("Success", "Memory Deleted Successfully.", "OK", "Cancel");
//        }
//        });
        
       
    
    
   
   // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
     //           , e-> previous.showBack()); // Revenir vers l'interface précédente
//}

    
//}}
 //setTitle("List tasks");
        
   //     SpanLabel sp = new SpanLabel();
     //   sp.setText(Servicecours.getInstance().getAllUsers().toString());
       // add(sp);
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
//}
Form current;
ArrayList<Cours> data = new ArrayList<>();

public Listcours(Form previous,Resources theme) {
    setTitle("Listes  Des cours");
    
    
    
    data = Servicecours.getInstance().getAllUsers();
   
        
       
    Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    
  

    for (int i=0;i<data.size();i++){
        Container x = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container xx = new Container(new BoxLayout(BoxLayout.Y_AXIS));
         Container xxx = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Cours u = new Cours();
        u.setIdc(data.get(i).getIdc());
        u.setNom(data.get(i).getNom());
        u.setType(data.get(i).getType());
        u.setDescription(data.get(i).getDescription());
        u.setPrix(data.get(i).getPrix());
       
        Label separation = new Label("----------------------------");
        Label email = new Label("Nom : " + data.get(i).getNom());
        Label firstname = new Label("type : "+ data.get(i).getType());
        Label lastname = new Label("description : "+ data.get(i).getDescription());
        Label nbrTel = new Label("prix: "+ data.get(i).getPrix());
        Label Supp=new Label("");
        Supp.setUIID("NewTopLine");
        Style Suppst=new Style(Supp.getUnselectedStyle());
        Suppst.setFgColor(0xf21f1f);
   FontImage suppi=FontImage.createMaterial(FontImage.MATERIAL_DELETE, Suppst);
   Supp.setIcon(suppi);
   Supp.setTextPosition(RIGHT);
   Supp.addPointerPressedListener(l-> {
           Dialog dig =new Dialog("supression");
           if (dig.show("supression","vous voulezsupprimer ce cours?","annuler","oui")){
           dig.dispose();
           }
           else
           {
               if(Servicecours.getInstance().deletecours(u)); 
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
      //   System.out.println("hello update cours");
 new Modifcour(current,u,theme).show();     
   }); 
   
            
        x.addAll(BoxLayout.encloseY(BoxLayout.encloseX(email),BoxLayout.encloseX(firstname),BoxLayout.encloseX(lastname),BoxLayout.encloseX(nbrTel,Mod,Supp)));
        //xx.addAll(supp,modif);
        y.addAll(x,xx,separation);
    }
    addAll(y);
//   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
 //               , e-> previous.showBack()); // Revenir vers l'interface précédente
}

   



}