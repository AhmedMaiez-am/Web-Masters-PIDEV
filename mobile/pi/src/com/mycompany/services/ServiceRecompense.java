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
import com.mycompany.entities.Recompense;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Lenovo
 */
public class ServiceRecompense {

    public ArrayList<Recompense> rec;
public boolean ok;
public static ServiceRecompense instance =null;
private ConnectionRequest req;
public static boolean resultok=true;

    public ServiceRecompense() {
        req =new ConnectionRequest();
    }

public static ServiceRecompense getInstance(){
    if(instance==null){
        instance =new ServiceRecompense();
    }
        return instance;
}

public boolean addRecompense(Recompense r){
    String url=Statics.BASE_URL+"/addRecomJSON?nomrec="+r.getNomrec()+"&nbrPoint="+r.getNbrPoint();
    req.setUrl(url);
    ConnectionRequest req=new ConnectionRequest(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
          ok=req.getResponseCode()==200;
          req.removeResponseListener(this);
        }
       
    });
     NetworkManager.getInstance().addToQueue(req);
    return ok;
}

public ArrayList<Recompense> parseRec(String jsonText){
    rec=new ArrayList<>();
    JSONParser j=new JSONParser();
    Map<String,Object> RecListJSON;
        try {
            RecListJSON = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                List<Map<String,Object>> list=(List<Map<String,Object>>)RecListJSON.get("root");
            for(Map<String,Object> obj:list){
                Recompense r=new Recompense();
                float id=Float.parseFloat(obj.get("idrec").toString());
                r.setIdrec((int) id);
                r.setNomrec(obj.get("nomrec").toString());
                r.setNbrPoint((int)Float.parseFloat(obj.get("nbrPoint").toString()));
                rec.add(r);
                
            }
        } catch (IOException ex) {
         
        }
    return rec;
   
    
}

public  ArrayList<Recompense> getAllRecompense(){
    String url=Statics.BASE_URL+"/listRecomJSON";
    req.setUrl(url);
    req.setPost(false);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
             rec=parseRec(new String(req.getResponseData()));
              req.removeResponseListener(this);
        }

      
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return rec;
    
} 


public  boolean supprimerRecompense(int id){
    String url=Statics.BASE_URL+"/deleteRecomJSON/"+id;
    req.setUrl(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
              req.removeResponseListener(this);
            
        }
        
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
    
    return resultok;
}

public boolean modifierRecomepense(Recompense r,int id){
    String url=Statics.BASE_URL+"/updateRecomJSON/"+id+"?nomrec="+r.getNomrec()+"&nbrPoint="+r.getNbrPoint();
    req.setUrl(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
           
        resultok=req.getResponseCode()==200;
        req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultok;
}



}
