/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Cours;
import entities.Inventairecours;
import entities.Pp;
import java.util.ArrayList;
import services.ServiceCours;
import services.ServicePP;

/**
 *
 * @author maiez
 */
public class ListeCoursForm extends Form{
    public ListeCoursForm (Resources theme){
        super(BoxLayout.y());
        setTitle("Liste des cours");
        Toolbar tb = getToolbar();
        tb.setTitleCentered(true);
        
        tb.addCommandToLeftBar("Retour",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new home(theme).show();
            }
        });
        ServiceCours sc = new ServiceCours();
        ArrayList<Cours> a = sc.afficherCours();
        
        Component[]listToAdd = new Component[a.size()];
        
        for(int i=0; i<sc.afficherCours().size();i++){
            final Cours c = a.get(i);
            
            MultiButton mb = new MultiButton();
            mb.setTextLine1("Nom : "+a.get(i).getNom());
            mb.setTextLine2("Type : "+a.get(i).getType());
            mb.setTextLine3("Description : "+a.get(i).getDescription());
            mb.setTextLine4("Prix : "+a.get(i).getPrix());
            final String nom = a.get(i).getNom();
            final String type = a.get(i).getType();
            final String desc = a.get(i).getDescription();
            final String prix = a.get(i).getPrix();
            Button btn = new Button("Ajouter");
            Button btn1 = new Button("Confirmer votre mail");
            Button btnPay = new Button ("Payer");
            TextField txtMail = new TextField("", "Entrez votre mail");
            TextField txtPass = new TextField("","Entrer le mot de passe de votre carte");
            btn1.setHidden(true);
            txtMail.setHidden(true);
            btnPay.setHidden(true);
            txtPass.setHidden(true);
            
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (prix == "Gratuit"){
                    Cours c = new Cours(nom, type,desc);
                    if(ServiceCours.getInstance().ajouterInvCours(c)){
                        Dialog.show("Succés", "Le cours a été ajouté avec succés à votre inventaire", new Command("OK"));
                    new ListeCoursForm(theme).show();
                    }
                    else
                        Dialog.show("Succés", "Le cours a été ajouté avec succés à votre inventaire", new Command("OK"));
                    new ListeCoursForm(theme).show();
                    }else{
                        btn1.setHidden(false);
                        txtMail.setHidden(false);
                        ServicePP sp = new ServicePP();
                        ArrayList<Pp> ap = sp.afficherp();
                        for (int j=0; j<sp.afficherp().size();j++){
                            final Pp ppp = ap.get(j);
                            final String mail = ap.get(j).getEmailp();
                            final String pass = ap.get(j).getPasscarte();
                            int prix1 =Integer.parseInt(prix);
                            int montant1=Integer.parseInt(ap.get(j).getPortefeuille());
                            btn1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                            if(mail.equals(txtMail.getText())){
                                btn1.setHidden(true);
                                txtMail.setHidden(true);
                                btnPay.setHidden(false);
                                txtPass.setHidden(false);
                                }/*else{
                                Dialog.show("Echèc", "Veuillez vérifier votre mail!", new Command("OK"));
                            }*/
                            }});
                            btnPay.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                
                                if (pass.equals(txtPass.getText())){
                                    if (prix1 > montant1){
                                        Dialog.show("Echèc", "Votre solde du portefeuille n'est pas suffisant!", new Command("OK"));
                                    }else{
                                        Cours c = new Cours(nom, type,desc);
                    if(ServiceCours.getInstance().ajouterInvCours(c)){
                        Dialog.show("Succés", "Le cours a été ajouté avec succés à votre inventaire", new Command("OK"));
                    new ListeCoursForm(theme).show();
                    }
                    else
                        Dialog.show("Succés", "Le cours a été ajouté avec succés à votre inventaire", new Command("OK"));
                    new ListeCoursForm(theme).show();
                                    }
                                }/*else{
                                    Dialog.show("Echèc", "Veuillez vérifier le mot de passe de votre carte!", new Command("OK"));
                                }*/
                            }
                            });
                        }
                    }
                }
            });
            
            addAll(mb,btn,txtMail,btn1,txtPass,btnPay);
        }
    }
}
