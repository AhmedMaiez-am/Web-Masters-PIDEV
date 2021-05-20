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
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author IHEB
 */
public class ListQuestions extends Form {

    public ListQuestions(Resources theme) {
        super(BoxLayout.y());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        setTitle("Liste des Questions");
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        //  setUIID("lolo");
        tb.addCommandToLeftBar("Back", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ProfileForm(theme).show();
            }
        });
        
        tb.addCommandToRightBar("Rechercher", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new SearchQuestion(theme).show();
            }
        });

         Services.ServiceQuestion spp = new ServiceQuestion();
        ArrayList<Questions> lRec = ServiceQuestion.getInstance().getAllQuestions();

        for (Questions rec : lRec) {

            addButton(rec, theme);
        }

    }



    private void addButton(Questions r, Resources theme) {

        //button supprimer
        Label lsupprimer = new Label("");
        lsupprimer.setUIID("suppbtn");
        Style supprimerStyle = new Style(lsupprimer.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f1f);
        FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        lsupprimer.setIcon(supprimerImage);
        lsupprimer.setTextPosition(RIGHT);
        
        Label lmodifier = new Label("");
        lmodifier.setUIID("suppbtn");
        Style modifierStyle = new Style(lmodifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf21f1f);
        FontImage modifierImage = FontImage.createMaterial(FontImage.MATERIAL_EDIT, modifierStyle);
        lmodifier.setIcon(modifierImage);
        lmodifier.setTextPosition(LEFT);
        
        
        Label Question = new Label("Question= " + r.getQuestion());
//             Label nbrTxt=new Label("nbr point= "+String.valueOf(r.getNbrPoint()));
        Label opt1 = new Label("1ére choix= " + r.getOption1());
        Label opt2 = new Label("2éme choix= " + r.getOption2());
        Label opt3 = new Label("3éme choix= " + r.getOption3());
        Label opt4 = new Label("4éme choix= " + r.getOption4());
        Label answer = new Label("Réponse= " + r.getAnswer());
         Question.setUIID("text");
         answer.setUIID("textV");
        
        Container cn = new Container(BoxLayout.y());
        //click delete button
        lsupprimer.addPointerPressedListener(l -> {
            Dialog dig = new Dialog("suppression");
            dig.show("Suppression", "vous voulez supprimer", "oui", "annuler");
            if (ServiceQuestion.getInstance().supprimerQuestion(r.getQuestionid())) {

                new ListQuestions(theme).show();
            
            
        }
          
          
            
            

        });
        
        lmodifier.addPointerPressedListener(
                  e->{
                      System.out.println("GUI.ListQuestions.addButton()");
          new ModifierQuestion(theme, r,r.getQuestionid()).show();
                  } );

        add(Question).add(opt1).add(opt2).add(opt3).add(opt4).add(answer);
        cn.add(BoxLayout.encloseY(BoxLayout.encloseX( lsupprimer,lmodifier)));
        add(cn);
      

    }

}
