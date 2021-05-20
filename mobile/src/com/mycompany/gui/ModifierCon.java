/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Conseil;
import entities.Reclamation;

/**
 *
 * @author HP
 */
public class ModifierCon extends BaseForm{
     public ModifierCon(Resources res , Conseil c) {
          super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier le conseil");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

      

        TextField nom = new TextField("","Entrer Nom");
        nom.setUIID("TextFieldBlack");
        addStringValue("Nom",nom);
        
     
        TextField conseil = new TextField("","Entrer Conseil");
        conseil.setUIID("TextFieldBlack");
        addStringValue("Conseil",conseil);
        
        Button modifier = new Button("Modifier");
        super.add(modifier);
        Services.ServiceModifiercon mod = new Services.ServiceModifiercon();
        nom.setText(c.getNomc());
        conseil.setText(c.getConseil());
      

        modifier.addActionListener(e -> {
         c.setNomc(nom.getText());
c.setConseil(conseil.getText());         
mod.ModifierCon(c);
            new Listeconseil(res).show();

        }
        );

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    
}
