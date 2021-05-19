/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Enfant;
import com.mycompany.entities.Parent;
import static com.mycompany.services.ServiceEnfant.instance;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class ServiceParent {
    public ArrayList<Parent> lP;
     public static boolean resultok=true;
    private ConnectionRequest req;
    public static ServiceParent instance=null;
    
    
     public ServiceParent(){
        req=new ConnectionRequest();
    }
    public  static ServiceParent getInstance(){
        if(instance==null){
            instance=new ServiceParent();
        }
        
            return instance;
    }
    
    public ArrayList<Parent> parsePar(String jsonText){
    lP=new ArrayList<>();
    JSONParser j=new JSONParser();
    Map<String,Object> RecListJSON;
        try {
            RecListJSON = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                List<Map<String,Object>> list=(List<Map<String,Object>>)RecListJSON.get("root");
            for(Map<String,Object> obj:list){
                Parent p=new Parent();
                float id=Float.parseFloat(obj.get("idp").toString());
                p.setIdp((int) id);
                p.setNomp(obj.get("nomp").toString());
                p.setTelp(obj.get("telp").toString());
                p.setEmailp(obj.get("emailp").toString());
                lP.add(p);
                
            }
        } catch (IOException ex) {
         
        }
    return lP;
   
    
}
  
    
     
public  ArrayList<Parent> getAllParent(){
    String url=Statics.BASE_URL+"/listparent/";
    req.setUrl(url);
    req.setPost(false);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
             lP=parsePar(new String(req.getResponseData()));
              req.removeResponseListener(this);
        }

      
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return lP;
    
} 

    
    
    
    
    
    
    
    
}
