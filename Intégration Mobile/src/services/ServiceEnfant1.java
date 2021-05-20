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
import entities.Enfant;
import entities.Recompense;
import utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class ServiceEnfant1 {
    
    public ArrayList<Enfant> lEnf;
    public static boolean resultok=true;
    private ConnectionRequest req;
    public static ServiceEnfant1 instance=null;
    
    
    public ServiceEnfant1(){
        req=new ConnectionRequest();
    }
    public  static ServiceEnfant1 getInstance(){
        if(instance==null){
            instance=new ServiceEnfant1();
        }
        
            return instance;
    }
    
  public ArrayList<Enfant> parseEnf(String jsonText){
    lEnf=new ArrayList<>();
    JSONParser j=new JSONParser();
    Map<String,Object> RecListJSON;
        try {
            RecListJSON = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                List<Map<String,Object>> list=(List<Map<String,Object>>)RecListJSON.get("root");
            for(Map<String,Object> obj:list){
                Enfant e=new Enfant();
                float id=Float.parseFloat(obj.get("ide").toString());
                e.setIde((int) id);
                e.setNomenfant(obj.get("nomenfant").toString());
                e.setPrenomenfant(obj.get("prenomenfant").toString());
                e.setNbr_point((int)Float.parseFloat(obj.get("nbrPoint").toString()));
                lEnf.add(e);
                
            }
        } catch (IOException ex) {
         
        }
    return lEnf;
   
    
}
  
  
public  ArrayList<Enfant> getAllEnfant(){
    String url=Statics.BASE_URL+"/listenfant/";
    req.setUrl(url);
    req.setPost(false);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
             lEnf=parseEnf(new String(req.getResponseData()));
              req.removeResponseListener(this);
        }

      
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return lEnf;
    
} 
    
    
    
    
}
