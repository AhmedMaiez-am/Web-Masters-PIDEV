/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.capture.Capture;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author asus
 */
public class Uploadf {
    public Uploadf()
    {
   Form hi =new Form("hi world",BoxLayout.y());
   Button btnbrowser = new Button("add");
   Label lbim=new Label();
   hi.add(btnbrowser);
   hi.add(lbim);
   btnbrowser.addActionListener((e)-> {
       
   String path = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
   if (path !=null)
   {
       try {
           Image img =Image.createImage(path);
     lbim.setIcon(img);
     hi.revalidate();
       
       } catch (IOException ex) {
         ex.printStackTrace();
       }
   
   }
   
   });
   hi.show();
   
           }

    
}
