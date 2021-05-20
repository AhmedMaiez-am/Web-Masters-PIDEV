/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import entities.Conseil;


/**
 *
 * @author HP
 */
public class ServiceModifiercon {
    public void ModifierCon(Conseil conseil) {
     ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8000/updateConseil?id="+ conseil.getIdc()+"&nom="+ conseil.getNomc()+ "&conseil=" + conseil.getConseil();
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
}
