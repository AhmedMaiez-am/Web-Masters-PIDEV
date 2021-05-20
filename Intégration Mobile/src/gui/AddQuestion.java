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
import java.util.ArrayList;

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
                new ProfileForm1(theme).show();
            }
        });

        setTitle("Ajouter un Question");
        setLayout(BoxLayout.y());

//        ComboBox<Quiz> Quiz = new ComboBox<Quiz>();
//        ServiceQuiz ser = new ServiceQuiz();
//        ArrayList<Quiz> listev = (ArrayList<Quiz>) ser.getListalls();
//        for (Quiz s : listev) {
//            Quiz.addItem(s);
//        }
        Quiz c = new Quiz();
        ComboBox Quiz = new ComboBox();

        Quiz.addItem("Math");
        Quiz.addItem("Francais");
        Quiz.addItem("English");
        Quiz.addItem("SGBD");
        Quiz.addItem("Java");

        if (c.getTitle() == "Math") {
            Quiz.setSelectCommandText("Math");

        } else if (c.getTitle() == "Francais") {
            Quiz.setSelectCommandText("Francais");

        } else if (c.getTitle() == "Java") {
            Quiz.setSelectCommandText("Java");

        } else if (c.getTitle() == "SGBD") {
            Quiz.setSelectCommandText("SGBD");

        } else {
            Quiz.setSelectCommandText("English");
        }

//        TextField Quiz = new TextField("", " Quiz ");
//        Quiz.setUIID("FloatingActionButton");
//         ComboBox G = new ComboBox("Quiz");
//         G.setUIID("FloatingActionButton");
        TextField A = new TextField("", " Question ");
        //A.setUIID("FloatingActionButton");
        TextField B = new TextField("", "1ére Choix");
       // B.setUIID("FloatingActionButton");
        TextField C = new TextField("", "2éme Choix");
        //C.setUIID("FloatingActionButton");
        TextField D = new TextField("", "3éme Choix");
       // D.setUIID("FloatingActionButton");
        TextField E = new TextField("", "4éme Choix");
       // E.setUIID("FloatingActionButton");

        TextField F = new TextField("", "La réponse");
        //F.setUIID("FloatingActionButton");

        Button btnValiderQuestion = new Button("Ajouter");
        btnValiderQuestion.setUIID("addbtn");

        btnValiderQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((A.getText().length() == 0 || B.getText().length() == 0 || C.getText().length() == 0 || D.getText().length() == 0 || E.getText().length() == 0 || F.getText().length() == 0)) {
                    Dialog.show("Alert", "il y a un chmap vide! ", new Command("OK"));
                } else if ((A.getText().length() < 3)) {
                    Dialog.show("Alert", "Minimum 3 caractere! ", new Command("OK"));
                } else {

                    Questions e = new Questions((Quiz.getSelectedIndex()),
                            A.getText(), B.getText(),
                            C.getText(), D.getText(),
                            E.getText(), F.getText());
                    if (ServiceQuestion.getInstance().addQuestion(e)) {
                        Dialog.show("Success", "Ajouté accepted", new Command("OK"));
                    } else {
                        Dialog.show("Success", "Ajouté accepted", new Command("OK"));
                    }
                    new ProfileForm(theme).show();

                }

            }

        });

        addAll(Quiz, A, B, C, D, E, F, btnValiderQuestion);

    }

}
