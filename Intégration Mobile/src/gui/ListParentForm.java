/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import entities.Parents;
import services.ServiceParent1;
import java.util.ArrayList;
import javafx.scene.paint.Material;

/**
 *
 * @author Lenovo
 */
public class ListParentForm extends Form{
    
    
    
    public ListParentForm(Form previous){
        
        setTitle("Liste Parent");
        setLayout(BoxLayout.y());
         getContentPane().setScrollVisible(false);
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev->previous.showBack());

         ArrayList<Parents>list=ServiceParent1.getInstance().getAllParent();
        for(Parents p:list){
            Label idp=new Label("idp: "+String.valueOf(p.getIdP()));
            Label nomTxt=new Label("nomp: "+p.getNomP());
            Label emailTxt=new Label("emailp: "+p.getEmailP());
          
            addButton(p , previous);
              add(idp).add(nomTxt).add(emailTxt);

        }
        
        
        
    }
    
    
    private void addButton(Parents p,Form previous){
            
            Label lsms=new Label();
            Style smsStyle=new Style(lsms.getUnselectedStyle());             
            smsStyle.setFgColor(0xf7ad02);
            FontImage font=FontImage.createMaterial(FontImage.MATERIAL_SMS, smsStyle);
            lsms.setIcon(font);
            lsms.setTextPosition(RIGHT);
            Container cn=new Container(BoxLayout.y());
            Label telpTxt=new Label("telp: "+p.getTelP());
            lsms.addPointerPressedListener(e->
            {
                new SmsForm(previous).show();
            });
        
           cn.add(BoxLayout.encloseY(BoxLayout.encloseX(telpTxt,lsms)));
           add(cn); 
    }
        
        
}
