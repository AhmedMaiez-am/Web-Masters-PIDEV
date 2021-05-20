/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import entities.Enfant;
import services.ServiceEnfant1;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class ListEnfantForm extends Form{
    
    
    public ListEnfantForm(Form previous){
        
        setTitle("Liste Enfant");
        setLayout(BoxLayout.y());
         getContentPane().setScrollVisible(false);

        ArrayList<Enfant>list=ServiceEnfant1.getInstance().getAllEnfant();
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev->previous.showBack());
      for(Enfant e:list){
          Label nomenfTxt=new Label("nom: "+e.getNomenfant());
          Label prenomTxt=new Label("prenom: "+e.getPrenomenfant());
          Label nbrTxt=new Label("nbr: "+e.getNbr_point());
       Label idpTxt=new Label("idp: "+e.getIdparent());

          
          add(nomenfTxt).add(prenomTxt).add(nbrTxt).add(idpTxt );
      }
        
        
        
    }
}
