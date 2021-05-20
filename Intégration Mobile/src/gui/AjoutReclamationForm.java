/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import static services.SendMail.resultOK;
import services.ServiceReclamation;
import static services.ServiceReclamation.resultOK;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Toolbar;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import utils.Statics;
import entities.Reclamation;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author HP
 */
public class AjoutReclamationForm extends BaseForm {
    Form current;
         private ConnectionRequest req;

    public AjoutReclamationForm(Resources res)
    {
        super("Ajout Reclamation",BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        //setTitle("Ajout Reclamation");
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

        add(LayeredLayout.encloseIn(
                sl
        ));
                tb.addSearchCommand(e -> {});
        
                Tabs swipe = new Tabs();
                Label s1 = new Label();
                Label s2 = new Label();
                addTab(swipe,s1,res.getImage("profile-background.jpg"),"","",res);

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
        RadioButton all = RadioButton.createToggle("Mes Reclamations", barGroup);
        all.setUIID("SelectBar");
        
        RadioButton featured = RadioButton.createToggle("Autres", barGroup);
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Reclamer", barGroup);
        popular.setUIID("SelectBar");
      
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, popular, all, featured),
                FlowLayout.encloseBottom(arrow)
        ));

        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        ServiceReclamation sr =new ServiceReclamation();
        bindButtonSelection(all,arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
        //bindButtonSelection(myFavorite, arrow);

        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        TextField nom = new TextField("","Entrer Nom");
        nom.setUIID("TextFieldBlack");
        addStringValue("Nom",nom);
        
        TextField prenom = new TextField("","Entrer Prenom");
        prenom.setUIID("TextFieldBlack");
        addStringValue("Prenom",prenom);
        
        TextField email = new TextField("","Entrer Email");
        email.setUIID("TextFieldBlack");
        addStringValue("Email",email);
         TextField etat = new TextField("Non Traitee");
                 etat.setUIID("TextFieldBlack");

        addStringValue("Etat",etat);
        
        TextField reclamation = new TextField("","Entrer Reclamation");
        reclamation.setUIID("TextFieldBlack");
        addStringValue("Reclamation",reclamation);
        
         
        
        
        Picker type = new Picker();
        type.setStrings("Cours","Enseignant","Enfant","Contes");
        addStringValue("Type",type);
        
        Button bnAjouter = new Button("Ajouter");
bnAjouter.setUIID("Button");
      super.add(bnAjouter);    
      bnAjouter.addActionListener((e)-> {
            try {
                if(nom.getText().equals ("") ||prenom.getText().equals ("")|| email.getText().equals ("") || reclamation.getText().equals ("")  || type.getText().equals ("") )
                {
                    Dialog.show("Veuilliez verifier les données","","Annulez","ok");
                }
                else {
                    InfiniteProgress ip = new InfiniteProgress();;
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    Reclamation r = new Reclamation(String.valueOf(nom.getText()).toString(),String.valueOf(prenom.getText()).toString(),String.valueOf(email.getText()).toString(),String.valueOf(reclamation.getText()).toString());
                    System.out.println("data reclamation =="+r);
                  //  etat.setText("");
                    // sendEmail(r.getEmail());
                    r.setEtat(String.valueOf(etat.getText()));
            if (type.getSelectedString().equals("Cours")){
                r.setType("Cours");
                
            }
            if (type.getSelectedString().equals("Enseignant")){
                r.setType("Enseignant");
            }
            if (type.getSelectedString().equals("Enfant")){
                r.setType("Enfant");
            }
            if (type.getSelectedString().equals("Contes")){
                r.setType("Contes");
            }
                   type.setText(type.getSelectedString()); 
                    services.ServiceReclamation.getInstance().ajouterReclamation(r);
                    iDialog.dispose();
                    
                   Dialog.show("Votre Reclamation est Ajouté avec Succés ","","Annuler","OK");
                   String Recepient = email.getText();
                   sendMail(Recepient);
                   new Listereclamation(res).show();
                   refreshTheme();
                }
                    }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
          });
    }
    public void sendMail(String Recepient){
        System.out.println("Preparing to send email");
        
        //java.util.Properties properties=new java.util.Properties();
      // Properties properties=new Properties();
       java.util.Properties properties=new java.util.Properties();
        //Enable authentication
        properties.put("mail.smtp.auth", "true");
           //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable",true);
         //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
         //Set smtp port
        properties.put("mail.smtp.port", "587");

    //Your gmail address

        
        String myAccountEmail = "maitressecole13@gmail.com";
        //Your gmail password
        String  password="ghadaarbia123456";    
        
        //Create a session with account credentials

        Session  session = Session.getInstance(properties, new javax.mail.Authenticator() {
            
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail, password);
            }
             
        });
        Message msg=prepareMessage(session,myAccountEmail,Recepient);
        try {
            Transport.send(msg);
        } catch (MessagingException ex) {
              ex.printStackTrace();

        }
        
        System.out.println("Message sent successfully");
        
        
    }
    
    private Message prepareMessage(Session session,String myAccountEmail,String Recepient) {
        Message msg=new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(myAccountEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(Recepient));
            msg.setSubject("Reclamation");
              String htmlCode = "<h1>Félicitations !</h1>"
                      + "<h3> Bonjour, vous avez ajouté une réclamation, Elle sera pris en considération de les plus bref délais </h3> "+
                      "<h3>Cordialement..</h3>";
            
              msg.setContent(htmlCode, "text/html");
            return msg;
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return null;
    } 


       private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
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

        swipe.addTab("profile-background.jpg", page1);
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
 
}
