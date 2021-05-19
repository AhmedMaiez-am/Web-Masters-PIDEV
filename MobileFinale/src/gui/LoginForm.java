/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import services.ServiceLogin;
/**
 *
 * @author HP
 */
public class LoginForm  extends Form {
     public static String idparent ;
     Resources res ;
    public LoginForm(Resources res) {
      //  super(new BorderLayout());
       setTitle("WELCOME To KIDZY");
          Label lab = new Label("\n    Bienvenue KIDZY  ");
          setLayout(BoxLayout.y());
    ImageViewer img=new ImageViewer();
    img.setImage(res.getImage("parent.jpg"));
          //ImageViewer imgm=new ImageViewer();
        
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Se Connecter");
        Button sign = new Button("Créer un Compte");
       // Button signUp = new Button("Sign Up");
     
        signIn.requestFocus();
        signIn.addActionListener(e -> ServiceLogin.getInstance().signin(username, password));
        
        sign.addActionListener(m -> new AjouterParents(res).show());
     
        addAll(img,username,password,signIn,sign);
    }
}
