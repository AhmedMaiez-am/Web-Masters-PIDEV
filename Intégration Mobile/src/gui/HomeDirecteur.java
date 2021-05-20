/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
/**
 *
 * @author HP
 */
public class HomeDirecteur extends Form {
    
      
      Form current ;
     public HomeDirecteur (Resources theme) {
         current = this ;
        setTitle("WELCOME To KIDZY");
          Label lab = new Label("\n    Bienvenue KIDZY  ");
          setLayout(BoxLayout.y());
          Button D = new Button("Liste des Enfants");
          Button P = new Button("Liste des Parents ");
         // Button Tuteur = new Button("Liste des Tuteurs");
          Button Dec = new Button("Déconnecxion");
           Button stat = new Button("Statistique");
           Button map = new Button("Google maps");
     D.addActionListener(e -> new ListeEnfant(theme).show());
     P.addActionListener(em -> new ListeParents(theme).show());
     Dec.addActionListener(q -> new AccueilLogin(theme).show());
     stat.addActionListener(o ->{
         BudgetPieChart a = new BudgetPieChart();
               a.execute().show();});
    map.addActionListener(e-> new GoogleMapForm1(current).show());
     addAll(lab,D,P,stat,map,Dec);
}}
