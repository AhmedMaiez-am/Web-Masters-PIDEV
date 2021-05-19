/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;

/**
 *
 * @author HP
 */
public class stat extends Form {
      public stat (Resources theme) {
          
          BudgetPieChart a = new BudgetPieChart();
           a.execute().show();
          
      }
}
