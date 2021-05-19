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
import entites.Enfant;
import entites.Parents;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.statics;

/**
 *
 * @author HP
 */
public class serviceParents {
    
      public static serviceParents instance=null;
    public ArrayList<Parents> listall , listall1;
    public ArrayList<Parents> tasks , tasks1;
 public boolean resultOK;
    private ConnectionRequest req;
    public serviceParents() {
         req = new ConnectionRequest();
    }
        public static serviceParents getInstance() {
        if (instance == null) {
            instance = new serviceParents();
        }
        return instance;
    }
    
    
    public ArrayList<Parents> listAll(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
            Parents t = new Parents ();
         
              t.setEmailP(obj.get("emailp").toString());
               t.setPrenomP(obj.get("nomp").toString());
             t.setIdP(((int)Float.parseFloat(obj.get("idp").toString())));
               // t.setIdparent((int) obj.get("idparent"));
              //  t.setPassword(obj.get("password").toString());
              //  t.setImage(obj.get("image").toString());
             tasks.add(t);

            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
       public ArrayList<Parents> getListalls(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(statics.BASE_URL +"/APIParent");
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                serviceParents ser = new serviceParents();
                listall = ser.listAll(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listall;
    }
    public boolean supprimer(String id){
        
        String url = statics.BASE_URL+"/removeParents/"+id;
        
        
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        
        return resultOK;
    }
       public boolean addParent(Parents e){
        
         String url = statics.BASE_URL +"/ajouterParent?emailp="+e.getEmailP()+"&nomp="+e.getNomP()+"&prenomp="+e.getPrenomP()+"&passwordp="+e.getPasswordP()+"&telp="+e.getTelP()+"&numcarte="+e.getNumcarteP()+"&passcarte="+e.getPasscaretp()+"&portfeuille="+e.getPf();
         req.setUrl(url);
        System.out.println(url);
        req.addResponseListener((ex) -> {
            String str = new String(req.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
     }
     
  
}
