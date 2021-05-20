/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Inventairecours;
import utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author maiez
 */
public class ServiceInventaireCours {
    public static ServiceInventaireCours instance = null;
    private ConnectionRequest req;
    public boolean r;
    
    
    public static ServiceInventaireCours getInstance()
    {
        if (instance == null)
        
            instance = new ServiceInventaireCours();
            return instance;
        
    }
    public ServiceInventaireCours()
    {
        req= new ConnectionRequest();
    }
    
    public ArrayList<Inventairecours>afficherInventaireCours()
    {
        ArrayList<Inventairecours> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/displayInvCours";
        req.setUrl(url);
        
        req.addResponseListener( new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try {
                    Map<String, Object>mapCours = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String, Object>> listOfMaps = (List<Map<String,Object>>) mapCours.get("root");
                    
                    for (Map<String, Object> obj : listOfMaps)
                    {
                        Inventairecours c = new Inventairecours();
                        float idcc = Float.parseFloat(obj.get("idcc").toString());
                        
                        String nomc = obj.get("nomc").toString();
                        
                        String typecc = obj.get("typecc").toString();
                        
                        String descriptioncc = obj.get("descriptioncc").toString();
                        
                        c.setIdcc((int)idcc);
                        c.setNomc(nomc);
                        c.setTypecc(typecc);
                        c.setDescriptioncc(descriptioncc);
                        
                        result.add(c);
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
                NetworkManager.getInstance().addToQueueAndWait(req);
                return result;  
    }
    public boolean supprimerInvCours(String id)
    {
        String url = Statics.BASE_URL+"/deleteInvCours/"+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                r = req.getResponseCode() == 200;
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return r;
    }
}
