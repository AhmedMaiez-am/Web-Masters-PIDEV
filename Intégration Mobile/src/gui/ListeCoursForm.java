/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.CoursAhmed;
import entities.Inventairecours;
import entities.Pp;
import java.util.ArrayList;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.ServiceCoursAhmed;
import services.ServicePP;

/**
 *
 * @author maiez
 */
public class ListeCoursForm extends Form{
    public ListeCoursForm (Resources theme){
        super(BoxLayout.y());
        setTitle("Liste des cours");
        Toolbar tb = getToolbar();
        tb.setTitleCentered(true);
        
        tb.addCommandToLeftBar("Retour",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new homeAhmed(theme).show();
            }
        });
        ServiceCoursAhmed sc = new ServiceCoursAhmed();
        ArrayList<CoursAhmed> a = sc.afficherCours();
        
        Component[]listToAdd = new Component[a.size()];
        
        for(int i=0; i<sc.afficherCours().size();i++){
            final CoursAhmed c = a.get(i);
            
            MultiButton mb = new MultiButton();
            mb.setTextLine1("Nom : "+a.get(i).getNom());
            mb.setTextLine2("Type : "+a.get(i).getType());
            mb.setTextLine3("Description : "+a.get(i).getDescription());
            mb.setTextLine4("Prix : "+a.get(i).getPrix());
            final String nom = a.get(i).getNom();
            final String type = a.get(i).getType();
            final String desc = a.get(i).getDescription();
            final String prix = a.get(i).getPrix();
            Button btn = new Button("Ajouter");
            Button btn1 = new Button("Confirmer votre mail");
            Button btnPay = new Button ("Payer");
            TextField txtMail = new TextField("", "Entrez votre mail");
            TextField txtPass = new TextField("","Entrer le mot de passe de votre carte");
            btn1.setHidden(true);
            txtMail.setHidden(true);
            btnPay.setHidden(true);
            txtPass.setHidden(true);
            
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (prix == "Gratuit"){
                    CoursAhmed c = new CoursAhmed(nom, type,desc);
                    if(ServiceCoursAhmed.getInstance().ajouterInvCours(c)){
                        Dialog.show("Succés", "Le cours a été ajouté avec succés à votre inventaire", new Command("OK"));
                    new ListeCoursForm(theme).show();
                    }
                    else
                        Dialog.show("Succés", "Le cours a été ajouté avec succés à votre inventaire", new Command("OK"));
                    new ListeCoursForm(theme).show();
                    }else{
                        btn1.setHidden(false);
                        txtMail.setHidden(false);
                        ServicePP sp = new ServicePP();
                        ArrayList<Pp> ap = sp.afficherp();
                        for (int j=0; j<sp.afficherp().size();j++){
                            final Pp ppp = ap.get(j);
                            final String mail = ap.get(j).getEmailp();
                            final String pass = ap.get(j).getPasscarte();
                            int prix1 =Integer.parseInt(prix);
                            int montant1=Integer.parseInt(ap.get(j).getPortefeuille());
                            btn1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                            if(mail.equals(txtMail.getText())){
                                btn1.setHidden(true);
                                txtMail.setHidden(true);
                                btnPay.setHidden(false);
                                txtPass.setHidden(false);
                                }/*else{
                                Dialog.show("Echèc", "Veuillez vérifier votre mail!", new Command("OK"));
                            }*/
                            }});
                            btnPay.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                
                                if (pass.equals(txtPass.getText())){
                                    if (prix1 > montant1){
                                        Dialog.show("Echèc", "Votre solde du portefeuille n'est pas suffisant!", new Command("OK"));
                                    }else{
                                        CoursAhmed c = new CoursAhmed(nom, type,desc);
                    if(ServiceCoursAhmed.getInstance().ajouterInvCours(c)){
                        Dialog.show("Succés", "Le cours a été ajouté avec succés à votre inventaire", new Command("OK"));
                        
                        
                    new ListeCoursForm(theme).show();
                    }
                    else
                        Dialog.show("Succés", "Le cours a été ajouté avec succés à votre inventaire", new Command("OK"));
                    String Recepient = mail;
                    sendMail(Recepient);
                    new ListeCoursForm(theme).show();
                                    }
                                }/*else{
                                    Dialog.show("Echèc", "Veuillez vérifier le mot de passe de votre carte!", new Command("OK"));
                                }*/
                            }
                            });
                        }
                    }
                }
            });
            
            addAll(mb,btn,txtMail,btn1,txtPass,btnPay);
        }
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
            msg.setSubject("Achat du cours");
              String htmlCode = "<h1>Félicitations !</h1>"
                      + "<h3> L'achat du cours a été effectué avec succés </h3> "+
                      "<h3>Vous trouverez le cours dans votre inventaire</h3>";
            
              msg.setContent(htmlCode, "text/html");
            return msg;
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return null;
    } 
}
