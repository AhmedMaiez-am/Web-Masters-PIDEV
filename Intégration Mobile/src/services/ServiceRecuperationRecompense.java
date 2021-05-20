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
import entities.Recompense;
import entities.RecuperationRecompense;
import static services.ServiceRecompense.resultok;
import utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class ServiceRecuperationRecompense {
    
    private ArrayList<RecuperationRecompense> lRec;
    private ConnectionRequest req;
    public static  ServiceRecuperationRecompense instance=null;
    public static boolean resultok=true;

    public ServiceRecuperationRecompense(){
        req=new ConnectionRequest();
    }
    
    
    public static ServiceRecuperationRecompense getInstance(){
        if(instance==null){
            instance =new ServiceRecuperationRecompense();
        }
        
            return instance;
    
    }
   
    public boolean addRecuperation(RecuperationRecompense r){
         String url=Statics.BASE_URL+"/addRecup/?nomrec="+r.getNomrec()+"&nbrPoint="+r.getNbrPoint()+
                 "&emailp="+r.getEmailp()+"&nomenfant="+r.getNomenfant();
    req.setUrl(url);
    ConnectionRequest req=new ConnectionRequest(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
          resultok=req.getResponseCode()==200;
          req.removeResponseListener(this);
        }
       
    });
     NetworkManager.getInstance().addToQueue(req);
    return resultok;
        
    }
    
public ArrayList<RecuperationRecompense> parseRec(String jsonText){
    lRec=new ArrayList<>();
    JSONParser j=new JSONParser();
    Map<String,Object> RecListJSON;
        try {
            RecListJSON = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                List<Map<String,Object>> list=(List<Map<String,Object>>)RecListJSON.get("root");
            for(Map<String,Object> obj:list){
                
                RecuperationRecompense r=new RecuperationRecompense();
                float id=Float.parseFloat(obj.get("idrecup").toString());
                r.setIdrecup((int) id);
                r.setNomrec(obj.get("nomrec").toString());
                r.setNbrPoint((int)Float.parseFloat(obj.get("nbrPoint").toString()));
                r.setEmailp(obj.get("emailp").toString());
                r.setNomenfant(obj.get("nomEnfant").toString());
                lRec.add(r);
                
            }
        } catch (IOException ex) {
         
        }
    return lRec;
   
    
}

public  ArrayList<RecuperationRecompense> getAllRecuperation(){
    String url=Statics.BASE_URL+"/listrecupJSON";
    req.setUrl(url);
    req.setPost(false);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
             lRec=parseRec(new String(req.getResponseData()));
              req.removeResponseListener(this);
        }

      
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return lRec;
    
} 
  

public  boolean supprimerRecuperation(int id){
    String url=Statics.BASE_URL+"/deleteRecupJSON/"+id;
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


    
    
    
}
