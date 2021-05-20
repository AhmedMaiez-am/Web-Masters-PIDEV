/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import services.ServiceLogin;

/**
 *
 * @author HP
 */
public class LoginTuteur extends Form {
     public LoginTuteur (Resources theme) {
        setTitle("WELCOME To KIDZY");
          Label lab = new Label("\n    Bienvenue KIDZY  ");
            Label lab1 = new Label("\n    Tuteur  ");
          setLayout(BoxLayout.y());
    ImageViewer img=new ImageViewer();
    img.setImage(theme.getImage("tuteur.jpg"));
          ImageViewer imgm=new ImageViewer();
    imgm.setImage(theme.getImage("logo.png"));
        TextField tfLogin = new TextField("", "Username", 20, TextField.ANY);
        TextField tfPassword = new TextField("", "Password", 20, TextField.PASSWORD);
        tfLogin.setSingleLineTextArea(false);
        tfPassword.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
       // Button signUp = new Button("Sign Up");
     
        signIn.requestFocus();
        signIn.addActionListener(ev -> ServiceLogin.getInstance().signinTuteur(tfLogin, tfPassword)
                
        );
        addAll(lab,lab1,img,tfLogin ,tfPassword,signIn,imgm);
       
}

    
}
