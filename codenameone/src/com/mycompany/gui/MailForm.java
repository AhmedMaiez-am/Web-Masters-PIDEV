/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.sun.mail.smtp.SMTPSSLTransport;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;



import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Lenovo
 */
public class MailForm extends Form {
  private TextField temail;  
    
    public MailForm(Form previous){
        setTitle("Envoi Mail");
             getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev->previous.showBack());
        temail=new TextField("", "saisir votre email",20, TextField.ANY);
        //temail.setSingleLineTextArea(false);
        Button btn=new Button("valider");
        btn.addActionListener(l->{
            
        String Recepient=temail.getText();
            sendMail(Recepient);
        Dialog.show("succes", "votre mail est envoyé avec succès", new Command("ok"));
           
        }
        );
        
        
        addAll(temail,btn);
    }
    
    
     
     /*
     public void sendMail(){
         try{
             Properties props=new Properties();
         
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
         Session s=Session.getInstance(props,null);
         MimeMessage msg=new MimeMessage(s);
         msg.setFrom(new InternetAddress("maitressecole13@gmail.com"));
          msg.setRecipients(Message.RecipientType.TO,temail.getText() );
         msg.setSubject("Recompense");
         msg.setSentDate(new Date(System.currentTimeMillis()));
     
         String txt="Felicitation";
         msg.setText(txt);
             SMTPTransport st=(SMTPSSLTransport)s.getTransport("smtps");
             st.connect("smtp.gmail",587,"maitressecole13@gmail.com","ghadaarbia123456");
             st.sendMessage(msg, msg.getAllRecipients());
          
             System.out.println("server response:"+st.getLastServerResponse());
             
         }catch(Exception ex){
             ex.printStackTrace();
         }
         
     }*/
     
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
            msg.setSubject("Recompense");
              String htmlCode = "<h1>Félicitations !</h1>"
                      + "<h3> Votre fils  a obtenus une recompense </h3> "+
                      "<h3>merci de nous contacter pour recuperer votre recompense</h3>";
            
              msg.setContent(htmlCode, "text/html");
            return msg;
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return null;
    } 
}
