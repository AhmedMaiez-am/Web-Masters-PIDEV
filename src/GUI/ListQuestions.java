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
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
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

        ServiceQuestion spp = new ServiceQuestion();
        ArrayList<Questions> pl = spp.getListalls();
        //  setupSideMenu();

        Component[] listingsToAdd = new Component[pl.size()];
        for (int iter = 0; iter < spp.getListalls().size(); iter++) {
            final Questions p = pl.get(iter);

            MultiButton mb = new MultiButton();
            mb.setTextLine1("Quiz : " + pl.get(iter).getQuestion());
            mb.setUIID("text");
//            mb.setTextLine2("Type : " + pl.get(iter).getIsamericain());
//            mb.setUIID("text");
            add(mb);
            Button btnsup = new Button("supprimer");
            Button btnMod = new Button("Modifier");

            btnsup.addActionListener((e) -> {
                ServiceQuestion ser = new ServiceQuestion();
                ser.supprimerQuestion(p);
                Dialog.show("avec succès", "Question supprimer", "ok", null);
                new ListQuestions(theme).show();
            });
            addAll(btnsup, btnMod);
            btnsup.setUIID("suppbtn");
            btnMod.setUIID("ModBtn");
        }
    }
}

//Button btnValider = new Button("Supprimer");
//
//            btnValider.addActionListener((e) -> {
//                ServiceQuiz ser = new ServiceQuiz();
//                ser.supprimer(p);
//                Dialog.show("avec succès", "Evenement supprimer", "ok", null);
//                new ListQuiz(theme).show();
//
//                add(mb, btnValider);
