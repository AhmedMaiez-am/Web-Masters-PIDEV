/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Questions;
import Entity.Quiz;
import Services.ServiceQuestion;
import Services.ServiceQuiz;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author IHEB
 */
public class AddQuestion extends Form {

    public AddQuestion(Resources theme) {
        
         Toolbar tb = getToolbar();
            //  setUIID("lolo");
        tb.addCommandToLeftBar("Back", null, new ActionListener() {
          @Override
                    public void actionPerformed(ActionEvent evt) {
      new ProfileForm(theme).show();        
}    } );
        
        setTitle("Ajouter un Question");
        setLayout(BoxLayout.y());
        
         ComboBox G = new ComboBox("Quiz");
         G.setUIID("FloatingActionButton");
        TextField A = new TextField("", " Question ");
        A.setUIID("FloatingActionButton");
        TextField B = new TextField("", "1ére Choix");
        B.setUIID("FloatingActionButton");
        TextField C = new TextField("", "2éme Choix");
        C.setUIID("FloatingActionButton");
        TextField D = new TextField("", "3éme Choix");
        D.setUIID("FloatingActionButton");
        TextField E = new TextField("", "4éme Choix");
        E.setUIID("FloatingActionButton");
        
        TextField F = new TextField( "","La réponse");
        F.setUIID("FloatingActionButton");

        Button btnValiderQuestion = new Button("Ajouter");
                btnValiderQuestion.setUIID("addbtn");

        
        btnValiderQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((A.getText().length()==0 || B.getText().length()==0 || C.getText().length()==0 || D.getText().length()==0 ||  E.getText().length()==0 ||  F.getText().length()==0))
                    Dialog.show("Alert", "il y a un chmap vide! ", new Command("OK"));
                else if 
                         ((A.getText().length()<3))
                    Dialog.show("Alert", "Minimum 3 caractere! ", new Command("OK"));
                else 
                {
                    try {
                        Questions q = new Questions(A.getText(),B.getText(),C.getText(),D.getText(),E.getText(),F.getText());
                        if( ServiceQuestion.getInstance().addQuestion(q))
                            Dialog.show("Success","add accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                    
                }
                
                
            
        });
        
        addAll(G,A,B,C,D,E,F,btnValiderQuestion);
        
                
    }
    

    

}
