/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Quiz;
import Services.ServiceQuiz;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import java.util.ArrayList;

/**
 *
 * @author IHEB
 */
public class ListQuiz extends Form {

    public ListQuiz(Resources theme) {
        super(BoxLayout.y());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        setTitle("Liste des Quizs");
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        //  setUIID("lolo");
        tb.addCommandToLeftBar("Back", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new GUI.ProfileForm(theme).show();
            }
        });
        
        tb.addCommandToRightBar("Rechercher", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Search(theme).show();
            }
        });
        

        Services.ServiceQuiz spp = new ServiceQuiz();
        ArrayList<Quiz> pl = spp.getListalls();
        //  setupSideMenu();

        Component[] listingsToAdd = new Component[pl.size()];
        for (int iter = 0; iter < spp.getListalls().size(); iter++) {
            final Quiz p = pl.get(iter);
            int m = pl.get(iter).getQuizId();
            String s = String.valueOf(m);
            int a = pl.get(iter).getIsamericain();

            MultiButton mb = new MultiButton();
            mb.setTextLine1("Nom de Quiz:" + pl.get(iter).getTitle());
            if (a == 1) {
                mb.setTextLine2("Type de Quiz: Americain");
            } else {
                mb.setTextLine2("Type de Quiz: Normal");
            }

            mb.setUIID("text");

           

            Button btnsupp = new Button("Supprimer");
            btnsupp.setUIID(s);

           

            btnsupp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    if (ServiceQuiz.getInstance().supprimerQuiz(btnsupp.getUIID())) {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    } else {
                        Dialog.show("Success", "deleted accepted", new Command("OK"));
                    }
                    new ListQuiz(theme).show();
                }

            });

            addAll( mb, btnsupp);
        }
    }

}
