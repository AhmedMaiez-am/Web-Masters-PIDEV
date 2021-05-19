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
import entities.Cours;
import utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author maiez
 */
public class ServiceCours {
    
    public static ServiceCours instance = null;
    private ConnectionRequest req;
    public boolean r;
    
    
    public static ServiceCours getInstance()
    {
        if (instance == null)
        
            instance = new ServiceCours();
            return instance;
        
    }
    public ServiceCours()
    {
        req= new ConnectionRequest();
    }

    public boolean ajouterInvCours(Cours cours)
    {
        String url = Statics.BASE_URL+"/addInvCours?nom="+cours.getNom()+"&type="+cours.getType()+"&description="+cours.getDescription();
        req.setUrl(url);
        req.addResponseListener((e)-> {
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return r;
    }
    
    
    public ArrayList<Cours>afficherCours()
    {
        ArrayList<Cours> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/displayCours";
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
                        Cours c = new Cours();
                        float idc = Float.parseFloat(obj.get("idc").toString());
                        
                        String nom = obj.get("nom").toString();
                        
                        String type = obj.get("type").toString();
                        
                        String description = obj.get("description").toString();
                        
                        String prix = obj.get("prix").toString();
                        
                        c.setIdc((int)idc);
                        c.setNom(nom);
                        c.setType(type);
                        c.setDescription(description);
                        c.setPrix(prix);
                        
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
