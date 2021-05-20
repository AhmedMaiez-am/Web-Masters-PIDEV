/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Toolbar;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Reclamation;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Listereclamation extends BaseForm{
    Form current;
    public Listereclamation (Resources res){
    
        super("Liste Reclamation",BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        //setTitle("Liste Reclamation");
        getContentPane().setScrollVisible(false);
                tb.addSearchCommand(e -> {});
        
                Tabs swipe = new Tabs();
                Label s1 = new Label();
                Label s2 = new Label();
                addTab(swipe,s1,res.getImage("mra.png"),"","",res);

                swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
            
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("MesReclamations", barGroup);
        all.setUIID("SelectBar");
        
        RadioButton featured = RadioButton.createToggle("Autres", barGroup);
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Reclamer", barGroup);
        popular.setUIID("SelectBar");
      
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, all, featured, popular),
                FlowLayout.encloseBottom(arrow)
        ));

        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
        //bindButtonSelection(myFavorite, arrow);

        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
       
        });
        ArrayList<Reclamation> List = services.ServiceReclamation.getInstance().AfficherReclamations();
        for (Reclamation rec : List)
        {
            String urlImage="timeline-background.jpg";
             Image placeholder = Image.createImage(450,450);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
         URLImage urlim = URLImage.createToStorage(encImage,urlImage,urlImage,URLImage.RESIZE_SCALE);
        
            addButton(urlim,rec,res);
            Button modifier = new Button("Modifier");
            modifier.addActionListener(mod->{
                new ModifierRec(res,rec).show();
            });
            Button Supp = new Button("Supprimer");
            Supp.addActionListener(sup->{
                services.ServiceReclamation s=new services.ServiceReclamation();
                s.deleteReclamation(rec.getIdr());
                new Listereclamation(res).show();
            });
            super.add(modifier);
            super.add(Supp);
            ScaleImageLabel image = new ScaleImageLabel(urlim);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
            
        }
}
    private void addTab(Tabs swipe,Label spacer, Image img, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size)
        {
            img=img.scaledHeight(size);
        }
          if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                                )
                        )
                );

        swipe.addTab("timeline-background.jpg", page1);
    }
      private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }
   private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }

    private void addButton(Image img,Reclamation rec,Resources res) {
  
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        
       Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        Label Nomtxt = new Label ("Nom : "+rec.getNom(),"NewsTopLine2");
        Label Mailtxt = new Label ("Mail : "+rec.getEmail(),"NewsTopLine2");
        Label Rectxt = new Label ("Reclamation : "+rec.getReclamation(),"NewsTopLine2");
        Label etattxt = new Label ("Etat : "+rec.getEtat(),"NewsTopLine2");
        Label typetxt = new Label ("Type : "+rec.getType(),"NewsTopLine2");
       
         
        
       // etattxt.setText("Non Traité");
        
     /*   Label lSupprimer = new Label ("Sup");
       
        lSupprimer.setUIID("NewsTopLine");
        Style supprimerStyle = new Style (lSupprimer.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f1f);
        
        FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE,supprimerStyle);
        lSupprimer.setIcon(supprimerImage);
        lSupprimer.setTextPosition(RIGHT);
        createLineSeparator();
        
        lSupprimer.addPointerPressedListener(l->{
        
        Dialog dig = new Dialog("Suppression");
        if(dig.show("Suppression","Vous voulez annuler la reclamation ?","Annuler","Oui")){
        dig.dispose();
        }
        else {
            dig.dispose();
            if(Services.ServiceReclamation.getInstance().deleteReclamation(rec.getIdr()))
            {
                new Listereclamation(res).show();
            }
        }
        });*/
                    //super.add(Supp);

        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseX(Nomtxt),BoxLayout.encloseX(Mailtxt),BoxLayout.encloseX(Rectxt),BoxLayout.encloseX(etattxt),BoxLayout.encloseX(typetxt)));
        add(cnt);
                }
}
