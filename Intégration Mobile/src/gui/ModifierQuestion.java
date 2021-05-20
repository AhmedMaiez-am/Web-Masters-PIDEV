/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Questions;
import services.ServiceQuestion;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
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
public class ModifierQuestion extends Form {

    public ModifierQuestion(Resources theme, Questions q, int id) {
        setTitle("modifier Question");
        setLayout(BoxLayout.y());

        getContentPane().setScrollVisible(false);
       Toolbar tb = getToolbar();
        //  setUIID("lolo");
        tb.addCommandToLeftBar("Back", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ProfileForm1(theme).show();
            }
        });

//        TextField Quiz = new TextField(String.valueOf(rec.getQuiz()), TextField.ANY);
        TextField question = new TextField(q.getQuestion(), TextField.ANY);
        TextField opt1 = new TextField(q.getOption1(), TextField.ANY);
        TextField opt2 = new TextField(q.getOption2(), TextField.ANY);
        TextField opt3 = new TextField(q.getOption3(), TextField.ANY);
        TextField opt4 = new TextField(q.getOption4(), TextField.ANY);
        TextField answer = new TextField(q.getAnswer(), TextField.ANY);

//        question.setSingleLineTextArea(true);

        Button save = new Button("modifier");
        save.addPointerPressedListener(l -> {

            q.setQuestion(question.getText());
            q.setOption1(opt1.getText());
            q.setOption2(opt2.getText());
            q.setOption3(opt3.getText());
            q.setOption4(opt4.getText());
            q.setAnswer(answer.getText());

            if (ServiceQuestion.getInstance().modifierQuestions(q, q.getQuestionid())) {
                System.out.println("hello");
                new ListQuestions(theme).show();

            } else {
                Dialog diag = new Dialog("modifier");
                diag.show("error", "", new Command("ok"));
            }
        });
        //appel function service
        Button btnannuler = new Button("annuler");
        btnannuler.addActionListener(l -> {
            new ListQuestions(theme).show();
        });
        Container content = BoxLayout.encloseY(save, btnannuler);

        addAll( question, opt1,opt2,opt3,opt4,answer);
       
        question.setUIID("text");
        opt1.setUIID("text");
        opt2.setUIID("text");
        opt3.setUIID("text");
        opt4.setUIID("text");
        answer.setUIID("text");
        save.setUIID("ModBtn");
        btnannuler.setUIID("suppbtn");
       
        add(content);
        show();
    }

}
