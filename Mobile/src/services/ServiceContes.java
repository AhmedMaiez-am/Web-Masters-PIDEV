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
import entities.Contes;
import utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author maiez
 */
public class ServiceContes {
     public static ServiceContes instance = null;
    private ConnectionRequest req;
    public Boolean r;
    
    
    public static ServiceContes getInstance()
    {
        if (instance == null)
        
            instance = new ServiceContes();
            return instance;
        
    }
    public ServiceContes()
    {
        req= new ConnectionRequest();
    }

    public boolean ajouterInvContes(Contes contes)
    {
        String url = Statics.BASE_URL+"/addInvContes?titre="+contes.getTitre()+"&auteur="+contes.getAuteur();
        req.setUrl(url);
        req.addResponseListener((e)-> {
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return r;
    }
    
    public ArrayList<Contes>afficherContes()
    {
        ArrayList<Contes> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/displayContes";
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
                        Contes c = new Contes();
                        float idconte = Float.parseFloat(obj.get("idconte").toString());
                        
                        String titre = obj.get("titre").toString();
                        
                        String auteur = obj.get("auteur").toString();
                        
                        c.setIdconte((int)idconte);
                        c.setTitre(titre);
                        c.setAuteur(auteur);
                        
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
}
