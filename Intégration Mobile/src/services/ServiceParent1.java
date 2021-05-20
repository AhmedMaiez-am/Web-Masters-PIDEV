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
import entities.Parents;
import static services.ServiceEnfant1.instance;
import utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class ServiceParent1 {
    public ArrayList<Parents> lP;
     public static boolean resultok=true;
    private ConnectionRequest req;
    public static ServiceParent1 instance=null;
    
    
     public ServiceParent1(){
        req=new ConnectionRequest();
    }
    public  static ServiceParent1 getInstance(){
        if(instance==null){
            instance=new ServiceParent1();
        }
        
            return instance;
    }
    
    public ArrayList<Parents> parsePar(String jsonText){
    lP=new ArrayList<>();
    JSONParser j=new JSONParser();
    Map<String,Object> RecListJSON;
        try {
            RecListJSON = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                List<Map<String,Object>> list=(List<Map<String,Object>>)RecListJSON.get("root");
            for(Map<String,Object> obj:list){
                Parents p=new Parents();
                float id=Float.parseFloat(obj.get("idp").toString());
                p.setIdP((int) id);
                p.setNomP(obj.get("nomp").toString());
                p.setTelP(obj.get("telp").toString());
                p.setEmailP(obj.get("emailp").toString());
                lP.add(p);
                
            }
        } catch (IOException ex) {
         
        }
    return lP;
   
    
}
  
    
     
public  ArrayList<Parents> getAllParent(){
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
