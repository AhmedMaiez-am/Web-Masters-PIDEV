/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Quiz;
import Services.ServiceQuiz;
import static com.codename1.io.Log.p;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.twilio.*;
import com.twilio.type.PhoneNumber;

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
            }
        });

        setTitle("Ajouter un Quiz");
        setLayout(BoxLayout.y());

        TextField A = new TextField("", "Nom De Quiz", 20, TextField.BASELINE);
        A.setUIID("text");
//        TextField B = new TextField("", "Isamerican  1 OR 0", 2, TextField.EMAILADDR);
//        B.setUIID("FloatingActionButton");

        ComboBox B = new ComboBox("Quiz Normal", "Quiz Americain");
        B.setUIID("MenuButton");

        Button btnValider = new Button("Ajouter");
        btnValider.setUIID("addbtn");

        addAll(A, B);

        LocalNotification ln = new LocalNotification();
        ln.setId("demo-notification");
        ln.setAlertImage("/notif.png");
        ln.setAlertTitle("Welcome");
        ln.setAlertBody("Votre Quiz est ");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((A.getText().length() == 0)) {
                    Dialog.show("Alert", "il y a un champ vide! ", new Command("OK"));
                } else if ((A.getText().length() < 3)) {
                    Dialog.show("Alert", "Minimum 3 caractere! ", new Command("OK"));
                } else {

                    Quiz e = new Quiz(A.getText(), (B.getCurrentSelected()));
                    if (ServiceQuiz.getInstance().addQuiz(e)) {
                        Dialog.show("Success", "Ajouté accepted", new Command("OK"));
                    } else {
                        Dialog.show("Success", "Ajouté accepted", new Command("OK"));
                        
                         Twilio.init("ACee06b0dbf480b460e85233ea6b4a881a", "8e27f33d5fd344def36edb311c9db1b1");
                    com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+21624420800"),
                            new PhoneNumber("+15413682029"), "your Quiz has been added succesfully").create();
                    new ProfileForm(theme).show();
                    }
                   

                }

            }

        });
        add(btnValider);

        // alert sound file name must begin with notification_sound
//        Display.getInstance().scheduleLocalNotification(ln,
//                System.currentTimeMillis() + 10000, LocalNotification.REPEAT_NONE);
    }

}
