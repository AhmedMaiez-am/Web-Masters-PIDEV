/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Questions;
import entities.Quiz;
import services.ServiceQuestion;
import services.ServiceQuiz;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
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
public class SearchQuestion extends Form {

    String t;
    SpanLabel sp = new SpanLabel();
    SpanLabel titre = new SpanLabel();
    SpanLabel question = new SpanLabel();
    SpanLabel opt1 = new SpanLabel();
    SpanLabel opt2 = new SpanLabel();
    SpanLabel opt3 = new SpanLabel();
    SpanLabel opt4 = new SpanLabel();
    SpanLabel answer = new SpanLabel();

    SpanLabel separation = new SpanLabel("----------------------------");

    TextField tfName = new TextField("", "nom du Question");

    Button btnS = new Button("Rechercher Quiz");

    public SearchQuestion(Resources theme) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        //  setUIID("lolo");
        tb.addCommandToLeftBar("Back", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListQuestions(theme).show();
            }
        });

        //serviceUser s =new serviceUser();
        setTitle("Listes  Des Questions");

        btnS.addActionListener((ActionListener) (ActionEvent evt) -> {
            if (tfName.getText().length() == 0) {
                Dialog.show("Alert", "Le Champ est vide", new Command("OK"));
            } else {
                try {

                    ArrayList<Questions> c;
                    c = ServiceQuestion.getInstance().getAllQuestions();
                    t = tfName.getText();
                    Questions u1 = new Questions();
                    u1 = extract_user(c);
                    System.out.println(u1);
                    sp.setText(u1.toString());
                    //          l.setText(u1.toString());

                    titre.setText("Nom de Question :" + u1.getQuestion());
                    titre.setUIID("text");

                    opt1.setText("1ére  choix :" + u1.getOption1());
                    titre.setUIID("text");
                    opt2.setText("2éme  choix :" + u1.getOption2());
                    titre.setUIID("text");
                    opt3.setText("3éme  choix :" + u1.getOption3());
                    titre.setUIID("text");
                    opt4.setText("4éme  choix :" + u1.getOption4());
                    titre.setUIID("text");
                    answer.setText("Réponse :" + u1.getAnswer());
                    titre.setUIID("text");

                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                }

            }
        }
        );
        Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        y.add(tfName);
        y.add(btnS);
        // y.add(sp);
        y.add(titre);
        y.add(opt1);
        y.add(opt2);
        y.add(opt3);
        y.add(opt4);
        y.add(answer);

        btnS.setUIID("nono");

        y.add(separation);
        addAll(y);
        //y.add(nom);
        // 

    }

    public Questions extract_user(ArrayList<Questions> c) {
        Questions u = new Questions();
        for (Questions c1 : c) {
            if (c1.getQuestion().equals(t)) {
                u = c1;

            }
        }
        return u;
    }

}
