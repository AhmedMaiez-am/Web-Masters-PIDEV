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
import entities.Inventairecontes;
import utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author maiez
 */
public class ServiceInventaireContes {
    public static ServiceInventaireContes instance = null;
    private ConnectionRequest req;
    boolean r;
    
    
    public static ServiceInventaireContes getInstance()
    {
        if (instance == null)
        
            instance = new ServiceInventaireContes();
            return instance;
        
    }
    public ServiceInventaireContes()
    {
        req= new ConnectionRequest();
    }
    
    public ArrayList<Inventairecontes>afficherInventaireContes()
    {
        ArrayList<Inventairecontes> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/displayInvContes";
        req.setUrl(url);
        
        req.addResponseListener( new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try {
                    Map<String, Object>mapContes = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String, Object>> listOfMaps = (List<Map<String,Object>>) mapContes.get("root");
                    
                    for (Map<String, Object> obj : listOfMaps)
                    {
                        Inventairecontes c = new Inventairecontes();
                        float idcontesc = Float.parseFloat(obj.get("idcontesc").toString());
                        
                        String titrec = obj.get("titrec").toString();
                        
                        String auteurc = obj.get("auteurc").toString();
                        
                        c.setIdcontesc((int)idcontesc);
                        c.setTitrec(titrec);
                        c.setAuteurc(auteurc);
                        
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
    public boolean supprimerInvContes(String id)
    {
        String url = Statics.BASE_URL+"/deleteInvContes/"+id;
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
