/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import static com.codename1.ui.CN.addNetworkErrorListener;
import static com.codename1.ui.CN.updateNetworkThreadCount;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author maiez
 */
public class Aziz extends Form{
    Form current ;
    
        
        


    public Aziz(Resources theme) {
        setTitle("Espace Tuteur");
        SpanLabel sp = new SpanLabel(" bienvenue monsieur/madame tuteur");        

    ImageViewer img =new ImageViewer();
    
    img.setImage(theme.getImage("illustration-appel-video-tuteur-ligne_191217-124.jpg"));
     
        //Form page1=new Form("Page 1",new FlowLayout(Component.CENTER, Component.CENTER));
        Addcours addcours =new Addcours(current);
       // addcours.add(new Label("Page 1"));
        
         addcours.getToolbar().addCommandToOverflowMenu("return", null, ev->{
            this.show();
        });
        
        //page1.getToolbar().addMaterialCommandToLeftBar("test", FontImage.MATERIAL_BACKSPACE, null);
        
       /// Form page2 =new Form("Page 2",new FlowLayout(Component.CENTER, Component.CENTER));
       Listcours listcours = new Listcours (current,theme);
       listcours.add(new Label("listes des cours"));
       listcours.getToolbar().addCommandToOverflowMenu("return", null, ev->{
            this.show();
        });
        
        
        //Form page3=new Form("Page 3",new FlowLayout(Component.CENTER, Component.CENTER));
       Searchcoursform sea =new Searchcoursform (current);
        sea.add(new Label(" recherche cours"));
        
        sea.getToolbar().addCommandToOverflowMenu("return", null, ev->{
            this.show();
        });
       /// Form page2 =new Form("Page 2",new FlowLayout(Component.CENTER, Component.CENTER));
       Listcontes listcontes = new Listcontes (current,theme);
       listcontes.add(new Label("listes des contes"));
       listcontes.getToolbar().addCommandToOverflowMenu("return", null, ev->{
           this.show();
        });
         Addcontes addcontes =new Addcontes(current);
       // addcours.add(new Label("Page 1"));
        
         addcontes.getToolbar().addCommandToOverflowMenu("return", null, ev->{
            this.show();
        });
        Serachcon src =new Serachcon (current);
        src.add(new Label(" recherche contes"));
        
        src.getToolbar().addCommandToOverflowMenu("return", null, ev->{
            this.show();
        });
        
        getToolbar().addCommandToSideMenu("ajouter cours",theme.getImage("round.png"), e->{
            addcours.show();
        });
        getToolbar().addCommandToSideMenu("liste des cours", theme.getImage("round.png"), e->{
            listcours.show();
        });
        getToolbar().addCommandToSideMenu("recherche cours",null, e->{
            sea.show();
        });
         getToolbar().addCommandToSideMenu("liste des contes", theme.getImage("round.png"), e->{
            listcontes.show();
        });
        
         getToolbar().addCommandToSideMenu("ajouter contes",theme.getImage("round.png"), e->{
            addcontes.show();
        });
         
          getToolbar().addCommandToSideMenu("recherche contes",null, e->{
            src.show();
        });
          addAll(sp,img );
     

    }
    
    
    

}
