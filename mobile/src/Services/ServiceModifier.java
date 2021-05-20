/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import entities.Reclamation;

/**
 *
 * @author HP
 */
public class ServiceModifier {
    public void ModifierRec(Reclamation reclamation) {
     ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8000/updateReclamation?id="+ reclamation.getIdr()+ "&nom=" + reclamation.getNom()+ "&reclamation=" + reclamation.getReclamation();
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
}
