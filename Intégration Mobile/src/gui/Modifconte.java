/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Contes;
import entities.Cours;
import services.Servicecontes;
import services.Servicecours;
import java.io.InputStream;

/**
 *
 * @author asus
 */
public class Modifconte extends Form{
     Form currnet;
    public Modifconte(Form previous,Contes c,Resources theme) {

        setTitle("update contes");
        setLayout(BoxLayout.y());

        TextField tfnom = new TextField(c.getTitre(),"titre");
        TextField tftype = new TextField(c.getAuteur(), "auteur");
        TextField tfdescription = new TextField(c.getContes(), "contes");
   
        //TextField tfrate = new TextField(String.valueOf(c.getRate()), "rate");
       Button up = new Button("browser");
        Button btnValider = new Button("update cours");
        
        up.addActionListener((ActionEvent e) -> {
          if (FileChooser.isAvailable()) {
    FileChooser.showOpenDialog(".pdf, text/plain", e2-> {
        String file = (String)e2.getSource();
        System.out.print(file);
        if (file == null) {
            add("No file was selected");
            revalidate();
        } else {
           String extension = null;
           if (file.lastIndexOf(".") > 0) {
               extension = file.substring(file.lastIndexOf(".")+1);
           }
           if ("txt".equals(extension)) {
               FileSystemStorage fs = FileSystemStorage.getInstance();
               try {
                   InputStream fis = fs.openInputStream(file);
                   addComponent(new SpanLabel(Util.readToString(fis)));
               } catch (Exception ex) {
                   Log.e(ex);
               }
           } else {
               add("Selected file "+file);
             String k=file.substring(6);
               tfdescription.setText(k);
               ;
           }
        }
       revalidate();
    });
}
               });
        
        btnValider.addPointerPressedListener(l-> {
          
            c.setTitre(tfnom.getText());
            //c.setType(tftype.getText());
           
           // c.setRate(tfrate.getText());
            
            c.setAuteur(tftype.getText());
            c.setContes(tfdescription.getText());
            //c.setPrix(tfprix.getText());
           
           if (Servicecontes.instance.updatecontes(c))
            {
              Dialog.show("Success","Connection accepted",new Command("OK"));
            }
                   //     else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
               
                
                   
                       
           
             });
      addAll(tfnom,tftype,tfdescription,up,btnValider);
        

//  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
  //       e -> previous.showBack()); // Revenir vers l'interface précédente
   //getToolbar().addCommandToOverflowMenu("return", null, ev->{
    //        current.show();
        //});
        
    getToolbar().addCommandToOverflowMenu("retourner", null, ev->{
            new Aziz(theme).show();
            });
    }

}
