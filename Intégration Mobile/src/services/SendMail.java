/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static services.ServiceReclamation.resultOK;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class SendMail {
     public static SendMail instance = null;
     private ConnectionRequest req;
     public static boolean resultOK=true;
     
     public static SendMail getInstance(){
         if(instance == null)
             instance=new SendMail(); 
             return instance;
     }
     
    public SendMail()
    {
        req=new ConnectionRequest();
    }
     private boolean sendEmail(String email) throws IOException {
        String url = Statics.BASE_URL + "/sendEmail";
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("email",email );
                req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        byte[] data = req.getResponseData();
        if (data == null) {
            throw new IOException("Network Err");
        }
        
        JSONParser parser = new JSONParser();
        Map response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(data), "UTF-8"));
        System.out.println("res" + response);
        List items = (List)response.get("items");
        return resultOK;
    }
    
}
