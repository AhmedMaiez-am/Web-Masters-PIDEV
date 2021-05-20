/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author IHEB
 */
public class HomeQuiz extends Form {
    
    
    
    public HomeQuiz(Resources res ){
    super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
     setTitle("Bienvenue");
    Toolbar tb = getToolbar();
       tb.addCommandToSideMenu("Ajouter des quizs", null, new ActionListener() {
          @Override
                    public void actionPerformed(ActionEvent evt) {
      new AddQuiz(res).show();        
}    } );
          tb.addCommandToSideMenu("Liste des quizs", null, new ActionListener() {
          @Override
                    public void actionPerformed(ActionEvent evt) {
      new ListQuiz(res).show();        
}    } ); 
          
          tb.addCommandToSideMenu("ajouter des questions", null, new ActionListener() {
          @Override
                    public void actionPerformed(ActionEvent evt) {
      new AddQuestion(res).show();        
}    } ); 
          
          tb.addCommandToSideMenu("Liste des questions", null, new ActionListener() {
          @Override
                    public void actionPerformed(ActionEvent evt) {
      new ListQuestions(res).show();        
}    } ); 
}
    
    
     
}