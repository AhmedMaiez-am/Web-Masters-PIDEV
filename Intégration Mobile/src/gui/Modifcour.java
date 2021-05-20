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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Cours;
import services.Servicecours;
import java.io.InputStream;


/**
 *
 * @author asus
 */
public class Modifcour extends Form {

   Form currnet;

    public Modifcour(Form previous,Cours c,Resources theme) {

        setTitle("update cours");
        setLayout(BoxLayout.y());

        TextField tfnom = new TextField(c.getNom(),"nom");
        TextField tftype = new TextField(c.getType(), "type");
        TextField tfdescription = new TextField(c.getDescription(), "description");
        TextField tfcours = new TextField(c.getCours(), "cours");
        TextField tfprix = new TextField(c.getPrix(), "prix");
        //TextField tfrate = new TextField(String.valueOf(c.getRate()), "rate");
 ComboBox type=new ComboBox();
type.addItem("math");
type.addItem("francai");
type.addItem("anglais");
if (c.getType()=="math")
    type.setSelectCommandText("math");
else
    if (c.getType()=="francai")
    type.setSelectCommandText("francai");
else
  
    type.setSelectCommandText("anglais");

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
               tfcours.setText(k);
               ;
           }
        }
       revalidate();
    });
}
               });
        
        btnValider.addPointerPressedListener(l-> {
          
            c.setNom(tfnom.getText());
            //c.setType(tftype.getText());
           
           // c.setRate(tfrate.getText());
           if (type.getSelectedItem().equals("math"))
           {
               c.setType("math");
           
           }else 
              if (type.getSelectedItem().equals("francais"))
              {
                  c.setType("francai");
              }
              else 
                  
               c.setType("anglais");
            
            c.setDescription(tfdescription.getText());
            c.setCours(tfcours.getText());
            c.setPrix(tfprix.getText());
           
           if (Servicecours.instance.updatecours(c))
            {
              Dialog.show("Success","Connection accepted",new Command("OK"));
            }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
               
                
                   
                       
           
             });
       addAll(tfnom,type,tfdescription,tfcours,up,tfprix,btnValider);
        

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
