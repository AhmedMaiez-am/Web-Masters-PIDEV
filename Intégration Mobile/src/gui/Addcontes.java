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
import com.codename1.ui.CN;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import entities.Contes;

import services.Servicecontes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;


/**
 *
 * @author asus
 */
public class Addcontes extends Form {
    
protected String saveFileToDevice(String hi, String ext) throws IOException, URISyntaxException {
        URI uri;
        try {
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            return hi;
        } catch (URISyntaxException ex) {
        }
        return "hh";
    }
    public Addcontes(Form previous) {

        setTitle("Add a new contes");
        setLayout(BoxLayout.y());

        TextField tfnom = new TextField("", "titre");
        TextField tftype = new TextField("", "auteur");
        TextField tfdescription = new TextField("", "contes");
        TextField tfrate = new TextField("", "rate");
        Button up = new Button(" choose  cours");
        Button btnValider = new Button("Add cours");
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
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length()==0)||(tftype.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Contes c = new Contes(tfnom.getText(), tftype.getText(), tfdescription.getText(),Integer.parseInt(tfrate.getText()));
                        if( Servicecontes.getInstance().addcontes(c))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        addAll(tfnom,tftype,tfdescription,tfrate,up,btnValider);
        

        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
          //       e -> previous.showBack()); // Revenir vers l'interface précédente
    
}
}
