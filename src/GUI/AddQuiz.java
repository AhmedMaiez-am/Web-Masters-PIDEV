/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Quiz;
import Services.ServiceQuiz;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author IHEB
 */
public class AddQuiz extends Form {

    public AddQuiz(Resources theme) {
       super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
              Toolbar tb = getToolbar();
            //  setUIID("lolo");
        tb.addCommandToLeftBar("Back", null, new ActionListener() {
          @Override
                    public void actionPerformed(ActionEvent evt) {
      new ProfileForm(theme).show();        
}    } );
        
        

        setTitle("Ajouter un Quiz");
        setLayout(BoxLayout.y());
        
        TextField A = new TextField("", "Nom De Quiz");
        A.setUIID("FloatingActionButton");
        CheckBox B = new CheckBox( "Americain");
        B.setUIID("FloatingActionButton");

        Button btnValider = new Button("Ajouter");
        btnValider.setUIID("addbtn");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((A.getText().length()==0))
                    Dialog.show("Alert", "il y a un chmap vide! ", new Command("OK"));
                else if 
                         ((A.getText().length()<3))
                    Dialog.show("Alert", "Minimum 3 caractere! ", new Command("OK"));
                else 
                {
                    try {
                        Quiz q = new Quiz(A.getText(),Integer.parseInt(B.getText()));
                        if( ServiceQuiz.getInstance().addQuiz(q))
                            Dialog.show("Success","add accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                    
                }
                
                
            
        });
        
        addAll(A,B,btnValider);
     
        
                
    }
    

    

}
