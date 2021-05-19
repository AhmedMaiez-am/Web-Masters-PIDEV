package com.mycompany.myapp;


import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.gui.Addcontes;
import com.mycompany.myapp.gui.Addcours;
import com.mycompany.myapp.gui.Homeform;
import com.mycompany.myapp.gui.Listcontes;
import com.mycompany.myapp.gui.Listcours;
import com.mycompany.myapp.gui.Searchcoursform;
import com.mycompany.myapp.gui.Serachcon;
import com.mycompany.myapp.gui.Uploadf;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class MyApplication {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });        
    }
    
    public void start() {
   //   new Homeform().show();
    // new Uploadf().show();
    Form current = null;
    Form hi = new Form("espace tuteur");
SpanLabel sp = new SpanLabel(" bienvenu monsieur/madame tuteur");        
hi.add(sp);
    ImageViewer img =new ImageViewer();
    
    img.setImage(theme.getImage("illustration-appel-video-tuteur-ligne_191217-124.jpg"));
    hi.add(img);    
        //Form page1=new Form("Page 1",new FlowLayout(Component.CENTER, Component.CENTER));
        Addcours addcours =new Addcours(current);
       // addcours.add(new Label("Page 1"));
        
         addcours.getToolbar().addCommandToOverflowMenu("return", null, ev->{
            hi.show();
        });
        
        //page1.getToolbar().addMaterialCommandToLeftBar("test", FontImage.MATERIAL_BACKSPACE, null);
        
       /// Form page2 =new Form("Page 2",new FlowLayout(Component.CENTER, Component.CENTER));
       Listcours listcours = new Listcours (current);
       listcours.add(new Label("listes des cours"));
       listcours.getToolbar().addCommandToOverflowMenu("re", null, ev->{
            hi.show();
        });
        
        
        //Form page3=new Form("Page 3",new FlowLayout(Component.CENTER, Component.CENTER));
       Searchcoursform sea =new Searchcoursform (current);
        sea.add(new Label(" recherche cours"));
        
        sea.getToolbar().addCommandToOverflowMenu("return", null, ev->{
            hi.show();
        });
       /// Form page2 =new Form("Page 2",new FlowLayout(Component.CENTER, Component.CENTER));
       Listcontes listcontes = new Listcontes (current);
       listcontes.add(new Label("listes des cours"));
       listcontes.getToolbar().addCommandToOverflowMenu("re", null, ev->{
            hi.show();
        });
         Addcontes addcontes =new Addcontes(current);
       // addcours.add(new Label("Page 1"));
        
         addcontes.getToolbar().addCommandToOverflowMenu("return", null, ev->{
            hi.show();
        });
        Serachcon src =new Serachcon (current);
        src.add(new Label(" recherche cours"));
        
        src.getToolbar().addCommandToOverflowMenu("return", null, ev->{
            hi.show();
        });
        
        hi.getToolbar().addCommandToSideMenu("ajouter cours",theme.getImage("round.png"), e->{
            addcours.show();
        });
        hi.getToolbar().addCommandToSideMenu("liste des cours", theme.getImage("round.png"), e->{
            listcours.show();
        });
        hi.getToolbar().addCommandToSideMenu("recherche cours",null, e->{
            sea.show();
        });
         hi.getToolbar().addCommandToSideMenu("liste des contes", theme.getImage("round.png"), e->{
            listcontes.show();
        });
        
          hi.getToolbar().addCommandToSideMenu("ajouter contes",theme.getImage("round.png"), e->{
            addcontes.show();
        });
         
          hi.getToolbar().addCommandToSideMenu("recherche cours",null, e->{
            src.show();
        });
          
         hi.show();
        
    
           
    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

}